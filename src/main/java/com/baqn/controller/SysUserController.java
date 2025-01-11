package com.baqn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baqn.pojo.SysUser;
import com.baqn.pojo.dto.LoginDTO;
import com.baqn.response.UserResponse;
import com.baqn.service.ISysUserService;
import com.baqn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author bao
 * @since 2025-01-03
 */
@RestController
@RequestMapping("/sys-user")
@Api(tags = "用户管理")
public class SysUserController {

  @Autowired
  private ISysUserService iSysUserService;

  @ApiOperation("登录")
  @PostMapping("/login")
  public R login(@RequestBody LoginDTO loginDTO) {
    try {
      UserResponse userResponse = iSysUserService.login(loginDTO.getUsername(), loginDTO.getPassword());
      return R.ok().put("data", userResponse).put("realName", userResponse.getRealName());
    } catch (RuntimeException e) {
      return R.error(e.getMessage());
    }
  }

  /*
  根据id查询用户
   */
  @ApiOperation("根据ID查询用户")
  @GetMapping("/get-by-id/{userId}")
  public R getById(@PathVariable Long userId) {
    try {
      if (userId == null) {
        return R.error("userId 不能为空");
      }
      SysUser user = iSysUserService.getById(userId);
      if (user != null) {
        return R.ok().put("data", user);
      } else {
        return R.error("用户不存在");
      }
   } catch (Exception e) {
      e.printStackTrace();
      return R.error("查询失败: " + e.getMessage());
    }
  }



  @ApiOperation("根据 real_name 模糊查询用户并支持分页")
  @GetMapping("/page-by-real-name")
  public R getUsersByRealName(
    @RequestParam int current,
    @RequestParam int size,
    @RequestParam(required = false) String realName) {
    Page<SysUser> page = new Page<>(current, size);
    Page<SysUser> userPage = iSysUserService.getUsersByRealName(page, realName);
    return R.ok().put("data", userPage);
  }



  @ApiOperation("添加用户")
  @PostMapping("/add")
  public R addUser(@RequestBody SysUser sysUser) {
    boolean result = iSysUserService.addUser(sysUser);
    if (result) {
      return R.ok().put("message", "用户添加成功");
    } else {
      return R.error("用户添加失败");
    }
  }

  @ApiOperation("修改用户")
  @PutMapping("/update")
  public R updateUser(@RequestBody SysUser sysUser) {
    boolean result = iSysUserService.updateUser(sysUser);
    if (result) {
      return R.ok().put("message", "用户修改成功");
    } else {
      return R.error("用户修改失败");
    }
  }

  @ApiOperation("删除用户（逻辑删除）")
  @DeleteMapping("/delete/{id}")
  public R deleteUser(@PathVariable Long id) {
    boolean result = iSysUserService.deleteUser(id);
    if (result) {
      return R.ok().put("message", "用户删除成功");
    } else {
      return R.error("用户删除失败");
    }
  }


}
