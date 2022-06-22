package com.xxxx.server.config;

import com.xxxx.server.config.security.component.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.Resource;

/**
 * webSocket配置类
 * @Author liyongkang
 * @Date 2021/12/15
 **/
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 添加Endpoint端点，这样在网页可以通过websocket连接上服务
     * 配置websocket的服务地址，并且可以指定是否使用socketJS
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 1. addEndpoint("/ws/ep"):将ws/ep路径注册为stomp的端点，用户连接了这个端点可以进行websocket通信，支持socketJS
         * 2. setAllowedOrigins("*")：允许跨域
         * 3. withSockJS()：支持socketJS访问
         * 相当于前端传递消息到后端的路径
         */
        registry
                .addEndpoint("/ws/ep")
                .setAllowedOrigins("*")
                .withSockJS();

    }

    /**
     * 输入通道参数的配置
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        //interceptors()相当于一个拦截器的效果
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //判断是否为链接，如果是，需要获取token，并且设置到用户对象中
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    if (!StringUtils.isEmpty(token)){
                        String authToken = token.substring(tokenHead.length());
                        String userName = jwtTokenUtils.getUserNameFromToken(authToken);
                        //token存在用户名
                        if (!StringUtils.isEmpty(userName)){
                            //登录
                            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                            //验证token是否有效，有效则重新设置用户对象
                            if (jwtTokenUtils.validateToken(authToken, userDetails)){
                                UsernamePasswordAuthenticationToken authenticationToken =
                                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    /**
     * 配置消息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置代理域，可以配置多个，配置代理目的地前缀为/queue,可以在配置域上向客户端推送消息(相当于接收消息的地方)
        registry.enableSimpleBroker("/queue");
    }
}
