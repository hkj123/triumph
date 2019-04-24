package com.accumulate.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * 用户表
 */
@SuppressWarnings("serial")   //抑制编译告警
@Data
@TableName(value = "test_user")
public class User extends SuperEntity<User> {
    /**
     * 名称
     */
    @TableField("name")
    private String name;
}
