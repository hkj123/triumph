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
 * @Description: 角色资源中间表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "角色资源中间表实体类")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "role_resource")
public class RoleResource extends SuperEntity<RoleResource> {
    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id", notes = "资源id")
    @TableField(value = "resource_id")
    private Long resourceId;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", notes = "角色id")
    @TableField(value = "role_id")
    private Long roleId;
}
