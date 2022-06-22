package com.xxxx.server.mapper;

import com.xxxx.server.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    //更新操作员角色
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);

    Integer getRoleIdByAdminId(Integer id);

    Integer addAdminWithRole(@Param("adminId") Integer adminId, @Param("roleId") Integer roleId);
}
