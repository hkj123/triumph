package com.accumulate.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description: 数据字典实体类
 */
@SuppressWarnings("serial")   //抑制编译告警
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "数据字典实体类")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "data_dict")
public class DataDict implements java.io.Serializable {
    /**
     * 主键id
     */
    @TableId("id")
    @ApiModelProperty(value = "主键ID", notes = "主键ID")
    private Integer id;
    /**
     * 数据字典项code
     */
    @ApiModelProperty(value = "数据字典项code", notes = "数据字典项code")
    @TableField(value = "type_code")
    private String typeCode;
    /**
     * 数据字典code
     */
    @ApiModelProperty(value = "数据字典code", notes = "数据字典code")
    @TableField(value = "code")
    private String code;
    /**
     * 数据字典名称
     */
    @ApiModelProperty(value = "数据字典名称", notes = "数据字典名称")
    @TableField(value = "name")
    private String name;
    /**
     * 数据字典排序
     */
    @ApiModelProperty(value = "数据字典排序", notes = "数据字典排序")
    @TableField(value = "sort")
    private Integer sort;
}
