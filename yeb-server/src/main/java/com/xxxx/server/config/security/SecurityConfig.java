package com.xxxx.server.config.security;

import com.xxxx.server.config.security.component.*;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.xxxx.server.config.security.component.JwtAuthenticationTokenFilter;

/**
 * Security配置类
 */
//WebSecurityConfigurerAdapter 类是个适配器,
// 在配置的时候,需要我们自己写个配置类去继承他,然后编写自己所特殊需要的配置
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private CustomFilter customFilter;

    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;

    //身份验证管理生成器,登录验证的时候让JWT使用重写后的UserDetailsService
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //重写这个方法是因为，让登录的时候请求走自己重写的登录方法 UserDetailsService userDetailsService()
        //userDetailsService() 获取了用户名
        //passwordEncoder(passwordEncoder())密码匹配是通过BCryptPasswordEncoder加密来完成的
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        //auth.userDetailsService(userDetailsService()).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                //放行的资源路径，此配置不经过Security拦截器
                "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "/index.html",
                "/favicon.ico",
                "/doc.html",
                "/captcha",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/ws/**"
        );
    }

    //Spring Security完整的配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用jwt不需要csrf
        http.csrf()
                .disable()//关闭csrf，关闭后面and之前的配置
                //基于token存储登录用户信息，不需要session，关闭session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //所有请求都要求认证
                .anyRequest()
                .authenticated()
                //动态权限，获取不同菜单列表
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(customFilter);
                        return o;
                    }
                })
                .and()
                //禁用缓存
                .headers()
                .cacheControl();

        //添加 jwt 登录授权拦截器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义 未授权 和 未登录 结果返回
        http.exceptionHandling()
                //自定义 未授权
                .accessDeniedHandler(restfulAccessDeniedHandler)
                //自定义 未登录结果返回
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }

    //重写获取用户的方法
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Admin admin = adminService.getAdminByUserName(username);
            if (admin != null) {
                admin.setRoles(adminService.getRoles(admin.getId()));
                return admin;
            }
            throw new UsernameNotFoundException("用户名或密码不正确！");
        };
    }

    //注入BCryptPasswordEncoder，实现密码的加密和解密
    @Bean
    public PasswordEncoder passwordEncoder() {
        //Security中默认的密码实现  BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }

    //Bean注入，JWT登录授权拦截器
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

}
