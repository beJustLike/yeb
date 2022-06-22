package com.xxxx.server.controller;

import com.xxxx.server.pojo.Admin;
import com.xxxx.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 在线聊天
 *
 * @Author liyongkang
 * @Date 2021/12/15
 **/
@RestController
@RequestMapping("/chat")
public class chatController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "查询所有聊天人员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(@RequestParam(value = "keywords", required = false) String keywords) {
        return adminService.getAllAdmins(keywords);
    }
}
