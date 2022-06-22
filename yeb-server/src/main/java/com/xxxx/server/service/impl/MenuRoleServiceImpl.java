package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.mapper.MenuRoleMapper;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    MenuRoleMapper menuRoleMapper;

    //更新角色菜单
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (null == mids || 0 == mids.length){
            return RespBean.success("更新成功!");
        }
        //批量添加权限
        Integer result = menuRoleMapper.batchInset(rid, mids);
        if (result == mids.length) return RespBean.success("修改成功！");
        return RespBean.error("修改失败！");
    }
}
