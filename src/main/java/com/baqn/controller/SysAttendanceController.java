package com.baqn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysAttendance;
import com.baqn.service.ISysAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys-attendance")
public class SysAttendanceController {

  @Autowired
  private ISysAttendanceService sysAttendanceService;

  @GetMapping("/page")
  public Page<SysAttendance> getAttendancePage(
    @RequestParam(defaultValue = "1") int current,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(required = false) String className) {
    Page<SysAttendance> page = new Page<>(current, size);
    QueryWrapper<SysAttendance> queryWrapper = new QueryWrapper<>();

    if (className != null && !className.isEmpty()) {
      queryWrapper.like("sys_class.name", className);
    }
    return sysAttendanceService.page(page, queryWrapper);
  }
}
