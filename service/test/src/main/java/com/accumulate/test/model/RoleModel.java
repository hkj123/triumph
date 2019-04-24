package com.accumulate.test.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by  hukaijia.
 * Description:
 * Date: 2017-08-16-11:37
 */
@Data
public class RoleModel {
    @ApiModelProperty("特定公司的标识")
    private String companyCode;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色状态 0：启用 1：停用")
    private Integer status;
    @ApiModelProperty("描述")
    private String remark;
    @ApiModelProperty("创建人用户名")
    private String operator;
    @ApiModelProperty("创建时间")
    private Date operateTime;
}
