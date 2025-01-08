package com.baqn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysInterview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ISysInterviewService extends IService<SysInterview> {

  List<Map<String, Object>> getStudentsWithoutInterview(String name, String classId, Long teacherId);
  Page<Map<String, Object>> getStudentsWithoutInterviewPage(Page<Map<String, Object>> page, String name, String classId, Long teacherId);
  boolean updateInterview(SysInterview sysInterview);
}
