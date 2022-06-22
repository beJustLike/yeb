package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.mapper.*;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IProcessListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liyongkang
 * @since 2022-01-07
 */
@Service
public class ProcessListServiceImpl extends ServiceImpl<ProcessListMapper, ProcessList> implements IProcessListService {

    @Resource
    private ProcessListMapper processListMapper;

    @Resource
    private HolidayMapper holidayMapper;

    @Resource
    private ReviewedMapper reviewedMapper;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private TypeListMapper typeListMapper;

    @Resource
    private BursementMapper bursementMapper;

    @Resource
    private DetailsburseMapper detailsburseMapper;

    @Resource
    private ResignMapper resignMapper;


    /**
     * 请假申请
     */
    @Override
    public RespBean submitHoliday(ProcessList processList) {

        //根据用户id查找用户上司id
        Long parentId = adminMapper.findParentIdByUserId(processList.getProcessUserId());
        //根据审核人姓名查找用户id
        Long shenID = adminMapper.findIdByShenUser(processList.getShenUser());

        try {
            if (!parentId.equals(shenID)) {
                return RespBean.error("请选择你的上司");
            } else {
                TypeList typeList = typeListMapper.findByTypeId(processList.getTypeId());

                if (40 == processList.getTypeId() && typeList.getSortValue() < processList.getProcessDays()) {
                    return RespBean.error("婚假必须小于10天");
                } else if (38 == processList.getTypeId() && typeList.getSortValue() < processList.getProcessDays()) {
                    return RespBean.error("单次事假必须小于4天");
                } else if (42 == processList.getTypeId() && typeList.getSortValue() < processList.getProcessDays()) {
                    return RespBean.error("陪产假必须小于10天");
                } else {
                    //存流程表
                    processListMapper.saveForHoliday(processList.setTypeName("请假申请"));
                    //存请假表
                    Holiday h = new Holiday();
                    h.setLeaveDays(processList.getProcessDays())
                            .setTypeId(processList.getTypeId())
                            .setProId(processList.getProcessId());
                    holidayMapper.save(h);
                    //存审核表
                    reviewedMapper.saveForHoliday(processList, shenID);
                    return RespBean.success("操作成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("操作错误,请重试");
        }
    }

    /**
     * 报销申请
     */
    @Override
    public RespBean submitReimburse(Reimburse reimburse) {

        Admin applyUser = adminMapper.selectUserById(reimburse.getUserId());//申请人
        Admin auditUser = adminMapper.findByUserName(reimburse.getShenUser());//审核人
        Admin zhenUser = adminMapper.selectUserById(reimburse.getUserName());//证明人

        Long parentId = applyUser.getParentId();//申请人上司id
        Long auditUserId = Long.valueOf(auditUser.getId() + "");//审核人id

        try {
            if (parentId.equals(auditUserId)) {
                //存流程主表
                ProcessList processList = new ProcessList().setDeeplyId(reimburse.getDeeplyId()).setShenUser(auditUser.getName())
                        .setProcessDes(reimburse.getProcessDes()).setProcessName(reimburse.getTitle()).setTypeName("费用报销")
                        .setProcessUserId(reimburse.getUserId());
                processListMapper.saveForReimburse(processList);

                //存费用报销表
                Bursement bursement = new Bursement()
                        .setProId(processList.getProcessId())
                        .setName(reimburse.getName())
                        .setUserName(Long.valueOf(zhenUser.getId()))
                        .setTypeId(reimburse.getTypeId());
                bursementMapper.save(bursement);

                double allDetailMoney = 0;
                int allInvoices = 0;

                //存报销明细表
                for (DetailsBurse d : reimburse.getList()) {
                    allInvoices += d.getInvoices();
                    allDetailMoney += d.getDetailmoney();
                    detailsburseMapper.save(d.setBursmentId(bursement.getBursementId()));
                }
                bursementMapper.updateLater(bursement.getBursementId(), allDetailMoney, allInvoices);
                //存审核表
                reviewedMapper.saveForReimburse(bursement, auditUserId);
                return RespBean.success("申请成功");

            } else {
                return RespBean.error("审核人请选择你的上司");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("操作失败");
        }
    }

    /**
     * 离职申请
     */
    @Override
    public RespBean submitResign(Resign resign) {

        Admin applyUser = adminMapper.selectUserById(resign.getProcessUserId());//申请人
        Admin auditUser = adminMapper.findByUserName(resign.getShenUser());//审核人
        try {
            if (applyUser.getParentId().equals(Long.valueOf(auditUser.getId()))) {

                //存流程主表
                ProcessList processList = new ProcessList()
                        .setProcessName(resign.getTitle())
                        .setDeeplyId(resign.getDeeplyId())
                        .setShenUser(resign.getShenUser())
                        .setProcessUserId(resign.getProcessUserId())
                        .setTypeName("离职申请")
                        .setProcessDes(resign.getProcessDes());
                processListMapper.saveForResign(processList);

                //存离职表
                resignMapper.save(resign.setProId(processList.getProcessId()));

                //存审核表
                reviewedMapper.saveForResign(resign, auditUser.getId());
                return RespBean.success("申请成功");
            } else {
                return RespBean.error("审核人请选择你的上司");
            }

        } catch (Exception e) {
            System.out.println(e);
            return RespBean.error("操作错误,请重试");
        }
    }

    /**
     * 显示所有审核流程
     */
    @Override
    public RespPageBean getAuditList(Long userId, Integer row, Integer pageSize) {
        Page<ProcessList> page = new Page<>(row, pageSize);
        IPage<ProcessList> auditList = processListMapper.getAuditList(userId, page);
        RespPageBean respPageBean = new RespPageBean(auditList.getTotal(), auditList.getRecords());
        return respPageBean;
    }

    /**
     * 流程审批
     * status == 23（未审核）
     * status == 24（审核中）
     * status == 25（已批准）
     * status == 26（已驳回）
     */
    @Override
    public String auditApply(Long userId, Long processId, String auditDes, Long status) {

        ProcessList processList = processListMapper.selectProcessById(processId);//找到流程

        if (25 == processList.getStatusId() || 26 == processList.getStatusId()) {
            return "该流程已经被审核";
        } else {

            Admin shenUser = adminMapper.selectUserById(userId);//审核人
            Admin applyUser = adminMapper.selectUserById(processList.getProcessUserId());//流程申请人

            if ("请假申请".equals(processList.getTypeName())) {

                //审核人为人事部经理
                if (9 == shenUser.getPosId()) {
                    //更新审核表
                    reviewedMapper.auditUpdate(userId, processId, auditDes, status);
                    if (14 == applyUser.getPosId()) {
                        //申请人为人事部职员，只需要一次审核
                        holidayMapper.auditUpdate(processId, auditDes);
                    } else {
                        //申请人不是人事部职员，更新请假表审核理由
                        holidayMapper.auditUpdateSecond(processId, auditDes);
                        //更新流程表审核人
                        processListMapper.updateShenUserByPerson(processId);
                    }
                    //修改流程状态
                    processListMapper.auditUpdate(processId, status);
                    return "审核成功";
                } else {
                    //审核人为其他部门经理
                    if (26 != status) { //审核人同意请假
                        reviewedMapper.auditUpdate(userId, processId, auditDes, 25L);
                        holidayMapper.auditUpdate(processId, auditDes);
                        processListMapper.auditUpdate(processId, 24L);
                        //审核流转到人事部主管
                        reviewedMapper.insertRecord(new Reviewed()
                                .setUserId(6)
                                .setAdvice(null)
                                .setReviewedTime(null)
                                .setStatusId(23L)
                                .setProId(processId));
                        return "审核成功";
                    } else { //审核人不同意请假
                        reviewedMapper.auditUpdate(userId, processId, auditDes, 26L);
                        holidayMapper.auditUpdate(processId, auditDes);
                        processListMapper.auditUpdate(processId, 26L);
                        return "审核成功";
                    }
                }
            } else if ("费用报销".equals(processList.getTypeName())) {
                //审核人为财务部经理
                if (8 == shenUser.getPosId()) {
                    reviewedMapper.auditUpdate(userId, processId, auditDes, status);
                    if (11 == applyUser.getPosId() || 12 == applyUser.getPosId()) {
                        //申请人为出纳员或会计
                        bursementMapper.auditUpdate(processId, auditDes, userId);
                    } else {
                        //申请人不是财务部职员
                        bursementMapper.auditUpdateSecond(processId, auditDes, userId);
                        processListMapper.updateShenUserByFinance(processId);
                    }
                    processListMapper.auditUpdate(processId, status);
                    return "审核成功";
                } else {
                    //审核人为其他部门经理
                    if (26 != status) { //同意报销
                        reviewedMapper.auditUpdate(userId, processId, auditDes, 25L);
                        bursementMapper.auditUpdateThree(processId, auditDes);
                        processListMapper.auditUpdate(processId, 24L);
                        //审核流转
                        reviewedMapper.insertRecord(new Reviewed()
                                .setUserId(3)
                                .setAdvice(null)
                                .setReviewedTime(null)
                                .setStatusId(23L)
                                .setProId(processId));
                        return "审核成功";
                    } else { //不同意报销
                        reviewedMapper.auditUpdate(userId, processId, auditDes, 26L);
                        bursementMapper.auditUpdateThree(processId, auditDes);
                        processListMapper.auditUpdate(processId, 26L);
                        return "审核成功";
                    }
                }
            } else if ("离职申请".equals(processList.getTypeName())) {

                //审核人为人事部经理
                if (9 == shenUser.getPosId()) {
                    reviewedMapper.auditUpdate(userId, processId, auditDes, status);
                    if (14 == applyUser.getPosId()) {
                        //申请人为人事部职员
                        resignMapper.auditUpdate(processId, auditDes);
                    } else {
                        //申请人不是人事部职员
                        processListMapper.updateShenUserByPerson(processId);
                        resignMapper.auditUpdateSecond(processId, auditDes);
                    }
                    processListMapper.auditUpdate(processId, status);
                    return "审核成功";
                } else {
                    //审核人为其他部门经理
                    if (26 != status) { //同意离职
                        reviewedMapper.auditUpdate(userId, processId, auditDes, 25L);
                        resignMapper.auditUpdate(processId, auditDes);
                        processListMapper.auditUpdate(processId, 24L);
                        //审核流转到人事部主管
                        reviewedMapper.insertRecord(new Reviewed()
                                .setUserId(6)
                                .setAdvice(null)
                                .setReviewedTime(null)
                                .setStatusId(23L)
                                .setProId(processId));

                        return "审核成功";
                    } else { //不同意请假
                        reviewedMapper.auditUpdate(userId, processId, auditDes, 26L);
                        resignMapper.auditUpdate(processId, auditDes);
                        processListMapper.auditUpdate(processId, 26L);
                        return "审核成功";
                    }
                }
            }
        }
        return "审核失败,请重试";
    }

    /**
     * 查看自己的申请
     */
    public RespPageBean findMyApply(Long userId, Long rows, Long pageSize) {

        Page<ProcessList> page = new Page<>(rows, pageSize);
        IPage<ProcessList> list = processListMapper.selectMyProcessList(userId, page);
        return new RespPageBean(list.getTotal(), list.getRecords());
    }

    /**
     * 查看流程的详细信息
     */
    @Override
    public RespBean processDetail(Long processId) {

        RespBean respBean = new RespBean();
        //通过processId获取对应的流程主表数据
        ProcessList processList = processListMapper.selectProcessById(processId);
        if ("请假申请".equals(processList.getTypeName())) {
            processList.setHolidayRecord(holidayMapper.getHolidayByProId(processId));
        } else if ("费用报销".equals(processList.getTypeName())) {
            Bursement bursement = bursementMapper.getBurseRecord(processId);
            bursement.setDetails(detailsburseMapper.getDetailsburse(bursement.getBursementId()));
            processList.setBursementRecord(bursement);
        } else if ("离职申请".equals(processList.getTypeName())) {
            processList.setResignRecord(resignMapper.getResignDetail(processId));
        }
        respBean.setObj(processList.setRecords(reviewedMapper.selectRecordByProcessId(processId)));
        return respBean;
    }

    //删除审核流程
    public RespBean deleteProcess(Long processId, Long userId) {
        try {
            Integer integer = processListMapper.deleteProcess(processId, userId);
            if (integer > 0) {
                return RespBean.success("删除成功");
            }
        } catch (Exception e) {
            return RespBean.success("删除失败");
        }
        return RespBean.error("删除失败");
    }

    //删除个人流程
    public RespBean deleteMyProcess(Long processId) {
        try {
            if (processListMapper.deleteMyProcess(processId) > 0) {
                return RespBean.success("删除成功");
            }
        } catch (Exception e) {
            return RespBean.success("删除失败");
        }
        return RespBean.error("删除失败");
    }
}
