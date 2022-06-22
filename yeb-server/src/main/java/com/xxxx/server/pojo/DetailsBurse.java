package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author liyongkang
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_detailsburse")
@ApiModel(value="DetailsBurse对象", description="")
public class DetailsBurse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detailsburse_id", type = IdType.AUTO)
    private Long detailsburseId;

    @TableField("descript")
    @ApiModelProperty("费用说明")
    private String descript;

    @TableField("produce_time")
    @ApiModelProperty("报销金额")
    private Double detailmoney;

    @TableField("invoices")
    @ApiModelProperty("票据张数")
    private Integer invoices;

    @TableField("produce_time")
    @ApiModelProperty("费用产生时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date produceTime;

    @TableField("subject")
    @ApiModelProperty("费用产生科目")
    private String subject;

    @TableField("bursment_id")
    @ApiModelProperty("报销表中对应id")
    private Long bursmentId;


}
