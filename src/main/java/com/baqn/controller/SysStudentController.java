package com.baqn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysStudent;
import com.baqn.service.ISysStudentService;
import com.baqn.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学员表 前端控制器
 * </p>
 *
 * @author bao
 * @since 2025-01-03
 */
@RestController
@RequestMapping("/sys-student")
public class SysStudentController {

  @Autowired
  private ISysStudentService iSysStudentService;

  @GetMapping("/list")
  public R list(
    @RequestParam(defaultValue = "1") Long currentPage,
    @RequestParam(defaultValue = "10") Long pageSize,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String gender,
    @RequestParam(required = false) Integer age) {
    try {
      Page<SysStudent> page = iSysStudentService.listByStudent(currentPage, pageSize, name, gender, age);
      return R.ok().put("data", page);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("查询失败: " + e.getMessage());
    }
  }

  @PutMapping("/update")
  public R update(@RequestBody SysStudent sysStudent) {
    try {
      if (sysStudent.getStudentId() == null) {
        return R.error("studentId 不能为空");
      }
      boolean result = iSysStudentService.updateStudent(sysStudent);
      if (result) {
        return R.ok().put("message", "更新成功");
      } else {
        return R.error("更新失败");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("更新失败: " + e.getMessage());
    }
  }

  @PostMapping("/add")
  public R add(@RequestBody SysStudent sysStudent) {
    try {
      boolean result = iSysStudentService.saveStudent(sysStudent);
      if (result) {
        return R.ok().put("message", "添加成功");
      } else {
        return R.error("添加失败");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("添加失败: " + e.getMessage());
    }
  }

  @DeleteMapping("/delete/{studentId}")
  public R delete(@PathVariable Long studentId) {
    try {
      if (studentId == null) {
        return R.error("studentId 不能为空");
      }
      boolean result = iSysStudentService.deleteStudent(studentId);
      if (result) {
        return R.ok().put("message", "删除成功");
      } else {
        return R.error("删除失败");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("删除失败: " + e.getMessage());
    }
  }

  @GetMapping("/list-by-headteacher")
  public R listByHeadteacher(
    @RequestParam(defaultValue = "1") Long currentPage,
    @RequestParam(defaultValue = "10") Long pageSize,
    @RequestParam String headteacher,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String gender,
    @RequestParam(required = false) Integer age) {
    try {
      Page<SysStudent> page = iSysStudentService.listByHeadteacher(currentPage, pageSize, headteacher, name, gender, age);
      return R.ok().put("data", page);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("查询失败: " + e.getMessage());
    }
  }
}
