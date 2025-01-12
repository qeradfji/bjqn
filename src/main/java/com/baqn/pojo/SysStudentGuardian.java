package com.baqn.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 学生监护人表
 * </p>
 *
 * @author bao
 * @since 2025-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_student_guardian")
@ApiModel(value="SysStudentGuardian对象", description="学生监护人表")
public class SysStudentGuardian implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "监护人ID")
    @TableId(value = "guardian_id", type = IdType.AUTO)
    private Long guardianId;

    @ApiModelProperty(value = "学生ID")
    private Long studentId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "关系")
    private String relationship;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "微信号")
    private String wechat;

    @ApiModelProperty(value = "职业")
    private String occupation;

    @ApiModelProperty(value = "工作单位")
    private String workUnit;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 新增 studentName 字段
    @TableField(exist = false)
    private String studentName;




}
