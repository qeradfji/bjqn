package com.baqn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysClass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 班级表 服务类
 * </p>
 *
 * @author bao
 * @since 2025-01-09
 */
public interface ISysClassService extends IService<SysClass> {
  /**
   * 分页查询并且可以根据name值模糊查询
   * @param page
   * @param name
   * @return
   */
  Page<SysClass> getClassesByName(Page<SysClass> page, String name);
}
