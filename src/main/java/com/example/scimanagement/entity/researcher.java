package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("researcher")
public class researcher {
    @TableId(value ="researcher_id",type = IdType.AUTO)
    private Integer researcher_id;

    @TableField("name")
    private String name;

    @TableField
    private Integer sub_project_id;

    @TableField
    private boolean gender;

    @TableField
    private int age;

    @TableField
    private String profession_grade;

    @TableField
    private String research_direction;
}
