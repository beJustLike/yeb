package com.xxxx.server.controller;

import com.xxxx.server.pojo.*;
import com.xxxx.server.service.impl.ProcessListServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author liyongkang
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/processList")
public class ProcessListController {

    @Resource
    ProcessListServiceImpl processListService;

    @PostMapping("/submitHoliday")
    @ApiOperation(value = "请假申请")
    public RespBean submitHoliday(@RequestBody ProcessList processList) {
        return processListService.submitHoliday(processList);
    }

    @PostMapping("/submitReimburse")
    @ApiOperation(value = "报销申请")
    public RespBean submitReimburse(@RequestBody Reimburse reimburse){
        return processListService.submitReimburse(reimburse);
    }

    @PostMapping("/submitResign")
    @ApiOperation(value = "离职申请")
    public RespBean submitResign(@RequestBody Resign resign){
        return processListService.submitResign(resign);
    }

    @GetMapping("/getAllApply")
    @ApiOperation(value = "显示审核人接收到的所有申请")
    public RespPageBean getAllApply(@RequestParam("userId") Long userId,
                                    @RequestParam(required = false,defaultValue = "1") Integer rows,
                                    @RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return processListService.getAuditList(userId,rows,pageSize);

    }

    @PostMapping("/auditApply")
    @ApiOperation(value = "审核操作")
    public RespBean auditApply(@RequestParam("userId") @ApiParam("当前用户Id") Long userId,
                               @RequestParam("processId") @ApiParam("流程id") Long processId,
                               @RequestParam("auditDes") @ApiParam("审核理由") String auditDes,
                               @RequestParam("status") @ApiParam("审核状态") Long status){
        try {
            return RespBean.success(processListService.auditApply(userId,processId,auditDes,status));
        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error("审核出错");
        }
    }

    @GetMapping("/findMyApply")
    @ApiOperation(value = "查询自己的流程")
    public RespPageBean findMyApply(@RequestParam("userId") Long userId,
                                    @RequestParam(value = "rows",defaultValue = "1") Long rows,
                                    @RequestParam(value = "pageSize",defaultValue = "10") Long pageSize){
        return processListService.findMyApply(userId,rows,pageSize);
    }

    @GetMapping("/processDetail")
    @ApiOperation(value = "查询流程的详细信息")
    public RespBean processDetail(@RequestParam("processId") Long processId){
        return processListService.processDetail(processId);
    }

    @PutMapping("/deleteProcess")
    @ApiOperation(value = "审核页面删除流程")
    public RespBean deleteProcess(@RequestParam("processId") Long processId,
                                  @RequestParam("userId") Long userId){
        return processListService.deleteProcess(processId,userId);
    }

    @PutMapping("/deleteMyProcess")
    @ApiOperation(value = "删除个人流程")
    public RespBean deleteMyProcess(@RequestParam("processId") Long processId){
        return processListService.deleteMyProcess(processId);
    }
}
