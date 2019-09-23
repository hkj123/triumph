package com.springboot.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @System: mg-mall
 * @Auther: hukj
 * @Description: 用户实体类
 */
@SuppressWarnings("serial")   //抑制编译告警
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User{
    /**
     * 主键ID , 这里故意演示注解可以无
     */
    private Long id;
    /**
     * 用户名
     */
    @NotNull
    @Size(min = 6, max = 50, message = "用户名长度不能小于6位大于50位")
    private String username;
    /**
     * 真实姓名
     */
    @NotNull
    @Pattern(regexp = "^([\\u4e00-\\u9fa5]+|([a-zA-Z]+\\s?)+)$", message = "姓名只能汉字或者英文")
    @Size(min = 1, max = 50, message = "姓名不能超过五十个字符")
    private String realname;

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

}
