package com.baqn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysClass;
import com.baqn.service.ISysClassService;
import com.baqn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys-class")
@Api(tags = "班级管理")
public class SysClassController {

  @Autowired
  private ISysClassService iSysClassService;

  /*
  统计班级数量
   */
  @ApiOperation("统计班级数量")
  @GetMapping("/count")
  public R count() {
    try {
      long count = iSysClassService.count();
      return R.ok().put("data", count);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("查询失败: " + e.getMessage());
    }
  }
  @ApiOperation("根据ID查询班级信息")
  @GetMapping("/get-by-id/{classId}")
  public R getById(@PathVariable Long classId) {
    try {
      SysClass sysClass = iSysClassService.getById(classId);
      if (sysClass != null) {
        return R.ok().put("data", sysClass);
      } else {
        return R.error("班级不存在");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("查询失败: " + e.getMessage());
    }
  }

  @ApiOperation("新增班级信息")
  @PostMapping("/add")
  public R add(@RequestBody SysClass sysClass) {
    try {
      boolean saved = iSysClassService.save(sysClass);
      if (saved) {
        return R.ok("新增成功");
      } else {
        return R.error("新增失败");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("新增失败: " + e.getMessage());
    }
  }

  @ApiOperation("更新班级信息")
  @PutMapping("/update")
  public R update(@RequestBody SysClass sysClass) {
    try {
      boolean updated = iSysClassService.updateById(sysClass);
      if (updated) {
        return R.ok("更新成功");
      } else {
        return R.error("更新失败");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("更新失败: " + e.getMessage());
    }
  }

  @ApiOperation("删除班级信息")
  @DeleteMapping("/delete/{classId}")
  public R delete(@PathVariable Long classId) {
    try {
      boolean deleted = iSysClassService.removeById(classId);
      if (deleted) {
        return R.ok("删除成功");
      } else {
        return R.error("删除失败");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return R.error("删除失败: " + e.getMessage());
    }
  }

  @ApiOperation("分页查询班级并且可以根据name值进行模糊查询")
  @GetMapping("/page-by-name")
  public R getClassesByName(
    @RequestParam int current,
    @RequestParam int size,
    @RequestParam(required = false) String name) {
    Page<SysClass> page = new Page<>(current, size);
    Page<SysClass> classPage = iSysClassService.getClassesByName(page, name);
    return R.ok().put("data", classPage);
  }

}
