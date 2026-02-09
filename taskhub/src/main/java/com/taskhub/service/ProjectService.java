package com.taskhub.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taskhub.entity.Project;

public interface ProjectService extends IService<Project> {
    // ProjectService.java
    boolean saveProject(Project project);

    // 分页查询当前用户的项目
    IPage<Project> listProjects(int pageNum, int pageSize, String name);
}