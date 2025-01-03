package com.baqn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baqn.pojo.SysUser;
import com.baqn.mapper.SysUserMapper;
import com.baqn.response.UserResponse;
import com.baqn.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author bao
 * @since 2025-01-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

  @Autowired
  private SysUserMapper sysUserMapper;

  /**
   * 登录接口
   *
   * @param username
   * @param password
   * @return UserResponse 包含用户名和角色信息
   */
  @Override
  public UserResponse login(String username, String password) {
    // 1. md5加密获取到的密码
    String encryptedPassword = DigestUtils.md5Hex(password);

    // 2. 根据数据库进行对比
    QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    SysUser user = sysUserMapper.selectOne(queryWrapper);
    if (user == null || !user.getPassword().equals(encryptedPassword)) {
      throw new RuntimeException("用户名或密码错误");
    }

    // 3. 判断用户角色
    String role;
    switch (user.getPosition()) {
      case -1:
        role = "超级管理员";
        break;
      case 1:
        role = "班主任";
        break;
      default:
        role = "未知角色";
        break;
    }

    // 4. 返回用户名和角色信息
    return new UserResponse(user.getUsername(), role);
  }
}
