package com.baqn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baqn.mapper.SysStudentDisciplinaryMapper;
import com.baqn.mapper.SysStudentMapper;
import com.baqn.pojo.SysStudentDisciplinary;
import com.baqn.service.ISysStudentDisciplinaryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 违纪表 服务实现类
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
@Service
public class SysStudentDisciplinaryServiceImpl extends ServiceImpl<SysStudentDisciplinaryMapper, SysStudentDisciplinary> implements ISysStudentDisciplinaryService {

  private SysStudentDisciplinaryMapper sysStudentDisciplinaryMapper;

  @Override
  public List<Map<String, Object>> getTypeCountByProcessor(String processor) {
    QueryWrapper<SysStudentDisciplinary> queryWrapper = new QueryWrapper<>();
    if (processor != null && !processor.isEmpty()) {
      queryWrapper.eq("processors", processor);
    }
    queryWrapper.select("type", "COUNT(*) as count")
      .groupBy("type");
    return listMaps(queryWrapper);
  }




}
