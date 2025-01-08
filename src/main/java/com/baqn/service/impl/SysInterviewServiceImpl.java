package com.baqn.service.impl;

import com.baqn.pojo.SysInterview;
import com.baqn.mapper.SysInterviewMapper;
import com.baqn.service.ISysInterviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baqn.util.R;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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


  /**
   * 查询没有访谈的学生
   *
   * @param name
   * @param queryType
   */
  @Override
  public List<Map<String, Object>> getStudentsWithoutInterview(String name, String queryType) {
    log.info("Received name: {}, queryType: {}", name, queryType);
    // 获取没有访谈记录的学生ID列表
    List<Long> studentIds = baseMapper.selectStudentsWithoutInterview();

    // 根据查询条件过滤学生
    if (name != null && !name.isEmpty()) {
      if ("name".equals(queryType)) {
        log.info("Executing selectStudentsByNameAndIds with name: {}", name);
        return sysInterviewMapper.selectStudentsByNameAndIds(name, studentIds);
      } else {
        // 其他查询类型逻辑
        log.info("Unknown queryType: {}", queryType);
        return Collections.emptyList();
      }
    } else {
      // 如果没有查询条件，直接返回学生ID列表
      log.info("No query conditions provided, returning students by IDs");
      return sysInterviewMapper.selectStudentsByIds(studentIds);
    }
  }



}
