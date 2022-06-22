package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_resign")
@ToString
@ApiModel(value="Resign对象", description="")
public class Resign implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "resign_id", type = IdType.AUTO)
    private Long resignId;

    @TableField("financial_advice")
    @ApiModelProperty("财务部经理意见")
    private String financialAdvice;

    @TableField("is_finish")
    @ApiModelProperty("是否还有费用报销未完成")
    private Boolean isFinish;

    @TableField("nofinish")
    @ApiModelProperty("未完成的工作")
    private String nofinish;

    @TableField("personnel_advice")
    @ApiModelProperty("人事部意见")
    private String personnelAdvice;

    @TableField("suggest")
    @ApiModelProperty("对公司或部门的建议")
    private String suggest;

    @TableField("hand_user")
    @ApiModelProperty("工作交接人")
    private Integer handUser;

    @TableField("pro_id")
    private Long proId;

    @TableField("manager_advice")
    @ApiModelProperty("部门经理意见")
    private String managerAdvice;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty(value = "流程紧急程度")
    private Long deeplyId;

    @ApiModelProperty(value = "流程申请人id")
    private Long processUserId;

    @ApiModelProperty(value = "审核人")
    private String  shenUser;

    @ApiModelProperty(value = "流程申请原因内容")
    private String processDes;

    //工作交接人姓名
    String name;

}
