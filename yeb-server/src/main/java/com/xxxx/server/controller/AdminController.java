package com.xxxx.server.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IAdminService;
import com.xxxx.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @Resource
    private IRoleService roleService;

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords) {
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "分页获取所有员工")
    @GetMapping("/getAllEmp")
    public RespPageBean getAllEmp(@RequestParam(defaultValue = "1") Long rows,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(defaultValue = "10") Long pageSize) {
        return adminService.getAllEmp(name, rows, pageSize);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation(value = "更新员工角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        return adminService.updateAdminRole(adminId, rids);
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/add")
    public RespBean addUser(@RequestBody Admin admin) {
        return adminService.addUser(admin);
    }

    @ApiOperation(value = "导出用户数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) {
        List<Admin> allAdmins = adminService.getAllAdmins(null);
        ExportParams params = new ExportParams("用户表", "用户表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Admin.class, allAdmins);
        ServletOutputStream outputStream = null;

        try {
            //流形式传输
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" +
                    URLEncoder.encode("用户表.xls", "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
