package com.xxxx.server.controller;

import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * websocket 在线聊天
 * @Author liyongkang
 * @Date 2021/12/15
 **/
@Controller
public class WsController {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handlerMsg(Authentication authentication, ChatMsg chatMsg){
        Admin admin = (Admin) authentication.getPrincipal();
        //登录的用户名
        chatMsg.setFrom(admin.getUsername());
        //显示的用户名
        chatMsg.setFormNickName(admin.getName());
        //设置消息日期
        chatMsg.setDate(LocalDateTime.now());
        /*
         * 发送消息
         * 1.消息接收者
         * 2.消息队列
         * 3.消息对象
         */
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
}
