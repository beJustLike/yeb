package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Resign;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-27
 */
public interface ResignMapper extends BaseMapper<Resign> {

    Integer save(@Param("entity") Resign resign);

    Integer auditUpdate(@Param("processId") Long processId, @Param("auditDes") String auditDes);

    Integer auditUpdateSecond(@Param("processId") Long processId, @Param("auditDes") String auditDes);

    Resign getResignDetail(Long processId);
}
