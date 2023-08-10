package com.demo.service.impl;

import com.demo.config.WxPayConfig;
import com.demo.entity.OrderInfo;
import com.demo.enums.OrderStatus;
import com.demo.enums.wxpay.WxApiType;
import com.demo.enums.wxpay.WxNotifyType;
import com.demo.service.WxPayService;
import com.demo.util.OrderNoUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author psikun
 * @Description WxPayServiceImpl
 * @Date 2023/07/24/ 15:23
 */

@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {

    @Autowired
    private WxPayConfig wxPayConfig;

    @Override
    public Map<String, Object> nativePay(Long productId) {

        log.info("生成订单");

        // 生成订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setTitle("test");
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo());
        orderInfo.setProductId(productId);
        // 单位为分
        orderInfo.setTotalFee(1);
        // 订单状态
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType());
        // 存入数据库

        log.info("调用统一下单API");

        // 调用统一下单API
        HttpPost httpPost = new HttpPost(wxPayConfig.getDomain().concat(WxApiType.NATIVE_PAY.getType()));

        // 请求body参数
        Gson gson = new Gson();
        Map paramsMap = new HashMap();
        paramsMap.put("appid", wxPayConfig.getAppid());
        paramsMap.put("mchid", wxPayConfig.getMchId());
        paramsMap.put("description", orderInfo.getTitle());
        paramsMap.put("out_trade_no", orderInfo.getOrderNo());
        paramsMap.put("notify_url", wxPayConfig.getNotifyDomain().concat(WxNotifyType.NATIVE_NOTIFY.getType()));

        Map amountMap = new HashMap();
        amountMap.put("total", orderInfo.getTotalFee());
        amountMap.put("currency", "CNY");

        paramsMap.put("amount", amountMap);
        String jsonParams = gson.toJson(paramsMap);

        log.info("请求参数: " + jsonParams);

        return null;
    }
}
