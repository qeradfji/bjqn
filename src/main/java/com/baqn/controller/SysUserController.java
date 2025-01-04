package com.baqn.controller;

import com.baqn.pojo.dto.LoginDTO;
import com.baqn.response.UserResponse;
import com.baqn.service.ISysUserService;
import com.baqn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      return R.ok().put("data", userResponse);
    } catch (RuntimeException e) {
      return R.error(e.getMessage());
    }
  }
}
