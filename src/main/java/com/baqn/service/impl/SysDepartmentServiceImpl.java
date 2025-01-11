package com.baqn.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysDepartment;
import com.baqn.mapper.SysDepartmentMapper;
import com.baqn.service.ISysDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author bao
 * @since 2025-01-11
 */
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements ISysDepartmentService {

  @Override
  public Page<SysDepartment> getDepartments(Page<SysDepartment> page) {
    return page(page);
  }
}
