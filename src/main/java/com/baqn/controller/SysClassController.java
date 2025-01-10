package com.baqn.controller;

import com.baqn.pojo.SysClass;
import com.baqn.service.ISysClassService;
import com.baqn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>
 * 班级表 前端控制器
 * </p>
 *
 * @author bao
 * @since 2025-01-09
 */
@RestController
@RequestMapping("/sys-class")
@Api(tags = "班级管理")
public class SysClassController {

  @Autowired
  private ISysClassService iSysClassService;

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
}
