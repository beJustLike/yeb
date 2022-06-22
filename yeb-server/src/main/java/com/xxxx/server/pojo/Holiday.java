package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author liyongkang
 * @Date 2022/1/5
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_holiday")
@ApiModel(value = "请假表", description = "")
@ToString
public class Holiday implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "请假表id")
    @TableId(value = "holiday_id", type = IdType.AUTO)
    private Long holidayId;

    @ApiModelProperty(value = "请假类型,如事假、年假等")
    private Long typeId;

    @ApiModelProperty(value = "流程表id")
    private Long proId;

    @ApiModelProperty(value = "请假天数")
    private Integer leaveDays;

    @ApiModelProperty(value = "经理意见及说明")
    private String managerAdvice;

    @ApiModelProperty(value = "人事部意见及说明")
    private String personnelAdvice;
}
