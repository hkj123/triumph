package com.accumulate.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @System: mg-mall
 * @Auther: hukj
 * @Description: 角色实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "角色实体类")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "role")
public class Role extends SuperEntity<Role> {
    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名", notes = "角色名")
    @TableField(value = "name")
    private String name;
    /**
     * 角色状态 424 启用 425 停用
     */
    @ApiModelProperty(value = "角色状态", notes = "0 启用 1 停用")
    @TableField(value = "status")
    private Integer status;
    /**
     * 备注
     */
    @Size(max = 800, message = "备注不能超过800字符")
    @ApiModelProperty(value = "备注", notes = "备注")
    @TableField(value = "remark")
    private String remark;
//    /**
//     * 角色的权限信息
//     */
//    @Transient
//    @ApiModelProperty(value = "角色的权限信息", notes = "角色的权限信息")
//    private List<Permission> roleResource;

    @ApiModelProperty(notes = "操作员")
    @TableField(value = "operator")
    private Long operator;
    @ApiModelProperty(notes = "操作时间")
    @TableField(value = "operatetime")
    private Date operatetime;

    /**
     * 角色状态
     */
    public enum Status {
        // 0-启用 1-禁用
        ENABLE(0), DISABLE(1);
        private Integer status;

        Status(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }
}
