package com.demo.controller;

import com.demo.service.WxPayService;
import com.demo.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @Author psikun
 * @Description WxPayController
 * @Date 2023/07/24/ 15:22
 */

@CrossOrigin
@RestController
@RequestMapping("/api/wx-pay")
@Api(tags = "网站微信支付api")
@Slf4j
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;


    @ApiOperation("调用统一下单API，生成支付二维码")
    @PostMapping("native/{product}")
    public R nativePay(@PathVariable Long productId) {

        log.info("发起支付请求");

        // 返回值返回二维码链接和订单号
        Map<String, Object> map = wxPayService.nativePay(productId);

        return R.ok().setData(map);
    }
}
