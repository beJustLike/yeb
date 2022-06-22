package com.xxxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IAdminService;
import com.xxxx.server.service.IEmployeeService;
import com.xxxx.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工账套
 * @Author liyongkang
 * @Date 2021/12/15
 **/
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Resource
    private ISalaryService salaryService;

    @Resource
    private IEmployeeService employeeService;

    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工账套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeWithSalary(currentPage,size);
    }

    @ApiOperation(value = "更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid,Integer sid){
        if (adminService.update(new UpdateWrapper<Admin>().set("salaryId",sid).eq("id", eid))){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

}
