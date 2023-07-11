package com.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String test() {
        return "hello";
    }
}
