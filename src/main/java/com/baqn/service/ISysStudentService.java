package com.baqn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysStudent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学员表 服务类
 * </p>
 *
 * @author bao
 * @since 2025-01-03
 */
public interface ISysStudentService extends IService<SysStudent> {
  /**
   * 分页查询学员信息
   *
   * @param currentPage 当前页码
   * @param pageSize 每页大小
   * @param name 姓名（可为空）
   * @param gender 性别（可为空）
   * @param age 年龄（可为空）
   * @return 分页结果
   */
  Page<SysStudent> listByStudent(Long currentPage, Long pageSize, String name, String gender, Integer age);

  /**
   * 更新学员信息
   *
   * @param sysStudent 学员信息
   * @return 更新结果
   */
  boolean updateStudent(SysStudent sysStudent);

  /**
   * 添加学员信息
   *
   * @param sysStudent 学员信息
   * @return 添加结果
   */
  boolean saveStudent(SysStudent sysStudent);

  /**
   * 删除学员信息
   *
   * @param studentId 学员ID
   * @return 删除结果
   */
  boolean deleteStudent(Long studentId);

  /**
   * 分页查询学员信息，必须包含 headteacher 条件
   *
   * @param currentPage 当前页码
   * @param pageSize 每页大小
   * @param headteacher 班主任（必须条件）
   * @param name 姓名（可为空）
   * @param gender 性别（可为空）
   * @param age 年龄（可为空）
   * @return 分页结果
   */
  Page<SysStudent> listByHeadteacher(Long currentPage, Long pageSize, String headteacher, String name, String gender, Integer age);

}
