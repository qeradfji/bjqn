package com.baqn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baqn.mapper.SysStudentMapper;
import com.baqn.pojo.SysStudent;
import com.baqn.service.ISysStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 学员表 服务实现类
 * </p>
 *
 * @author bao
 * @since 2025-01-03
 */
@Service
public class SysStudentServiceImpl extends ServiceImpl<SysStudentMapper, SysStudent> implements ISysStudentService {

  @Autowired
  private SysStudentMapper studentMapper;

  @Override
  public Page<SysStudent> listByStudent(Long currentPage, Long pageSize, String name, String gender, Integer age) {
    Page<SysStudent> page = new Page<>(currentPage, pageSize);
    QueryWrapper<SysStudent> queryWrapper = new QueryWrapper<>();
    if (name != null && !name.isEmpty()) {
      queryWrapper.like("name", name);
    }
    if (gender != null && !gender.isEmpty()) {
      queryWrapper.eq("gender", gender);
    }
    if (age != null) {
      queryWrapper.eq("age", age);
    }
    return page(page, queryWrapper);
  }

  @Override
  public boolean updateStudent(SysStudent sysStudent) {
    if (sysStudent.getStudentId() == null) {
      throw new IllegalArgumentException("studentId cannot be null");
    }
    QueryWrapper<SysStudent> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("student_id", sysStudent.getStudentId());
    queryWrapper.eq("deleted", 0);
    return update(sysStudent, queryWrapper);
  }

  @Override
  public boolean saveStudent(SysStudent sysStudent) {
    return save(sysStudent);
  }

  @Override
  public boolean deleteStudent(Long studentId) {
    if (studentId == null) {
      throw new IllegalArgumentException("studentId cannot be null");
    }
    QueryWrapper<SysStudent> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("student_id", studentId);
    queryWrapper.eq("deleted", 0);
    return remove(queryWrapper);
  }

  @Override
  public Page<SysStudent> listByHeadteacher(Long currentPage, Long pageSize, String headteacher, String name, String gender, Integer age) {
    if (headteacher == null || headteacher.isEmpty()) {
      throw new IllegalArgumentException("headteacher cannot be null or empty");
    }
    Page<SysStudent> page = new Page<>(currentPage, pageSize);
    QueryWrapper<SysStudent> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("headteacher", headteacher);
    if (name != null && !name.isEmpty()) {
      queryWrapper.like("name", name);
    }
    if (gender != null && !gender.isEmpty()) {
      queryWrapper.eq("gender", gender);
    }
    if (age != null) {
      queryWrapper.eq("age", age);
    }
    return page(page, queryWrapper);
  }

  @Override
  public Long countByHeadteacher(String headteacher) {
    QueryWrapper<SysStudent> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("headteacher", headteacher);
    return baseMapper.selectCount(queryWrapper);
  }

  @Override
  public Map<String, Integer> countBySex() {
    HashMap<String, Integer> sexCountMap = new HashMap<>();
    sexCountMap.put("male",studentMapper.countBySex(1));
    sexCountMap.put("female",studentMapper.countBySex(2));
    return sexCountMap;
  }


}
