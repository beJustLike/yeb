package com.xxxx.server.mapper;

import com.xxxx.server.pojo.DetailsBurse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-26
 */
public interface DetailsburseMapper extends BaseMapper<DetailsBurse> {

    Integer save(@Param("entity") DetailsBurse detailsBurse);

    List<DetailsBurse> getDetailsburse(Long bursementId);
}
