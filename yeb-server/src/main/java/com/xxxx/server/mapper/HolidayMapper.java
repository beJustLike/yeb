package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Holiday;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-07
 */
public interface HolidayMapper extends BaseMapper<Holiday> {

    Integer save(@Param("entity") Holiday h);

    Integer auditUpdate(@Param("processId") Long processId, @Param("auditDes") String auditDes);


    Integer auditUpdateSecond(@Param("processId") Long processId, @Param("auditDes") String auditDes);

    Holiday getHolidayByProId(Long processId);
}
