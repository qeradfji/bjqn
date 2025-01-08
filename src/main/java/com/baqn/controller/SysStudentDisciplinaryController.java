package com.baqn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysStudentDisciplinary;
import com.baqn.service.ISysStudentDisciplinaryService;
import com.baqn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys-student-disciplinary")
@Api(tags = "违纪表")
public class SysStudentDisciplinaryController {

  private static final Logger logger = LoggerFactory.getLogger(SysStudentDisciplinaryController.class);

  @Autowired
  private ISysStudentDisciplinaryService iSysStudentDisciplinaryService;

  /**
   * 根据班主任姓名和其他条件进行分页查询违纪记录
   */
  @ApiOperation("根据班主任姓名和其他条件进行分页查询违纪记录")
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

  /**
   * 添加违纪记录
   */
  @ApiOperation("添加违纪记录")
  @PostMapping("/add")
  public R addDisciplinary(@RequestBody SysStudentDisciplinary sysStudentDisciplinary) {
    try {
      boolean result = iSysStudentDisciplinaryService.save(sysStudentDisciplinary);
      if (result) {
        return R.ok("添加成功");
      } else {
        return R.error("添加失败");
      }
    } catch (Exception e) {
      logger.error("添加违纪记录失败", e);
      return R.error("添加违纪记录失败");
    }
  }

  /**
   * 修改违纪记录
   */
  @ApiOperation("修改违纪记录")
  @PutMapping("/update")
  public R updateDisciplinary(@RequestBody SysStudentDisciplinary sysStudentDisciplinary) {
    try {
      boolean result = iSysStudentDisciplinaryService.updateById(sysStudentDisciplinary);
      if (result) {
        return R.ok("修改成功");
      } else {
        return R.error("修改失败");
      }
    } catch (Exception e) {
      logger.error("修改违纪记录失败", e);
      return R.error("修改违纪记录失败");
    }
  }

  /**
   * 删除违纪记录
   */
  @ApiOperation("删除违纪记录")
  @DeleteMapping("/delete/{id}")
  public R deleteDisciplinary(@PathVariable Integer id) {
    try {
      boolean result = iSysStudentDisciplinaryService.removeById(id);
      if (result) {
        return R.ok("删除成功");
      } else {
        return R.error("删除失败");
      }
    } catch (Exception e) {
      logger.error("删除违纪记录失败", e);
      return R.error("删除违纪记录失败");
    }
  }

  /**
   * 根据老师姓名统计不同违规类型的数量
   */
  @ApiOperation("根据老师姓名统计不同违规类型的数量")
  @GetMapping("/type-count-by-processor")
  public R getTypeCountByProcessor(@RequestParam(required = false) String processor) {
    try {
      List<Map<String, Object>> typeCountList = iSysStudentDisciplinaryService.getTypeCountByProcessor(processor);
      return R.ok().put("typeCount", typeCountList);
    } catch (Exception e) {
      logger.error("统计违规类型数量失败", e);
      return R.error("统计违规类型数量失败");
    }
  }


}
