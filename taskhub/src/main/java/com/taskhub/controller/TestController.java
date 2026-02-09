package com.taskhub.controller;

import com.taskhub.common.api.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test") // 注意这里
public class TestController {
    @GetMapping("/hello") // 组合起来就是 /test/hello
    public CommonResult<String> hello() {
        return CommonResult.success("Hello TaskHub!");
    }
}