package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Bursement;
import com.xxxx.server.pojo.ProcessList;
import com.xxxx.server.pojo.Resign;
import com.xxxx.server.pojo.Reviewed;
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
public interface ReviewedMapper extends BaseMapper<Reviewed> {

    Integer saveForHoliday(@Param("entity") ProcessList processList, @Param("shenID") Long shenID);

    Integer auditUpdate(@Param("userId") Long userId,
                        @Param("processId") Long processId,
                        @Param("auditDes") String auditDes,
                        @Param("status") Long status);

    Reviewed selectRecordById(@Param("userId") Long userId, @Param("processId") Long processId);

    Integer insertRecord(@Param("entity") Reviewed reviewed);

    Integer saveForReimburse(@Param("entity") Bursement bursement,@Param("auditUserId") Long auditUserId);

    Integer saveForResign(@Param("entity") Resign resign, @Param("id") Integer id);

    List<Reviewed> selectRecordByProcessId(@Param("processId") Long processId);
}
