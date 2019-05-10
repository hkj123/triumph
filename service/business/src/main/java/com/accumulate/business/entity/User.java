package com.accumulate.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @System: mg-mall
 * @Auther: hukj
 * @Description: 用户实体类
 */
@SuppressWarnings("serial")   //抑制编译告警
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户实体类")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "user_information")
public class User extends SuperEntity<User> {
    /**
     * 用户类型  409 正式用户  410试用账户
     */
    @ApiModelProperty(value = "用户类型", notes = "409 正式用户  410试用账户")
    @TableField(value = "type")
    private Integer type;
    /**
     * 试用时长 402试用账户 单位:月
     */
    @ApiModelProperty(value = "试用时长", notes = "单位（月）")
    @TableField(value = "trialtime")
    private Integer trialtime;
    /**
     * 411 密码登录 412 二维码登录 413 人脸识别登录
     */

    @ApiModelProperty(value = "登录类型", notes = "411 密码登录 412 二维码登录 413 人脸识别登录")
    @TableField(value = "logintype")
    private Integer logintype;
    /**
     * 登录设备类型  414 pc端 415 app端
     */

    @ApiModelProperty(value = "登录设备类型", notes = "414 pc端 415 app端 ")
    @TableField(value = "logindevice")
    private Integer logindevice;
    /**
     * 登录地址
     */
    @ApiModelProperty(value = "登录地址", notes = "登录地址")
    @TableField(value = "loginaddress")
    private String loginaddress;
    /**
     * 密码过时时间
     */
    @ApiModelProperty(value = "密码过时时间", notes = "密码过时时间")
    @TableField(value = "passwordtime")
    private Date passwordtime;
    /**
     * 部门id
     */
    @NotNull
    @ApiModelProperty(value = "部门id", notes = "部门id")
    @TableField(value = "deptid")
    private Long deptid;
    /**
     * 用户绑定的消息推送id
     */
    @ApiModelProperty(value = "用户绑定的消息推送id", notes = "用户绑定的消息推送id")
    @TableField(value = "messagepushid")
    private String messagepushid;
    /**
     * 是否管理员 416 个人权限 417 部门权限 418 公司权限
     */
    @NotNull
    @ApiModelProperty(value = "是否管理员", notes = "416 个人权限 417 部门管理员 418 公司管理员")
    @TableField(value = "usermanager")
    private Integer usermanager;
    /**
     * 用户名
     */
    @NotNull
    @Size(min = 6, max = 50, message = "用户名长度不能小于6位大于50位")
    @ApiModelProperty(value = "用户名", notes = "用户名")
    @TableField(value = "username")
    private String username;
    /**
     * 真实姓名
     */
    @NotNull
    @Pattern(regexp = "^([\\u4e00-\\u9fa5]+|([a-zA-Z]+\\s?)+)$", message = "姓名只能汉字或者英文")
    @ApiModelProperty(value = "姓名", notes = "姓名")
    @Size(min = 1, max = 50, message = "姓名不能超过五十个字符")
    @TableField(value = "realname")
    private String realname;
    /**
     * 密码
     */
    @Size(min = 6, max = 64, message = "密码长度不能小于6位大于64位")
    @ApiModelProperty(value = "密码", notes = "密码")
    @TableField(value = "password")
    private String password;
    /**
     * 性别  419 男  420 女
     */
    @ApiModelProperty(value = "性别", notes = "419 男  420 女")
    @TableField(value= "sex")
    private Integer sex;
    /**
     * 电话
     */
    @Pattern(regexp = "[0-9-()（）]{7,18}", message = "电话格式错误")
    @ApiModelProperty(value = "手机号", notes = "手机号")
    @TableField(value= "phone")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", notes = "邮箱")
    @TableField(value= "email")
    private String email;
    /**
     * 用户状态 0 启用 1 停用
     */
    @ApiModelProperty(value = "用户状态", notes = "0 启用 1 停用")
    @TableField(value = "status")
    private Integer status;
    /**
     * 用户所属公司
     */
    @ApiModelProperty(value = "用户所属公司", notes = "用户所属公司")
    @TableField(value = "company")
    private String company;
    /**
     * 用户签名
     */
    @ApiModelProperty(value = "用户签名", notes = "用户签名")
    @TableField(value = "signature")
    private String signature;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", notes = "备注")
    @TableField(value= "remark")
    private String remark;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", notes = "用户头像")
    @TableField(value = "photo")
    private String photo;
//    /**
//     * 用户所属角色
//     */
//    @Transient
//    @ApiModelProperty(value = "角色名", notes = "角色名")
//    private String roleName;

    @ApiModelProperty(notes = "操作员")
    @TableField(value = "operator")
    private Long operator;
    @ApiModelProperty(notes = "操作时间")
    @TableField(value = "operatetime")
    private Date operatetime;

    /**
     * 用户状态
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

    public enum UserManager {
        //416 个人权限 417 部门权限 418 公司权限
        PERSONAL("个人权限", 416), DEPARTMENT("部门权限", 417), COMPANY("公司权限", 418);

        private String name;
        private Integer value;

        UserManager(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    public enum UserType {
        // 409 正式用户  410试用账户
        FORMAL(409), PRACTISE(410);
        private Integer userType;

        UserType(Integer userType) {
            this.userType = userType;
        }

        public Integer getUserType() {
            return userType;
        }
    }

}
