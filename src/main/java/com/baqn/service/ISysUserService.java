package com.baqn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
   *
   * @param username
   * @param password
   */
  UserResponse login(String username, String password);

  /**
   * 分页查询用户
   * @param page
   * @return
   */
  Page<SysUser> getUsers(Page<SysUser> page);

  /**
   * 添加用户
   * @param sysUser
   * @return
   */
  boolean addUser(SysUser sysUser);

  /**
   * 修改用户
   * @param sysUser
   * @return
   */
  boolean updateUser(SysUser sysUser);

  /**
   * 删除用户
   * @param id
   * @return
   */
  boolean deleteUser(Long id);
}
