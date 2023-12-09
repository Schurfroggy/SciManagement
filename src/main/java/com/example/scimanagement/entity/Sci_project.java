package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("sci_project")
public class Sci_project {
    @TableId(value ="sci_project_id",type = IdType.AUTO)
    private Integer sci_project_id;

    @TableField
    private Integer client_id;

    @TableField
    private Integer testing_id;

    @TableField
    private String head_name;

    @TableField
    private String name;

    @TableField
    private String content;

    @TableField
    private double fund;

    @TableField("start_date")
    private Date start_date;

    @TableField("end_date")
    private Date end_date;

}
