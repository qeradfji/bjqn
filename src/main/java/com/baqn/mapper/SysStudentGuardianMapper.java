package com.baqn.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysStudentGuardian;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 学生监护人表 Mapper 接口
 * </p>
 *
 * @author bao
 * @since 2025-01-12
 */
public interface SysStudentGuardianMapper extends BaseMapper<SysStudentGuardian> {

  @Select("SELECT sg.guardian_id, sg.student_id, sg.name, sg.phone, s.name AS student_name, " +
    "sg.relationship, sg.wechat, sg.occupation, sg.work_unit, sg.address, sg.deleted, sg.create_by, sg.update_by, sg.create_time, sg.update_time " +
    "FROM sys_student_guardian sg " +
    "LEFT JOIN sys_student s ON sg.student_id = s.student_id " +
    "WHERE (#{name} IS NULL OR sg.name LIKE CONCAT('%', #{name}, '%')) " +
    "AND sg.deleted = 0 " +
    "ORDER BY sg.guardian_id")
  IPage<SysStudentGuardian> selectGuardiansWithStudentName(IPage<SysStudentGuardian> page, @Param("name") String name);

  @Select("SELECT sg.guardian_id, sg.student_id, sg.name, sg.phone, s.name AS student_name, " +
    "sg.relationship, sg.wechat, sg.occupation, sg.work_unit, sg.address, sg.deleted, sg.create_by, sg.update_by, sg.create_time, sg.update_time " +
    "FROM sys_student_guardian sg " +
    "LEFT JOIN sys_student s ON sg.student_id = s.student_id " +
    "WHERE (#{studentName} IS NULL OR s.name LIKE CONCAT('%', #{studentName}, '%')) " +
    "AND sg.deleted = 0 " +
    "ORDER BY sg.guardian_id")
  IPage<SysStudentGuardian> selectGuardiansByStudentName(IPage<SysStudentGuardian> page, @Param("studentName") String studentName);

  @Select("SELECT * FROM sys_student_guardian WHERE student_id = #{studentId} AND deleted = 0")
  List<SysStudentGuardian> selectGuardiansByStudentId(@Param("studentId") Long studentId);

}
