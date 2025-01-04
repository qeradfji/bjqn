package com.baqn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysStudentDisciplinary;
import com.baqn.service.ISysStudentDisciplinaryService;
import com.baqn.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 违纪表 前端控制器
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
@RestController
@RequestMapping("/sys-student-disciplinary")
public class SysStudentDisciplinaryController {

  private static final Logger logger = LoggerFactory.getLogger(SysStudentDisciplinaryController.class);

  @Autowired
  private ISysStudentDisciplinaryService iSysStudentDisciplinaryService;

  /**
   * 根据班主任姓名和其他条件进行分页查询违纪记录
   */
  @GetMapping("/page")
  public R pageQuery(
    @RequestParam(defaultValue = "1") Integer pageNum,
    @RequestParam(defaultValue = "10") Integer pageSize,
    @RequestParam(required = false) String processors, // 班主任姓名
    @RequestParam(required = false) String name,       // 学生姓名
    @RequestParam(required = false) String type,       // 违纪类型
    @RequestParam(required = false) String processingTime, // 处理时间
    @RequestParam(required = false) String timeOfInfraction // 违纪时间
  ) {
    Page<SysStudentDisciplinary> page = new Page<>(pageNum, pageSize);
    QueryWrapper<SysStudentDisciplinary> queryWrapper = new QueryWrapper<>();

    // 构建查询条件
    if (processors != null && !processors.isEmpty()) {
      queryWrapper.eq("processors", processors);
    }
    if (name != null && !name.isEmpty()) {
      queryWrapper.like("name", name);
    }
    if (type != null && !type.isEmpty()) {
      queryWrapper.eq("type", type);
    }
    if (processingTime != null && !processingTime.isEmpty()) {
      queryWrapper.eq("processing_time", processingTime);
    }
    if (timeOfInfraction != null && !timeOfInfraction.isEmpty()) {
      queryWrapper.eq("time_of_infraction", timeOfInfraction);
    }

    try {
      Page<SysStudentDisciplinary> result = iSysStudentDisciplinaryService.page(page, queryWrapper);
      return R.ok().put("records", result.getRecords()).put("total", result.getTotal());
    } catch (Exception e) {
      logger.error("查询失败", e);
      return R.error("查询失败");
    }
  }
}
