package com.baqn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baqn.mapper.SysInterviewMapper;
import com.baqn.mapper.SysStudentMapper;
import com.baqn.pojo.SysInterview;
import com.baqn.pojo.SysStudent;
import com.baqn.service.ISysInterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 访谈记录表 服务实现类
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
@Service
@Slf4j
public class SysInterviewServiceImpl extends ServiceImpl<SysInterviewMapper, SysInterview> implements ISysInterviewService {

  @Autowired
  private SysInterviewMapper sysInterviewMapper;

  @Autowired
  private SysStudentMapper sysStudentMapper;

  @Override
  public List<Map<String, Object>> getStudentsWithoutInterview(String name, String classId, Long teacherId) {
    // 使用 MyBatis-Plus 的 QueryWrapper 构造查询条件
    QueryWrapper<SysInterview> interviewWrapper = new QueryWrapper<>();
    interviewWrapper.isNull("student_id");

    // 获取没有访谈记录的学生 ID 列表
    List<Long> studentIds = sysInterviewMapper.selectObjs(interviewWrapper)
      .stream()
      .map(obj -> (Long) obj)
      .collect(Collectors.toList());

    // 构建学生查询条件
    QueryWrapper<SysStudent> studentWrapper = new QueryWrapper<>();
    // 检查 studentIds 是否为空，如果不为空则添加 notIn 条件
    if (!studentIds.isEmpty()) {
      studentWrapper.notIn("student_id", studentIds);
    }
    if (name != null && !name.isEmpty()) {
      studentWrapper.like("name", name);
    }
    if (classId != null && !classId.isEmpty()) {
      studentWrapper.eq("class_id", classId);
    }

    // 获取符合条件的学生列表
    List<SysStudent> students = sysStudentMapper.selectList(studentWrapper);

    // 构建根据 teacherId 查询的条件
    QueryWrapper<SysInterview> teacherInterviewWrapper = new QueryWrapper<>();
    teacherInterviewWrapper.eq("teacher_id", teacherId);

    // 获取属于特定教师的访谈记录
    List<SysInterview> teacherInterviews = sysInterviewMapper.selectList(teacherInterviewWrapper);

    // 获取属于特定教师的学生 ID 列表
    List<Long> teacherStudentIds = teacherInterviews.stream()
      .map(SysInterview::getStudentId)
      .filter(Objects::nonNull)
      .collect(Collectors.toList());

    // 进一步筛选出属于特定教师的学生
    List<SysStudent> filteredStudents = students.stream()
      .filter(student -> teacherStudentIds.isEmpty() || teacherStudentIds.contains(student.getStudentId()))
      .collect(Collectors.toList());

    // 构建结果
    List<Map<String, Object>> result = filteredStudents.stream().map(student -> {
      Map<String, Object> studentInfo = new HashMap<>();
      studentInfo.put("studentId", student.getStudentId());
      studentInfo.put("studentName", student.getName());
      // 添加其他需要的字段
      // 查找该学生的访谈记录
      SysInterview interview = teacherInterviews.stream()
        .filter(intv -> Objects.equals(intv.getStudentId(), student.getStudentId()))
        .findFirst()
        .orElse(null);

      if (interview != null) {
        studentInfo.put("interviewDate", interview.getInterviewDate());
        studentInfo.put("interviewType", interview.getInterviewType());
        studentInfo.put("content", interview.getContent());
        studentInfo.put("feedback", interview.getFeedback());
        studentInfo.put("status", interview.getStatus());
        studentInfo.put("createBy", interview.getCreateBy());
        studentInfo.put("createTime", interview.getCreateTime());
      }

      return studentInfo;
    }).collect(Collectors.toList());

    return result;
  }

  @Override
  public Page<Map<String, Object>> getStudentsWithoutInterviewPage(Page<Map<String, Object>> page, String name, String classId, Long teacherId) {
    // 使用 MyBatis-Plus 的 QueryWrapper 构造查询条件
    QueryWrapper<SysInterview> interviewWrapper = new QueryWrapper<>();
    interviewWrapper.isNull("student_id");

    // 获取没有访谈记录的学生 ID 列表
    List<Long> studentIds = sysInterviewMapper.selectObjs(interviewWrapper)
      .stream()
      .map(obj -> (Long) obj)
      .collect(Collectors.toList());

    // 构建学生查询条件
    QueryWrapper<SysStudent> studentWrapper = new QueryWrapper<>();
    // 检查 studentIds 是否为空，如果不为空则添加 notIn 条件
    if (!studentIds.isEmpty()) {
      studentWrapper.notIn("student_id", studentIds);
    }
    if (name != null && !name.isEmpty()) {
      studentWrapper.like("name", name);
    }
    if (classId != null && !classId.isEmpty()) {
      studentWrapper.eq("class_id", classId);
    }

    // 创建一个正确的分页对象
    Page<SysStudent> studentPage = new Page<>(page.getCurrent(), page.getSize());

    // 获取符合条件的学生列表
    studentPage = sysStudentMapper.selectPage(studentPage, studentWrapper);

    // 构建根据 teacherId 查询的条件
    QueryWrapper<SysInterview> teacherInterviewWrapper = new QueryWrapper<>();
    teacherInterviewWrapper.eq("teacher_id", teacherId);

    // 获取属于特定教师的访谈记录
    List<SysInterview> teacherInterviews = sysInterviewMapper.selectList(teacherInterviewWrapper);

    // 获取属于特定教师的学生 ID 列表
    List<Long> teacherStudentIds = teacherInterviews.stream()
      .map(SysInterview::getStudentId)
      .filter(Objects::nonNull)
      .collect(Collectors.toList());

    // 进一步筛选出属于特定教师的学生
    List<SysStudent> filteredStudents = studentPage.getRecords().stream()
      .filter(student -> teacherStudentIds.isEmpty() || teacherStudentIds.contains(student.getStudentId()))
      .collect(Collectors.toList());

    // 构建结果
    List<Map<String, Object>> result = filteredStudents.stream().map(student -> {
      Map<String, Object> studentInfo = new HashMap<>();
      studentInfo.put("studentId", student.getStudentId());
      studentInfo.put("studentName", student.getName());
      // 添加其他需要的字段
      // 查找该学生的访谈记录
      SysInterview interview = teacherInterviews.stream()
        .filter(intv -> Objects.equals(intv.getStudentId(), student.getStudentId()))
        .findFirst()
        .orElse(null);

      if (interview != null) {
        studentInfo.put("interviewDate", interview.getInterviewDate());
        studentInfo.put("interviewType", interview.getInterviewType());
        studentInfo.put("content", interview.getContent());
        studentInfo.put("feedback", interview.getFeedback());
        studentInfo.put("status", interview.getStatus());
        studentInfo.put("createBy", interview.getCreateBy());
        studentInfo.put("createTime", interview.getCreateTime());
      }

      return studentInfo;
    }).collect(Collectors.toList());

    // 设置分页结果
    Page<Map<String, Object>> resultPage = new Page<>(studentPage.getCurrent(), studentPage.getSize(), studentPage.getTotal());
    resultPage.setRecords(result);
    return resultPage;
  }


  @Override
  public boolean updateInterview(SysInterview sysInterview) {
    // 设置 update_time 为当前时间
    sysInterview.setUpdateTime(LocalDateTime.now());
    return sysInterviewMapper.updateById(sysInterview) > 0;
  }
}
