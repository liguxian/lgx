package com.cyd.login.controller;

import com.cyd.api.login.domain.dto.LoginDto;
import com.cyd.api.login.domain.vo.LoginVo;
import com.cyd.common.result.GeneralResult;
import com.cyd.login.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping(name = "用户登录")
    public GeneralResult<LoginDto> toLogin(@RequestBody @Validated LoginVo loginVo) {
        return GeneralResult.success(userService.toLogin(loginVo));
    }
}
