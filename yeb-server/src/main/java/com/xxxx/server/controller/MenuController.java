package com.xxxx.server.controller;

import com.xxxx.server.pojo.Menu;
import com.xxxx.server.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liyongkang
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "通过用户ID获取菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByAdminId(){

        return menuService.getMenuByAdminId();
    }

}
