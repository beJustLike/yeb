package com.xxxx.server.service;

import com.xxxx.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
public interface IMenuRoleService extends IService<MenuRole> {

    //更新角色菜单
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
