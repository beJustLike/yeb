package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    //获取所有部门
    List<Department> getAllDepartments(Integer parentId);

    //添加部门
    void addDep(Department dep);

    //删除部门
    void deleteDep(Department dep);

    Department getDepartmentById(Integer departmentId);
}
