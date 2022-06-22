package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author liyongkang
 * @Date 2022/1/6
 **/
@TableName("t_type_list")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "t_type_list实体类", description = "")
public class TypeList {

    @ApiModelProperty(value = "类型id")
    @TableId(value = "type_id", type = IdType.AUTO)
    private Long typeId;

    @ApiModelProperty(value = "类型颜色")
    private String typeColor;

    @ApiModelProperty(value = "所属模块")
    private String typeModel;

    @ApiModelProperty(value = "类型名字")
    private String typeName;

    @ApiModelProperty(value = "排序值")
    private Integer sortValue;

}
