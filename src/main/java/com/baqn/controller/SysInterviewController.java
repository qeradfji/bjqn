package com.baqn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysInterview;
import com.baqn.pojo.SysStudent;
import com.baqn.service.ISysInterviewService;
import com.baqn.service.ISysStudentService;
import com.baqn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 访谈记录表 前端控制器
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
@RestController
@RequestMapping("/sys-interview")
@Api(tags = "访谈记录表")
public class SysInterviewController {

  private static final Logger logger = LoggerFactory.getLogger(SysInterviewController.class);

  @Autowired
  private ISysInterviewService iSysInterviewService;
  @Autowired
  private ISysStudentService iStudentService;

  @ApiOperation("查询没有访谈的学生，支持多条件查询")
  @GetMapping("/students-without-interview")
  public R getStudentsWithoutInterview(@RequestParam(required = false) String name, @RequestParam(required = false) String classId, @RequestParam(required = false) Long teacherId) {
    try {
      List<Map<String, Object>> students = iSysInterviewService.getStudentsWithoutInterview(name, classId, teacherId);
      return R.ok().put("students", students);
    } catch (Exception e) {
      logger.error("查询没有访谈的学生失败", e);
      return R.error("查询没有访谈的学生失败");
    }
  }

  @ApiOperation("分页查询没有访谈的学生，支持多条件查询")
  @GetMapping("/students-without-interview-page")
  public R getStudentsWithoutInterviewPage(
    @RequestParam(defaultValue = "1") Integer pageNum,
    @RequestParam(defaultValue = "10") Integer pageSize,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String classId,
    @RequestParam(required = false) Long teacherId) {
    try {
      Page<Map<String, Object>> page = new Page<>(pageNum, pageSize);
      Page<Map<String, Object>> resultPage = iSysInterviewService.getStudentsWithoutInterviewPage(page, name, classId, teacherId);
      return R.ok().put("records", resultPage.getRecords()).put("total", resultPage.getTotal());
    } catch (Exception e) {
      logger.error("分页查询没有访谈的学生失败", e);
      return R.error("分页查询没有访谈的学生失败");
    }
  }

  /**
   * 删除访谈记录
   */
  @ApiOperation("删除访谈记录")
  @DeleteMapping("/delete/{interviewId}")
  public R deleteInterview(@PathVariable Long interviewId) {
    try {
      boolean result = iSysInterviewService.removeById(interviewId);
      if (result) {
        return R.ok("删除成功");
      } else {
        return R.error("删除失败");
      }
    } catch (Exception e) {
      logger.error("删除访谈记录失败", e);
      return R.error("删除访谈记录失败");
    }
  }

  /**
   * 添加访谈记录
   */
  @ApiOperation("添加访谈记录")
  @PostMapping("/add")
  public R addInterview(@RequestBody SysInterview sysInterview) {
    try {
      boolean result = iSysInterviewService.save(sysInterview);
      if (result) {
        return R.ok("添加成功");
      } else {
        return R.error("添加失败");
      }
    } catch (Exception e) {
      logger.error("添加访谈记录失败", e);
      return R.error("添加访谈记录失败");
    }
  }

  /**
   * 修改访谈记录
   */
  @ApiOperation("修改访谈记录")
  @PutMapping("/update")
  public R updateInterview(@RequestBody SysInterview sysInterview) {
    try {
      boolean result = iSysInterviewService.updateInterview(sysInterview);
      if (result) {
        return R.ok("修改成功");
      } else {
        return R.error("修改失败");
      }
    } catch (Exception e) {
      logger.error("修改访谈记录失败", e);
      return R.error("修改访谈记录失败");
    }
  }
}
