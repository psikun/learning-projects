package com.redis.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.redis.demo.annotations.RedisCache;
import com.redis.demo.entity.Student;
import com.redis.demo.mapper.StudentMapper;
import com.redis.demo.service.StudentService;
import com.redis.demo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author psikun
 * @description 针对表【student(学生)】的数据库操作Service实现
 * @createDate 2023-06-07 14:03:38
 */

@CacheConfig(cacheNames = "student",cacheManager = "cacheManager")
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @RedisCache(key = "list", expire = 3600)
    public IPage<Student> list(Integer pageNum, Integer pageSize) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        return studentMapper.selectPage(page, null);
    }

    @Override
    @Cacheable(key = "#id")
    public Student getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @CachePut(key = "#student.id", condition = "#result == true")
    public boolean save(Student student) {
        return super.save(student);
    }

    @Override
    @CachePut(key = "#student.id")
    public boolean updateById(Student student) {
        return super.updateById(student);
    }

    @Override
    @CacheEvict(key = "#id", beforeInvocation = false)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}




