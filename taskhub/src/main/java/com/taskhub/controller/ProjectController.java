package com.taskhub.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taskhub.common.api.CommonResult;
import com.taskhub.entity.Project;
import com.taskhub.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService; // 1. 确保已经注入了实例

    @PostMapping("/create")
    public CommonResult<Boolean> create(@RequestBody Project project) {
        // 2. 必须使用注入的小写变量 projectService 调用，而不是大写的类名
        boolean success = projectService.saveProject(project);
        return success ? CommonResult.success(true) : CommonResult.failed("创建失败");
    }

    // 检查这里的 Mapping
    @GetMapping("/list") // 加上前面的 RequestMapping，完整路径就是 /api/project/list
    public CommonResult<IPage<Project>> list(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name) {
        return CommonResult.success(projectService.listProjects(pageNum, pageSize, name));
    }
}
