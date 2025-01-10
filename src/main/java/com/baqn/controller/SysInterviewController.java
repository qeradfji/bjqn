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


  /**
   * 根据学生名字和/或老师ID模糊查询访谈记录，并关联 student、class 和 teacher 表
   */
  @ApiOperation("根据学生名字和/或老师ID模糊查询访谈记录，并关联 student、class 和 teacher 表")
  @GetMapping("/list-by-student-name-and-teacher-id")
  public R listInterviewsByStudentNameAndTeacherId(
    @RequestParam(required = false) String studentName,
    @RequestParam(required = false) Long teacherId,
    @RequestParam int current,
    @RequestParam int size) {
    Page<SysInterview> page = new Page<>(current, size);
    Page<SysInterview> resultPage = iSysInterviewService.getInterviewsByStudentNameAndTeacherId(studentName, teacherId, page);
    return R.ok().put("data", resultPage);
  }



}
