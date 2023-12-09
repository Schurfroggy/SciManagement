package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("links")
public class links {
    @TableId(value ="link_id",type = IdType.AUTO)
    private Integer head_id;

    @TableField("name")
    private String name;

    @TableField("office_number")
    private String office_number;

    @TableField("mobile_number")
    private String mobile_number;

    @TableField("email_address")
    private String email_address;
}