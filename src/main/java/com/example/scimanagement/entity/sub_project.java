package com.example.scimanagement.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sub_project")
public class sub_project {
    @TableId(value ="sub_project_id",type = IdType.AUTO)
    private Integer sub_project_id;

    @TableField("head_name")
    private String head_name;

    @TableField("sequence_id")
    private Integer sequence_id;

    @TableField("deadline")
    private Date deadline;

    @TableField("fund")
    private double fund;
}
