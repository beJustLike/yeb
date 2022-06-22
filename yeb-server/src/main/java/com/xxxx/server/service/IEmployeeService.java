package com.xxxx.server.service;

import com.xxxx.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
public interface IEmployeeService extends IService<Employee> {

    //获取所有客户(分页)
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean maxWorkID();

    //添加客户
    RespBean addEmp(Employee employee);

    //查询客户
    List<Employee> getEmployee(Integer id);

    //获取所有员工工资账套
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
