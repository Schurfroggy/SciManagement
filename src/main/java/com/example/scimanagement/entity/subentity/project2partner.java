package com.example.scimanagement.entity.subentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("project2partner")
public class project2partner {
    @TableId(value ="id",type = IdType.AUTO)
    private Integer id;

    @TableField("sci_project_id")
    private Integer sci_project_id;

    @TableField("partner_id")
    private Integer partner_id;

}
