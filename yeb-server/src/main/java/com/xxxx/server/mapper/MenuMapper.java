package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> getMenuByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenuRoleList();

    List<Menu> getAllMenus();
}
