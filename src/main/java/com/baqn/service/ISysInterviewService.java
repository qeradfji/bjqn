package com.baqn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baqn.pojo.SysInterview;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 访谈记录表 服务类
 * </p>
 *
 * @author bao
 * @since 2025-01-04
 */
public interface ISysInterviewService extends IService<SysInterview> {


  /**
   * 查询没有访谈的学生
   */
  List<Map<String, Object>> getStudentsWithoutInterview(String name, String queryType);
}
