package com.baqn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysStudentGuardian;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学生监护人表 服务类
 * </p>
 *
 * @author bao
 * @since 2025-01-12
 */
public interface ISysStudentGuardianService extends IService<SysStudentGuardian> {

  // 添加方法
  boolean saveGuardian(SysStudentGuardian sysStudentGuardian);

  // 修改方法
  boolean updateGuardian(SysStudentGuardian sysStudentGuardian);

  // 删除方法
  boolean deleteGuardianById(Long id);


  // 根据学生名字查询监护人
  IPage<SysStudentGuardian> getGuardiansByStudentName(Page<SysStudentGuardian> page, String studentName);


  IPage<SysStudentGuardian> getGuardiansWithStudentName(IPage<SysStudentGuardian> page, String name);


  // 根据学生ID查询监护人
  List<SysStudentGuardian> getGuardiansByStudentId(Long studentId);

}
