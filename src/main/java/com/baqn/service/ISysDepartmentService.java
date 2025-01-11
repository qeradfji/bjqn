package com.baqn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysDepartment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author bao
 * @since 2025-01-11
 */
public interface ISysDepartmentService extends IService<SysDepartment> {
  Page<SysDepartment> getDepartments(Page<SysDepartment> page);
}
