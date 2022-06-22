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
import java.util.List;

/**
 * @Author liyongkang
 * @Date 2022/1/5
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
@TableName("t_process_list")
@ApiModel(value = "流程主表", description = "")
public class ProcessList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程id")
    @TableId(value = "process_id", type = IdType.AUTO)
    private Long processId;

    @ApiModelProperty(value = "流程紧急程度")
    private Long deeplyId;

    @ApiModelProperty(value = "流程申请人id")
    private Long processUserId;

    @ApiModelProperty(value = "流程申请原因内容")
    private String processDes;

    @ApiModelProperty(value = "流程标题")
    private String processName;

    @ApiModelProperty(value = "流程总天数")
    private Integer processDays;

    @ApiModelProperty(value = "流程是否被驳回")
    private Integer isChecked;

    @ApiModelProperty(value = "流程审核状态id")
    private Long statusId;

    @ApiModelProperty(value = "流程类型id")
    private Long typeId;

    @ApiModelProperty(value = "流程申请类型")
    private String typeName;

    @ApiModelProperty(value = "流程审核人")
    private String shenUser;

    @ApiModelProperty(value = "流程申请时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date applyTime;

    @ApiModelProperty(value = "流程开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date startTime;

    @ApiModelProperty(value = "流程结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date endTime;

    @ApiModelProperty(value = "流程用户名")
    String name;

    @ApiModelProperty(value = "状态")
    String state;

    @ApiModelProperty(value = "紧急程度")
    String harry;

    @ApiModelProperty(value = "审核记录")
    List<Reviewed> records;

    @ApiModelProperty(value = "请假记录")
    Holiday holidayRecord;

    @ApiModelProperty(value = "报销记录")
    Bursement bursementRecord;

    @ApiModelProperty(value = "离职记录")
    Resign resignRecord;
}
