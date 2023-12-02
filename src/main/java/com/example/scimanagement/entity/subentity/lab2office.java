package com.example.scimanagement.entity.subentity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("lab_office")
public class lab2office {
    @TableId(value ="id",type = IdType.AUTO)
    private Integer id;

    @TableField("lab_id")
    private Integer lab_id;

    @TableField("office_id")
    private Integer office_id;
}
