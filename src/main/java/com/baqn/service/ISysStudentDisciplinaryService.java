package com.baqn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baqn.pojo.SysStudentDisciplinary;

import java.util.List;
import java.util.Map;

public interface ISysStudentDisciplinaryService extends IService<SysStudentDisciplinary> {
  List<Map<String, Object>> getTypeCountByProcessor(String processor);
}
