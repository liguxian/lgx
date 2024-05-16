package com.cyd.login.intercepter;

import cn.hutool.core.util.StrUtil;
import com.cyd.common.utils.EncryptUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class RequestIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //时间戳
        Object t = request.getAttribute("t");
//        if (ObjectUtil.isNull(t)) {
//            return false;
//        }

        //1.判断时间戳，如果大于3秒，直接返回错误
//        if (System.currentTimeMillis() - (Long) t > 3) {
//            return false;
//        }

        System.out.println("机内拦截器");
        Object deviceIdentifier = request.getAttribute("deviceIdentifier");
//        if (ObjectUtil.isNull(deviceIdentifier)) {
//            return false;
//        }
//
        Object deviceModel = request.getAttribute("deviceModel");
//        if (ObjectUtil.isNull(deviceModel)) {
//            return false;
//        }
//
        Object sign = request.getAttribute("sign");
//        if (ObjectUtil.isNull(sign)) {
//            return false;
//        }

        //2.验证签名
        String signServer = EncryptUtil.Md5(StrUtil.format("{}:{}", t, deviceIdentifier));

//        System.out.println(sign);
//        System.out.println(signServer);
//
//        if(!ObjectUtil.equal(signServer, sign)) {
//            return false;
//        }
        return true;
    }
}
