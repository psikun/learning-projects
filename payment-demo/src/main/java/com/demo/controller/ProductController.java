package com.demo.controller;

import com.demo.vo.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author psikun
 * @Description ProductController
 * @Date 2023/07/11/ 10:29
 */

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {
    @GetMapping("/test")
    public R test(){
        return R
                .ok()
                .data("message", "hello")
                .data("now", new Date());
    }
}
