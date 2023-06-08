package com.redis.demo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 学生
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 0：男 1：女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}