package com.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("t_refund_info")
public class RefundInfo extends BaseEntity {

    /**
     * 商品订单编号
     */
    private String orderNo;

    /**
     * 退款单编号
     */
    private String refundNo;

    /**
     * 支付系统退款单号
     */
    private String refundId;

    /**
     * 原订单金额（以分为单位）
     */
    private Integer totalFee;

    /**
     * 退款金额（以分为单位）
     */
    private Integer refund;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 退款单状态
     */
    private String refundStatus;

    /**
     * 申请退款返回参数
     */
    private String contentReturn;

    /**
     * 退款结果通知参数
     */
    private String contentNotify;
}
