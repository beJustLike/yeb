package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.ProcessList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-07
 */
public interface ProcessListMapper extends BaseMapper<ProcessList> {

    //提交请假申请
    int saveForHoliday(@Param("entity") ProcessList processList);

    //提交报销申请
    int saveForReimburse(@Param("entity") ProcessList processList);

    //提交离职申请
    Integer saveForResign(@Param("entity") ProcessList processList);

    //获取某个用户的审核列表
    IPage<ProcessList> getAuditList(@Param("userId") Long userId, Page page);

    //审核操作
    Integer auditUpdate(@Param("processId") Long processId, @Param("status") Long status);

    //根据流程id查询某一个流程
    ProcessList selectProcessById(@Param("processId") Long processId);

    //查询个人已经提交的流程
    IPage<ProcessList> selectMyProcessList(@Param("id") Long id, Page page);

    //人事进行二次审核
    Integer updateShenUserByPerson(Long processId);

    //财务人员进行二级审核
    Integer updateShenUserByFinance(Long processId);

    //查询所有的操作员
    List<Admin> findAllUser();

    //删除审核列表中流程
    Integer deleteProcess( @Param("processId") Long processId,@Param("userId") Long userId);

    //删除个人流程
    Integer deleteMyProcess(Long processId);
}
