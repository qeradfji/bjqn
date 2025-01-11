package com.baqn.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学员表
 * </p>
 *
 * @author bao
 * @since 2025-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_student")
@ApiModel(value="SysStudent对象", description="学员表")
public class SysStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学员ID")
    @TableId(value = "student_id", type = IdType.AUTO)
    private Long studentId;

    @ApiModelProperty(value = "学号")
    private String studentNo;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态(1:在读,2:休学,3:退学,4:毕业)")
    private Integer status;

    @ApiModelProperty(value = "班级ID")
    private Long classId;

    @ApiModelProperty(value = "班主任ID")
    private Long teacherId;

    @ApiModelProperty(value = "入学时间")
    private LocalDate enrollmentDate;

    @ApiModelProperty(value = "毕业时间")
    private LocalDate graduationDate;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "修改人ID")
    private Long updateBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

    @ApiModelProperty(value = "微信号")
    private String wechat;

    @ApiModelProperty(value = "QQ号")
    private String qq;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "政治面貌")
    private String politicalStatus;

    @ApiModelProperty(value = "婚姻状况(1:未婚,2:已婚)")
    private Integer maritalStatus;

    @ApiModelProperty(value = "现居地址")
    private String address;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "班级")
    private String classe;

    @ApiModelProperty(value = "性别(1:男,2:女)")
    private Integer sex;

    @ApiModelProperty(value = "班主任")
    private String headteacher;


}
