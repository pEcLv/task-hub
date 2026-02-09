package com.taskhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taskhub.common.util.SecurityUtils;
import com.taskhub.entity.Project;
import com.taskhub.mapper.ProjectMapper;
import com.taskhub.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    // ProjectServiceImpl.java
    @Override
    public boolean saveProject(Project project) {
        // 关键点：设置创建者 ID，体现安全性
        project.setCreateBy(SecurityUtils.getCurrentUserId());
        // 设置初始状态为 0（进行中）
        project.setStatus(0);
        return this.save(project);
    }

    @Override
    public IPage<Project> listProjects(int pageNum, int pageSize, String name) {
        Long userId = SecurityUtils.getCurrentUserId(); // 获取当前登录人ID

        Page<Project> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Project::getCreateBy, userId); // 只查自己的

        if (StringUtils.hasText(name)) {
            wrapper.like(Project::getName, name); // 支持按名称模糊搜索
        }
        wrapper.orderByDesc(Project::getCreateTime);

        return this.page(page, wrapper);
    }
}