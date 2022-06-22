package com.xxxx.server.mapper;

import com.xxxx.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    //更新角色菜单
    Integer batchInset(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
