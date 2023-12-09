package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("lab")
public class lab {

    @TableId(value ="lab_id",type = IdType.AUTO)
    private Integer lab_id;

    @TableField("name")
    private String name;

    @TableField("introduction")
    private String introduction;

    @TableField("director_id")
    private Integer director_id;

    @TableField("secretary_id")
    private Integer secretary_id;
}
