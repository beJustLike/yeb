package com.xxxx.server.service;

import com.xxxx.server.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.tomcat.jni.Proc;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-07
 */
public interface IProcessListService extends IService<ProcessList> {
    RespBean submitHoliday(ProcessList processList) throws Exception;

    RespPageBean getAuditList(Long userId,Integer rows,Integer pageSize);

    String auditApply(Long userId, Long processId, String auditDes, Long status);

    RespBean submitReimburse(Reimburse reimburse);

    RespBean submitResign(Resign resign);

    RespBean processDetail(Long processId);



}
