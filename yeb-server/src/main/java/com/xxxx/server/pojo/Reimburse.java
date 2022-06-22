package com.xxxx.server.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author liyongkang
 * @Date 2022/1/27
 **/
@Data
@AllArgsConstructor
@ToString
public class Reimburse {

    @ApiModelProperty("流程标题")
    String title;

    @ApiModelProperty("紧急程度")
    Long deeplyId;

    @ApiModelProperty("流程申请人")
    Long userId;

    @ApiModelProperty("证明人")
    Long userName;

    @ApiModelProperty("报销方式")
    Long typeId;

    @ApiModelProperty("客户名")
    String name;

    @ApiModelProperty("审核人")
    String shenUser;

    @ApiModelProperty("报销事由")
    String processDes;

    @ApiModelProperty("报销明细")
    List<DetailsBurse> list;
}
