package com.accumulate.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 演示实体父类
 */
@Data
@ApiModel
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID , 这里故意演示注解可以无
     */
    @TableId("id")
    @ApiModelProperty(value = "主键ID", notes = "主键ID")
    private Long id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
