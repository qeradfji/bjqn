package com.baqn.service;

import com.baqn.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baqn.response.UserResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author bao
 * @since 2025-01-03
 */
public interface ISysUserService extends IService<SysUser> {

  /**
   * 登录接口
   * @param username
   * @param password
   */
  UserResponse login(String username, String password);
}
