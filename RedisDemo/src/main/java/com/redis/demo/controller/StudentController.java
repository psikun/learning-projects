package com.redis.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.redis.demo.common.Result;
import com.redis.demo.entity.Student;
import com.redis.demo.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author psikun
 */
@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("list")
    public Result<IPage<Student>> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {

        IPage<Student> list = studentService.list(pageNum, pageSize);
        if (!Objects.isNull(list)) {
            return Result.success(list);
        }
        return Result.failed("查询失败");
    }

    @GetMapping("/{id}")
    public Result<Student> getReportDetailById(@PathVariable("id") String id) {
        Student student = studentService.getById(id);
        if (!Objects.isNull(student)) {
            return Result.success(student);
        }
        return Result.failed();
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody Student student) {
        if (studentService.save(student)) {
            return Result.success("添加成功");
        }
        return Result.failed("添加失败");
    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody Student reportDetail) {
        if (studentService.updateById(reportDetail)) {
            return Result.success("修改成功");
        }
        return Result.failed("修改失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteById(@PathVariable("id") String id) {
        if (studentService.removeById(id)) {
            return Result.success(null, "删除成功");
        }
        return Result.failed("删除失败");
    }
}
    