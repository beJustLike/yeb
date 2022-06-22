package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
public interface PositionMapper extends BaseMapper<Position> {

    Position getPositionById(Integer posId);
}
