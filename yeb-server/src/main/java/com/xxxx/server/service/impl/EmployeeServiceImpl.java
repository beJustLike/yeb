package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.mapper.MailLogMapper;
import com.xxxx.server.pojo.*;
import com.xxxx.server.mapper.EmployeeMapper;
import com.xxxx.server.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Resource
    private AdminMapper adminMapper;

    //获取所有员工(分页)
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {

        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean maxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        return RespBean.success("返回成功", String.format("%08d", Integer.parseInt(maps.get(0)
                .get("max(workID)")
                .toString()) + 1));
    }

    //添加客户
    @Override
    public RespBean addEmp(Employee employee) {
        //处理合同期限，保留2位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        employee.setPosId(6).setJobLevelId(2).setDepartmentId(3);

        if (1 == employeeMapper.insert(employee)) {
        //Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    //查询员工
    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }

    //获取所有员工账套
    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        //开启分页
        Page<Admin> page = new Page<>(currentPage, size);
        IPage<Admin> AdminIPage = adminMapper.getAdminWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(AdminIPage.getTotal(), AdminIPage.getRecords());
        return respPageBean;
    }
}
