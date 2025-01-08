package com.baqn.service;

import com.baqn.pojo.SysInterview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ISysInterviewService extends IService<SysInterview> {

  List<Map<String, Object>> getStudentsWithoutInterview(String name, String classId, Long teacherId);
  boolean updateInterview(SysInterview sysInterview);
}
