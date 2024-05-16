package com.cyd.login.controller;

import com.cyd.api.login.domain.dto.InitDto;
import com.cyd.api.login.domain.vo.InitVo;
import com.cyd.common.result.GeneralResult;
import com.cyd.login.service.InitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {

    @Resource
    private InitService initService;

    @PostMapping(name = "初始化")
    public GeneralResult<InitDto> init(@RequestBody InitVo vo) {
        return GeneralResult.success(initService.init(vo));
    }
}
