package com.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("t_product")
public class Product extends BaseEntity {

    /**
     * 商品名称
     */
    private String title;

    /**
     * 价格（以分为单位）
     */
    private Integer price;
}
