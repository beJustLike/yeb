package com.xxxx.server.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

/**
 * 在线聊天的消息实体
 * @Author liyongkang
 * @Date 2021/12/15
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {

    @ApiModelProperty(value = "消息发送者")
    private String from;

    @ApiModelProperty(value = "消息接收者")
    private String to;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息发送日期")
    private LocalDateTime date;

    @ApiModelProperty(value = "昵称")
    private String formNickName;

}
