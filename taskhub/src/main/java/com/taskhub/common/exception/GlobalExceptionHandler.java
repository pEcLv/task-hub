package com.taskhub.common.exception;

import com.taskhub.common.api.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> handle(Exception e) {
        // 面试加分点：此处可以记录错误日志
        return CommonResult.failed(e.getMessage());
    }

    // 你还可以针对具体的校验异常进行处理
}