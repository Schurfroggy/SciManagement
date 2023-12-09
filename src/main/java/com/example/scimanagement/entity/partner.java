package com.example.scimanagement.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("partner")
public class partner {

    @TableId(value ="partner_id",type = IdType.AUTO)
    private Integer partner_id;

    @TableField("name")
    private String name;

    @TableField("address")
    private String address;

    @TableField("head_id")
    private Integer head_id;

    @TableField(exist = false)
    private heads head;

    @TableField(exist = false)
    private List<links> links;
}

