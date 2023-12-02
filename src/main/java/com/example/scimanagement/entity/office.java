package com.example.scimanagement.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("office")
public class office {
    @TableId(value ="office_id",type = IdType.AUTO)
    private Integer office_id;

    @TableField("area")
    private double area;

    @TableField("address")
    private String address;

}
