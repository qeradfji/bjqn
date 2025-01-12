package com.baqn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
  import com.baqn.pojo.SysDepartment;
  import com.baqn.service.ISysDepartmentService;
  import com.baqn.util.R;
  import io.swagger.annotations.Api;
  import io.swagger.annotations.ApiOperation;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author bao
 * @since 2025-01-11
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/sys-department")
public class SysDepartmentController {

  @Autowired
  private ISysDepartmentService iSysDepartmentService;

  @ApiOperation("分页查询所有部门")
  @GetMapping("/list")
  public Page<SysDepartment> listDepartments(@RequestParam int current, @RequestParam int size) {
    Page<SysDepartment> page = new Page<>(current, size);
    return iSysDepartmentService.getDepartments(page);
  }

  @ApiOperation("删除部门")
  @DeleteMapping("/delete/{id}")
  public R deleteDepartment(@PathVariable Long id) {
    boolean result = iSysDepartmentService.removeById(id);
    return result ? R.ok("删除成功") : R.error("删除失败");
  }

  @ApiOperation("修改部门")
  @PutMapping("/update")
  public R updateDepartment(@RequestBody SysDepartment department) {
    boolean result = iSysDepartmentService.updateById(department);
    return result ? R.ok("修改成功") : R.error("修改失败");
  }

  @ApiOperation("添加部门")
  @PostMapping("/add")
  public R addDepartment(@RequestBody SysDepartment department) {
    boolean result = iSysDepartmentService.save(department);
    return result ? R.ok("添加成功") : R.error("添加失败");
  }

  @ApiOperation("统计部门数量")
  @GetMapping("/count")
  public R getDepartmentCount() {
    long count = iSysDepartmentService.getDepartmentCount();
    return R.ok().put("count", count);
  }
}
