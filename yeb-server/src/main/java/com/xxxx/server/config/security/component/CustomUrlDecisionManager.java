package com.xxxx.server.config.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.Collection;

/**
 * 权限控制
 * 判断用户角色
 *
 * @Author liyongkang
 * @Date 2021/12/10
 **/

@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            //当前 url 所需要的角色
            String needRole = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)){
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登录，请登录！");
                }else {
                    return;
                }
            }
            //判断用户角色是否为rul所需要角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}