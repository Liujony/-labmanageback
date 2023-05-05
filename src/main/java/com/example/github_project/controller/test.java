package com.example.github_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZC0
 * @Date: 2023/05/05/22:07
 * @Description: 代码完成
 */
@RestController
public class test {
    @GetMapping("test")
    public String test(){
        return "success";
    }

}
