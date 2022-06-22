package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Employee;
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
public interface AdminMapper extends BaseMapper<Admin> {

    IPage<Admin> getEmpByPage(@Param("name") String name, @Param("page") Page<Employee> page);

    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);

    Admin findByUserName(String nameUser);

    Long findParentIdByUserId(Long userId);

    Long findIdByShenUser(String shenUser);

    String getshenNameById(Long userId);

    Admin selectUserById(Long userId);

    Integer addUser(@Param("admin") Admin admin);

    Admin getAdminById(@Param("id") Integer id);

    IPage<Admin> getAdminWithSalary(Page<Admin> page);
}
