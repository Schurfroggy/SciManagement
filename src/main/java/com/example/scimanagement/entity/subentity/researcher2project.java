package com.example.scimanagement.entity.subentity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;


@Data
@TableName("researcher_sci_project_record")
public class researcher2project {

    @TableId(value ="record_id",type = IdType.AUTO)
    private Integer record_id;

    @TableField("sci_project_id")
    private Integer sci_project_id;

    @TableField("researcher_id")
    private Integer researcher_id;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField("start_date")
    private Date start_date;

    @TableField("workload")
    private String workload;

    @TableField("fund")
    private double fund;
}
