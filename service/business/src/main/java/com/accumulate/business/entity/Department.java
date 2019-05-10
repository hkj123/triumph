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
 * @Description: 机构实体类
 */
@SuppressWarnings("serial")   //抑制编译告警
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "机构实体类")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "department")
public class Department extends SuperEntity<Department> {
    /**
     * 机构所属的公司id
     */
    @ApiModelProperty(value = "机构所属的公司id", notes = "机构所属的公司id")
    @TableField(value = "company_id")
    private Long companyId;
    /**
     * 机构父 id
     */
    @ApiModelProperty(value = "机构父 id", notes = "机构父 id")
    @TableField(value = "dept_pid")
    private Long deptPid;
    /**
     * 机构名称
     */
    @NotNull
    @ApiModelProperty(value = "机构名称", notes = "")
    @TableField(value = "dept_name")
    private String deptName;
    /**
     * 机构类型
     */
    @ApiModelProperty(value = "机构类型", notes = "")
    @TableField(value = "dept_type")
    private String deptType;
    /**
     * 机构编码
     */
    @NotNull
    @ApiModelProperty(value = "机构编码", notes = "机构编码")
    @TableField(value = "dept_code")
    private String deptCode;
    /**
     * 机构等级 401 -- 408 一 -- 八 级机构
     */
    @ApiModelProperty(value = "机构等级", notes = "401 -- 408 一 -- 八 级机构")
    @TableField(value = "dept_level")
    private Integer deptLevel;
    /**
     * 机构状态 422 启用  423 停用
     */
    @NotNull
    @ApiModelProperty(value = "机构状态", notes = "0 启用  1 停用")
    @TableField(value = "dept_status")
    private Integer deptStatus;
    /**
     * 机构备注
     */
    @ApiModelProperty(value = "机构备注", notes = "机构备注")
    @TableField(value = "dept_remark")
    private String deptRemark;

    @ApiModelProperty(notes = "操作员")
    @TableField(value = "operator")
    private Long operator;
    @ApiModelProperty(notes = "操作时间")
    @TableField(value = "operate_time")
    private Date operateTime;

    /**
     * 机构状态
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
