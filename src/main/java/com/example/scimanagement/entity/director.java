package com.example.scimanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("director")
public class director {
    @TableId(value ="director_id",type = IdType.AUTO)
    private Integer director_id;

    @TableField("researcher_id")
    private Integer researcher_id;

    @TableField("lab_id")
    private Integer lab_id;

    @TableField("date")
    private Date employ_date;

    @TableField("tenure")
    private String tenure;

    @TableField(exist = false)
    private researcher researcherDetail;
}
