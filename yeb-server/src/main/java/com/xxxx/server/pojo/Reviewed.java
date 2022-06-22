package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author liyongkang
 * @Date 2022/1/5
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_reviewed")
@ApiModel(value = "审核表", description = "")
@ToString
public class Reviewed implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "请假表id")
    @TableId(value = "reviewed_id", type = IdType.AUTO)
    private Long reviewedId;

    @ApiModelProperty(value = "流程id")
    private Long proId;

    @ApiModelProperty(value = "流程状态")
    private Long statusId;

    @ApiModelProperty(value = "审核人id")
    private Integer userId;

    @ApiModelProperty(value = "是否删除")
    private Integer del;

    @ApiModelProperty(value = "审核意见")
    private String advice;

    @ApiModelProperty(value = "流程申请时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date reviewedTime;

    String name;
}
