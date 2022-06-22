package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2022-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_attachment_list")
@ApiModel(value="AttachmentList对象", description="")
public class AttachmentList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "attachment_id", type = IdType.AUTO)
    private Long attachmentId;

    @TableField("attachment_name")
    private String attachmentName;

    @TableField("attachment_path")
    private String attachmentPath;

    @TableField("attachment_shuffix")
    private String attachmentShuffix;

    @TableField("attachment_size")
    private String attachmentSize;

    @TableField("attachment_type")
    private String attachmentType;

    private String model;

    @TableField("upload_time")
    private LocalDateTime uploadTime;

    @TableField("user_id")
    private String userId;


}
