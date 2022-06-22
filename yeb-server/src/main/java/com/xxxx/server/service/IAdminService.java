package com.xxxx.server.service;

import com.xxxx.server.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
public interface IAdminService extends IService<Admin> {

    //登录之后返回token
    RespBean login(String userName, String password, String code, HttpServletRequest request);

    //根据用户名获取用户
    Admin getAdminByUserName(String userName);

    //根据用户id查询角色列表
    List<Role> getRoles(Integer adminId);

    //获取所有员工
    List<Admin> getAllAdmins(String keywords);

    //更新操作员角色
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    //更新用户密码
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);

    //更新用户头像
    RespBean updateAdminUserFace(String url, Integer id, Authentication authentication);

    //重置用户密码
    RespBean resetPassword(Integer id);

    RespPageBean getAllEmp(String name, Long rows, Long pageSize);

    RespBean addUser(Admin admin);
}
