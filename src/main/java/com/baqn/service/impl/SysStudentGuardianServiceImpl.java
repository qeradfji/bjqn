package com.baqn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysStudentGuardian;
import com.baqn.mapper.SysStudentGuardianMapper;
import com.baqn.service.ISysStudentGuardianService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学生监护人表 服务实现类
 * </p>
 *
 * @author bao
 * @since 2025-01-12
 */
@Service
public class SysStudentGuardianServiceImpl extends ServiceImpl<SysStudentGuardianMapper, SysStudentGuardian> implements ISysStudentGuardianService {

  @Override
  public IPage<SysStudentGuardian> getGuardiansWithStudentName(IPage<SysStudentGuardian> page, String name) {
    // 将 IPage 转换为 Page
    Page<SysStudentGuardian> convertedPage = (Page<SysStudentGuardian>) page;
    return baseMapper.selectGuardiansWithStudentName(convertedPage, name);
  }


  @Override
  public List<SysStudentGuardian> getGuardiansByStudentId(Long studentId) {
    return baseMapper.selectGuardiansByStudentId(studentId);
  }

  @Override
  public boolean saveGuardian(SysStudentGuardian sysStudentGuardian) {
    return save(sysStudentGuardian);
  }

  @Override
  public boolean updateGuardian(SysStudentGuardian sysStudentGuardian) {
    return updateById(sysStudentGuardian);
  }

  @Override
  public boolean deleteGuardianById(Long id) {
    return removeById(id);
  }

  @Override
  public IPage<SysStudentGuardian> getGuardiansByStudentName(Page<SysStudentGuardian> page, String studentName) {
    return baseMapper.selectGuardiansByStudentName(page, studentName);
  }
}
