package com.redis.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redis.demo.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
* @author psikun
* @description 针对表【student(学生)】的数据库操作Mapper
* @createDate 2023-06-07 14:03:38
* @Entity .entity.Student
*/

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}




