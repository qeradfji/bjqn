package com.baqn.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 访谈记录表
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_interview")
@ApiModel(value="SysInterview对象", description="访谈记录表")
public class SysInterview implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "访谈ID")
    @TableId(value = "interview_id", type = IdType.AUTO)
    private Long interviewId;

    @ApiModelProperty(value = "学生ID")
    private Long studentId;

    @ApiModelProperty(value = "班级ID")
    private Long classId;

    @ApiModelProperty(value = "班主任ID")
    private Long teacherId;

    @ApiModelProperty(value = "访谈日期")
    private LocalDate interviewDate;

    @ApiModelProperty(value = "访谈对象(1:学员,2:监护人)")
    private Integer interviewType;

    @ApiModelProperty(value = "访谈内容")
    private String content;

    @ApiModelProperty(value = "反馈建议")
    private String feedback;

    @ApiModelProperty(value = "状态(1:正常,2:需要关注)")
    private Integer status;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标记")
    private Integer deleted;


    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private String className;

    @TableField(exist = false)
    private String teacherName;

}
