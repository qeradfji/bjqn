package com.baqn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baqn.pojo.SysStudent;
import com.baqn.service.ISysStudentService;
import com.baqn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Api(tags = "学员管理")
public class SysStudentController {

  @Autowired
  private ISysStudentService iSysStudentService;

  @ApiOperation("分页查询")
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

  @ApiModelProperty("更新学员")
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

  @ApiOperation("添加学员")
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

  @ApiOperation("删除学员")
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

  @ApiOperation("分页查询根据班主任")
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

  /**
   * 根据ID查询学生
   */
  @ApiOperation("根据ID查询学生")
  @GetMapping("/get-by-id/{studentId}")
  public R getById(@PathVariable Long studentId) {
    try {
      if (studentId == null) {
        return R.error("studentId 不能为空");
      }
      SysStudent student = iSysStudentService.getById(studentId);
      if (student != null) {
        return R.ok().put("data", student);
      } else {
        return R.error("学生不存在");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("查询失败: " + e.getMessage());
    }
  }

  @ApiOperation("根据班主任统计学员人数")
  @GetMapping("/count-by-headteacher")
  public R countByHeadteacher(@RequestParam String headteacher) {
    try {
      int count = Math.toIntExact(iSysStudentService.countByHeadteacher(headteacher));
      Map<String, Object> result = new HashMap<>();
      result.put("headteacher", headteacher);
      result.put("studentCount", count);
      return R.ok().put("data", result);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("统计失败: " + e.getMessage());
    }
  }

  @ApiOperation("根据性别统计学员人数")
  @GetMapping("/count-by-sex")
  public R countBySex() {
    try {
      Map<String, Integer> sexCountMap = iSysStudentService.countBySex();
      return R.ok().put("data", sexCountMap);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("统计失败: " + e.getMessage());
    }
  }

  @ApiOperation("批量导入学生数据")
  @PostMapping("/import")
  public R importStudents(@RequestBody List<SysStudent> studentList) {
    try {
      Map<String, Object> importResult = iSysStudentService.importStudents(studentList);
      return R.ok().put("data", importResult);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("导入失败: " + e.getMessage());
    }
  }



}
