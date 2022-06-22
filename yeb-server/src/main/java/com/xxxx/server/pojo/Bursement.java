package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_bursement")
@ApiModel(value="Bursement对象", description="")
@ToString
public class Bursement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bursement_id", type = IdType.AUTO)
    @ApiModelProperty(value = "报销表主键Id")
    private Long bursementId;

    @TableField("all_money")
    @ApiModelProperty(value = "报销总金额")
    private Double allMoney;

    @ApiModelProperty(value = "票据总数")
    private Integer allinvoices;

    @TableField("burse_time")
    @ApiModelProperty(value = "报销日期")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date burseTime;

    @TableField("financial_advice")
    @ApiModelProperty(value = "财务经理意见")
    private String financialAdvice;

    @TableField("manager_advice")
    @ApiModelProperty(value = "部门经理意见")
    private String managerAdvice;

    @TableField("name")
    @ApiModelProperty(value = "客户名")
    private String name;

    @TableField("type_id")
    @ApiModelProperty(value = "报销方式")
    private Long typeId;

    @TableField("operation_name")
    @ApiModelProperty(value = "报销人员")
    private Long operationName;

    @TableField("pro_id")
    @ApiModelProperty(value = "流程表id")
    private Long proId;

    @TableField("user_name")
    @ApiModelProperty(value = "证明人")
    private Long userName;

    private String shenUser;//审核人员

    //报销明细
    List<DetailsBurse> details;

}
