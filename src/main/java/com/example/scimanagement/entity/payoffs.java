package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("payoffs")
public class payoffs {

    @TableId(value ="payoff_id",type = IdType.AUTO)
    private Integer payoff_id;

    @TableField("type")
    private String type;

    @TableField("name")
    private String name;

    @TableField("date")
    private Date date;

    @TableField("rank")
    private Integer rank;


}
