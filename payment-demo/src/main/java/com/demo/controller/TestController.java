package com.demo.controller;

import com.demo.config.WxPayConfig;
import com.demo.vo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author psikun
 * @Description TestController
 * @Date 2023/07/12/ 14:29
 */

@Api("测试控制器")
@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private WxPayConfig wxPayConfig;


    @GetMapping
    public R getWxPayConfig() {
        String mchId = wxPayConfig.getMchId();
        return R.ok().data("mchId", mchId);
    }

}
