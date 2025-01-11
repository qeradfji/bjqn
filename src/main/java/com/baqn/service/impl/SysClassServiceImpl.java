package com.baqn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysClass;
import com.baqn.mapper.SysClassMapper;
import com.baqn.service.ISysClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 班级表 服务实现类
 * </p>
 *
 * @author bao
 * @since 2025-01-09
 */
@Service
public class SysClassServiceImpl extends ServiceImpl<SysClassMapper, SysClass> implements ISysClassService {

  /**
   * 分页查询并且可以根据name值模糊查询
   *
   * @param page
   * @param name
   * @return
   */
  @Override
  public Page<SysClass> getClassesByName(Page<SysClass> page, String name) {
    QueryWrapper<SysClass> queryWrapper = new QueryWrapper<>();
    if (name != null && !name.isEmpty()) {
      queryWrapper.like("name", name);
    }
    return page(page, queryWrapper);
  }
}
