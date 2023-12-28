package com.example.scimanagement.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("sub_project")
public class sub_project {
    @TableId(value ="sub_project_id",type = IdType.AUTO)
    private Integer sub_project_id;

    @TableField("head_name")
    private String head_name;

    @TableField("sequence_id")
    private Integer sequence_id;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("deadline")
    private Date deadline;

    @TableField("fund")
    private double fund;

    @TableField("tech_indicator")
    private String tech_indicator;
}
