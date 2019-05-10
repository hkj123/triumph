package com.accumulate.business.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginModel {
    /**
     * 411 密码登录 412 二维码登录 413 人脸识别登录
     */
    @ApiModelProperty(value = "登录类型", notes = "411 密码登录 412 二维码登录 413 人脸识别登录")
    private Integer type;
    /**
     * 登录设备类型  414 pc端 415 app端
     */
    @ApiModelProperty(value = "登录设备类型", notes = "414 pc端 415 app端 ")
    private Integer logindevice;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", notes = "用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", notes = "密码")
    private String password;

    /**
     * 登录设备编号
     */
    @ApiModelProperty(value = "设备编号", notes = "设备编号")
    private String code;
}
