package com.xxxx.server.utils;

import com.xxxx.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 操作员工具类
 * @Author liyongkang
 * @Date 2021/12/13
 **/
public class AdminUtils {

    //获取当前登录操作员
    public static Admin getCurrentAdmin(){
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
