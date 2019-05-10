package com.accumulate.business.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {
    private static final long serialVersionUID = 5194933845448697148L;

    @ApiModelProperty(value = "查询整型")
    private Integer selectInt;
    @ApiModelProperty(value = "查询字符串")
    private String selectStr;

    public MyPage() {
    }

    public MyPage(long current, long size) {
        super(current, size);
    }
}
