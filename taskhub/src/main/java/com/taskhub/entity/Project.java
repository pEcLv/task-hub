package com.taskhub.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("th_project")
public class Project {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;

    /**
     * 状态: 0-进行中, 1-已完成
     */
    private Integer status;

    /**
     * 创建者用户ID
     */
    private Long createBy;

    /**
     * 创建时间（稍后我们会配置自动填充）
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除: 0-未删, 1-已删
     */
    @TableLogic
    private Integer isDeleted;
}