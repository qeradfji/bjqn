package com.baqn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysInterview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ISysInterviewService extends IService<SysInterview> {
  /**
   * 根据学生名字和/或老师ID模糊查询访谈记录，并关联 student、class 和 teacher 表
   *
   * @param studentName 学生名字（可选）
   * @param teacherId 教师ID（可选）
   * @param page 分页对象
   * @return 分页结果
   */
  Page<SysInterview> getInterviewsByStudentNameAndTeacherId(String studentName, Long teacherId, Page<SysInterview> page);
  boolean updateInterview(SysInterview sysInterview);
}
