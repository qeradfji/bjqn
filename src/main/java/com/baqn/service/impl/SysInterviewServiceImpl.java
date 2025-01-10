package com.baqn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baqn.mapper.SysInterviewMapper;
import com.baqn.mapper.SysStudentMapper;
import com.baqn.pojo.SysInterview;
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

  /**
   * 根据学生名字和/或老师ID模糊查询访谈记录，并关联 student、class 和 teacher 表
   *
   * @param studentName 学生名字（可选）
   * @param teacherId   教师ID（可选）
   * @param page        分页对象
   * @return 分页结果
   */
  @Override
  public Page<SysInterview> getInterviewsByStudentNameAndTeacherId(String studentName, Long teacherId, Page<SysInterview> page) {
    // 调用自定义查询方法
    Page<SysInterview> resultPage = sysInterviewMapper.getInterviewsByStudentNameAndTeacherId(page, studentName, teacherId);
    log.info("Query result: {}", resultPage.getRecords());
    return resultPage;
  }

  @Override
  public boolean updateInterview(SysInterview sysInterview) {
    // 设置 update_time 为当前时间
    sysInterview.setUpdateTime(LocalDateTime.now());
    return sysInterviewMapper.updateById(sysInterview) > 0;
  }
}
