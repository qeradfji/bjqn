package com.baqn.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 违纪表
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_student_disciplinary")
@ApiModel(value="SysStudentDisciplinary对象", description="违纪表")
public class SysStudentDisciplinary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "违纪人 ")
    private String name;

    @ApiModelProperty(value = "违纪时间")
    private LocalDate timeOfInfraction;

    @ApiModelProperty(value = "违纪描述 ")
    private String descriptionOfTheIndiscipline;

    @ApiModelProperty(value = "处理方式 ")
    private String processing;

    @ApiModelProperty(value = "1-已处理 2-待处理")
    private Integer processingStatus;

    @ApiModelProperty(value = "班级 ")
    private String classe;

    @ApiModelProperty(value = "班主任 ")
    private String headteacher;

    @ApiModelProperty(value = "处理人")
    private String processors;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "处理时间")
    private LocalDate processingTime;

    @ApiModelProperty(value = "违纪类型")
    private String type;


}
