package com.baqn.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 班级表
 * </p>
 *
 * @author bao
 * @since 2025-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_class")
@ApiModel(value="SysClass对象", description="班级表")
public class SysClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "班级ID")
    @TableId(value = "class_id", type = IdType.AUTO)
    private Long classId;

    @ApiModelProperty(value = "班级名称")
    private String name;

    @ApiModelProperty(value = "课程类型(1:Java开发,2:Web前端,3:Python开发,4:大数据,5:人工智能)")
    private Integer courseType;

    @ApiModelProperty(value = "班主任ID")
    private Long teacherId;

    @ApiModelProperty(value = "开班时间")
    private LocalDate startDate;

    @ApiModelProperty(value = "结课时间")
    private LocalDate endDate;

    @ApiModelProperty(value = "状态(1:在训,2:结课)")
    private Integer status;

    @ApiModelProperty(value = "班级描述")
    private String description;

    @ApiModelProperty(value = "是否删除(0:未删除,1:已删除)")
    private Boolean deleted;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "修改人ID")
    private Long updateBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
