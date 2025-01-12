package com.baqn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysStudentGuardian;
import com.baqn.service.ISysStudentGuardianService;
import com.baqn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学生监护人表 前端控制器
 * </p>
 *
 * @author bao
 * @since 2025-01-12
 */
@Api(tags = "学生监护人管理")
@RestController
@RequestMapping("/sys-student-guardian")
public class SysStudentGuardianController {

  @Autowired
  private ISysStudentGuardianService iSysStudentGuardianService;

  @ApiOperation("分页查询监护人并模糊查询名字，连表查询学生名字")
  @GetMapping("/list")
  public R listGuardians(@RequestParam int current, @RequestParam int size, @RequestParam(required = false) String name) {
    Page<SysStudentGuardian> page = new Page<>(current, size);
    IPage<SysStudentGuardian> guardianPage = iSysStudentGuardianService.getGuardiansWithStudentName(page, name);
    return R.ok().put("data", guardianPage);
  }

  @ApiOperation("根据学生名字查询监护人")
  @GetMapping("/list-by-student-name")
  public R listGuardiansByStudentName(@RequestParam int current, @RequestParam int size, @RequestParam(required = false) String studentName) {
    Page<SysStudentGuardian> page = new Page<>(current, size);
    IPage<SysStudentGuardian> guardianPage = iSysStudentGuardianService.getGuardiansByStudentName(page, studentName);
    return R.ok().put("data", guardianPage);
  }

  @ApiOperation("根据学生ID查询监护人")
  @GetMapping("/list-by-student-id")
  public R listGuardiansByStudentId(@RequestParam Long studentId) {
    List<SysStudentGuardian> guardians = iSysStudentGuardianService.getGuardiansByStudentId(studentId);
    return R.ok().put("data", guardians);
  }

  @ApiOperation("添加监护人")
  @PostMapping("/add")
  public R addGuardian(@RequestBody SysStudentGuardian sysStudentGuardian) {
    boolean result = iSysStudentGuardianService.saveGuardian(sysStudentGuardian);
    return R.ok();
  }

  @ApiOperation("修改监护人")
  @PutMapping("/update")
  public R updateGuardian(@RequestBody SysStudentGuardian sysStudentGuardian) {
    boolean result = iSysStudentGuardianService.updateGuardian(sysStudentGuardian);
    return R.ok();
  }

  @ApiOperation("删除监护人")
  @DeleteMapping("/delete/{id}")
  public R deleteGuardian(@PathVariable Long id) {
    boolean result = iSysStudentGuardianService.deleteGuardianById(id);
    return R.ok();
  }
}
