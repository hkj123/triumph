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
 * @Description: 数据字典项实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "数据字典项实体类")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "data_dict_type")
public class DataDictType implements java.io.Serializable {
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
    @TableField(value = "code")
    private String code;
    /**
     * 数据字典项名称
     */
    @ApiModelProperty(value = "数据字典项名称", notes = "数据字典项名称")
    @TableField(value = "name")
    private String name;
    /**
     * 数据字典项类型  0 业务类型  1 非业务类型
     */
    @ApiModelProperty(value = "数据字典状态", notes = " 0 业务类型  1 非业务类型")
    @TableField(value = "type")
    private Integer type;
}
