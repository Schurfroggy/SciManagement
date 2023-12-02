package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("secretary")
public class secretary {
    @TableId(value ="secretary_id",type = IdType.AUTO)
    private Integer secretary_id;

    @TableField("name")
    private String name;

    @TableField("gender")
    private boolean gender;

    @TableField("age")
    private Integer age;

    @TableField("employ_date")
    private Date employ_date;

    @TableField("work")
    private String work;
}
