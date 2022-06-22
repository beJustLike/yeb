package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_status_list")
@ApiModel(value = "StatusList对象", description = "")
public class StatusList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态id")
    @TableId(value = "status_id", type = IdType.AUTO)
    private Long statusId;

    @ApiModelProperty(value = "状态颜色")
    @TableField("status_color")
    private String statusColor;

    @ApiModelProperty(value = "状态模块")
    @TableField("status_model")
    private String statusModel;

    @ApiModelProperty(value = "状态名称")
    @TableField("status_name")
    private String statusName;

    @ApiModelProperty(value = "状态排序值")
    @TableField("sort_value")
    private Integer sortValue;

    @ApiModelProperty(value = "百分比")
    @TableField("sort_precent")
    private String sortPrecent;


}
