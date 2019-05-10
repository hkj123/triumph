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
import java.util.Date;

/**
 * @System: mg-mall
 * @Auther: hukj
 * @Description: 资源实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "资源实体类")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "resource")
public class Resource extends SuperEntity<Resource> {
    /**
     * 父节点id,一级节点为空
     */
    @ApiModelProperty(value = "父节点id", notes = "一级节点为空")
    @TableField(value = "reso_pid")
    private Long resoPid;
    /**
     * 系统名称
     */
    @NotNull
    @ApiModelProperty(value = "系统名称", notes = "系统名称")
    @TableField(value = "reso_sysname")
    private String resoSysname;
    /**
     * 资源初始名称
     */
    @NotNull
    @ApiModelProperty(value = "资源名称")
    @TableField(value = "reso_init_name")
    private String resoInitName;
    /**
     * 资源名称
     */
    @NotNull
    @ApiModelProperty(value = "资源名称")
    @TableField(value = "reso_name")
    private String resoName;
    /**
     * 资源码
     */
    @ApiModelProperty(value = "资源码", notes = "资源码")
    @TableField(value = "reso_code")
    private String resoCode;
    /**
     * 资源级别
     */
    @ApiModelProperty(value = "资源级别", notes = "资源级别")
    @TableField(value = "reso_level")
    private Integer resoLevel;
    /**
     * 资源状态  0 启用 1 停用
     */
    @ApiModelProperty(value = "资源状态", notes = "0 启用 1 停用")
    @TableField(value = "reso_status")
    private Integer resoStatus;
    /**
     * 资源路径
     */
    @ApiModelProperty(value = "资源路径", notes = "资源路径")
    @TableField(value = "reso_path")
    private String resoPath;
    /**
     * 资源初始图标
     */
    @ApiModelProperty(value = "资源初始图标", notes = "资源初始图标")
    @TableField(value = "reso_init_icon")
    private String resoInitIcon;
    /**
     * 资源图标
     */
    @ApiModelProperty(value = "资源图标", notes = "资源图标")
    @TableField(value = "reso_icon")
    private String resoIcon;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", notes = "类型")
    @TableField(value = "reso_type")
    private Integer resoType;
    /**
     * 资源文件类型 421 菜单 422 目录 423 按钮 424 输入框 425 列表
     */
    @ApiModelProperty(value = "资源文件类型", notes = "421 菜单 422 目录 423 按钮 424 输入框 425 列表")
    @TableField(value = "reso_filetype")
    private Integer resoFiletype;
    @ApiModelProperty(value = "颜色", notes = "颜色")
    @TableField(value = "color")
    private String color;
    @ApiModelProperty(value = "排序", notes = "排序")
    @TableField(value = "sort")
    private Integer sort;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", notes = "备注")
    @TableField(value = "reso_remark")
    private String resoRemark;
    /**
     * 数据库排序标识
     */
    @ApiModelProperty(value = "数据库排序标识", notes = "数据库排序标识")
    @TableField(value = "flag")
    private Integer flag;
    @ApiModelProperty(notes = "操作员")
    @TableField(value = "operator")
    private Long operator;
    @ApiModelProperty(notes = "操作时间")
    @TableField(value = "operate_time")
    private Date operateTime;
}
