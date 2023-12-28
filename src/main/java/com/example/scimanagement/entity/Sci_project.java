package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("start_date")
    private Date start_date;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("end_date")
    private Date end_date;

}
