package com.xxxx.server.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import javafx.scene.control.Pagination;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页配置
 * @Author liyongkang
 * @Date 2021/12/13
 **/
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
