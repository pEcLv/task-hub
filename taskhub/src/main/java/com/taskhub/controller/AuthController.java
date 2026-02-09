package com.taskhub.controller;

import com.taskhub.common.api.CommonResult;
import com.taskhub.security.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody Map<String, String> loginMap) {
        // 1. 验证账号密码
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginMap.get("username"), loginMap.get("password"))
        );

        // 2. 生成 Token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginMap.get("username"));
        final String token = jwtUtils.generateToken(userDetails);

        return CommonResult.success(Map.of("token", token));
    }
}
