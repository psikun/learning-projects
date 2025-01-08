package org.demo.security.controller;

import org.demo.security.entity.UsernameAuthentication;
import org.demo.security.model.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("")
public class TestController {

    public Result usernameLogin(@RequestBody UsernameAuthentication requestParam) {
        // 1. 获取请求参数
        String username = requestParam.getUsername();
        String password = requestParam.getPassword();

        // 2. 认证逻辑
        System.out.println("校验:username:" + username + "password:" + password);

        //3.认证通过，返回jwt Token
        HashMap<Object, Object> responseData = new HashMap<>();
        responseData.put("token", "jwtToken...");
        responseData.put("refreshToken", "refreshToken...");

        return new Result("200", "登录成功", responseData);
    }


}
