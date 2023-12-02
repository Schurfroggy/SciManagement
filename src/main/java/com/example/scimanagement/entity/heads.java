package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("heads")
public class heads {
    @TableId(value ="head_id",type = IdType.AUTO)
    private Integer head_id;

    @TableField("name")
    private String name;

    @TableField("office_number")
    private long office_number;

    @TableField("mobile_number")
    private long mobile_number;

    @TableField("email_address")
    private String email_address;
}
