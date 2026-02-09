package com.taskhub.common.util;

import com.taskhub.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    /**
     * 获取当前登录用户信息
     */
    public static User getCurrentUser() {
        // 从 SecurityContextHolder 中获取认证信息并强转为我们的 User 实体
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户 ID
     */
    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}