package com.demo.service;

import java.util.Map;

/**
 * @Author psikun
 * @Description WxpayService
 * @Date 2023/07/24/ 15:23
 */
public interface WxPayService {
    Map<String, Object> nativePay(Long productId);
}
