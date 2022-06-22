package com.xxxx.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xxxx.server.config.CustomAuthorityDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 *
 * @author liyongkang
 * @since 2021-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_admin")
@ApiModel(value="Admin对象", description="")
public class Admin implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    @Excel(name = "工号")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    @Excel(name = "员工姓名")
    private String name;

    @ApiModelProperty(value = "手机号码")
    @Excel(name = "手机号码")
    private String phone;

    @ApiModelProperty(value = "住宅电话")
    private String telephone;

    @ApiModelProperty(value = "联系地址")
    @Excel(name = "联系地址")
    private String address;

    @ApiModelProperty(value = "是否启用")
    @Getter(AccessLevel.NONE)
    private Boolean enabled;

    @ApiModelProperty(value = "用户名")
    @Excel(name = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户头像")
    private String userFace;

    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty(value = "上司Id")
    private Long parentId;

    @ApiModelProperty(value = "邮箱")
    @Excel(name = "邮箱")
    private String email;

    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty(value = "职位id")
    private Integer posId;

    @ApiModelProperty(value = "职位")
    @TableField(exist = false)
    @Excel(name = "职位")
    private String posName;

    @ApiModelProperty(value = "主管")
    @TableField(exist = false)
    @Excel(name = "主管")
    private String parentName;

    @ApiModelProperty(value = "所属部门Id")
    private Integer departmentId;

    @ApiModelProperty(value = "部门")
    @TableField(exist = false)
    private Department department;

    @ApiModelProperty(value = "部门名称")
    @Excel(name = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "工资账套ID")
    private Integer salaryId;

    @ApiModelProperty(value = "工资账套")
    @Excel(name = "工资账套")
    @TableField(exist = false)
    private String salaryName;

    @ApiModelProperty(value = "工资账套")
    @TableField(exist = false)
    private Salary salary;

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
