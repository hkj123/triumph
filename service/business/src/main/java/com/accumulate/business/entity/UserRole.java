package com.accumulate.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @System: mg-mall
 * @Auther: hukj
 * @Description: 用户角色实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户角色实体类")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "user_role")
public class UserRole extends SuperEntity<UserRole> {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", notes = "用户id")
   @TableField(value = "user_id")
    private Long userId;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", notes = "角色id")
   @TableField(value = "role_id")
    private Long roleId;
}
