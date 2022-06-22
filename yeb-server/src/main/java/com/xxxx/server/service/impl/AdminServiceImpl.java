package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.utils.AdminUtils;
import com.xxxx.server.config.security.component.JwtTokenUtils;
import com.xxxx.server.mapper.*;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private MailLogMapper mailLogMapper;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @Resource
    private AdminRoleMapper adminRoleMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    //登录之后返回token
    @Override
    public RespBean login(String userName, String password, String code, HttpServletRequest request) {

        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equals(code)) {
            return RespBean.error("验证码输入错误，请重新输入。");
        }
        //jwt根据loadUserByUsername()方法实现登录，返回UserDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确，请重新输入。");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员。");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        //生成token
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return RespBean.success("登陆成功", tokenMap);
    }

    //根据用户名获取用户
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username)
                .eq("enabled", true));
    }

    //根据用户id查询角色列表
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    //获取所有操作员
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(), keywords);
    }

    //更新操作员角色
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if (rids.length == result) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    //更新用户密码
    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPass, admin.getPassword())) {
            admin.setPassword(encoder.encode(pass));
            int i = adminMapper.updateById(admin);
            if (i == 1) {
                return RespBean.success("更新成功");
            }
        }
        return RespBean.error("更新失败");
    }

    //更新用户头像
    @Override
    public RespBean updateAdminUserFace(String url, Integer id, Authentication authentication) {

        Admin admin = adminMapper.selectById(id);
        admin.setUserFace(url);
        int result = adminMapper.updateById(admin);
        if (1 == result) {
            Admin principal = (Admin) authentication.getPrincipal();
            principal.setUserFace(url);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities()));
            return RespBean.success("更新成功!", url);
        }
        return RespBean.error("更新失败!");
    }

    //重置用户密码
    @Override
    public RespBean resetPassword(Integer id) {
        Admin admin = adminMapper.selectById(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode("123"));
        int i = adminMapper.updateById(admin);
        if (i == 1) {
            return RespBean.success("更新成功，新密码为123");
        }
        return RespBean.error("更新失败");
    }

    @Override
    public RespPageBean getAllEmp(String name, Long rows, Long pageSize) {
        Page<Employee> page = new Page<>(rows, pageSize);
        IPage<Admin> employeeByPage = adminMapper.getEmpByPage(name,page);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean addUser(Admin admin) {
        try {

            //获取员工职位
            Position position = positionMapper.getPositionById(admin.getPosId());
            admin.setPosName(position.getName());

            //根据职位获取角色
            Role role = roleMapper.getRoleByposId(position.getId());
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(role);
            admin.setRoles(roles);

            //员工部门设置
            Department department = departmentMapper.getDepartmentById(position.getDepartmentId());
            admin.setDepartmentId(department.getId());
            admin.setDepartment(department);
            admin.setDepartmentName(department.getName());
            admin.setParentId(Long.valueOf(department.getDepManager()));

            //员工工资账套设置
            Salary salary = salaryMapper.getSalaryById(department.getSalaryId());
            admin.setSalaryId(salary.getId());
            admin.setSalary(salary);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            admin.setPassword(encoder.encode("123"));

            if (1 == adminMapper.addUser(admin)){
                adminRoleMapper.addAdminWithRole(admin.getId(), admin.getRoles().get(0).getId());
            }
            return RespBean.success("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error("添加失败");
        }
    }
}
