package com.redis.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.redis.demo.entity.Student;

/**
 * @author psikun
 * @description 针对表【student(学生)】的数据库操作Service
 * @createDate 2023-06-07 14:03:38
 */
public interface StudentService extends IService<Student> {
    IPage<Student> list(Integer pageNum, Integer pageSize);

}
