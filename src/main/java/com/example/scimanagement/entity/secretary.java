package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@TableName("secretary")
public class secretary {
    @TableId(value ="secretary_id",type = IdType.AUTO)
    private Integer secretary_id;

    @TableField("lab_id")
    private Integer lab_id;

    @TableField("name")
    private String name;

    @TableField("gender")
    private String gender;

    @TableField("age")
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField(value="employ_date")
    private Date employ_date;

    @TableField("work")
    private String work;
}
