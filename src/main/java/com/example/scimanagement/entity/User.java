package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {

    @TableId(value ="user_id",type = IdType.AUTO)
    private Integer id;

    @TableField
    private String name;

    @TableField(value="phone")
    private String phone_number;

    @TableField
    private String password;
}
