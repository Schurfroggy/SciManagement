package com.example.scimanagement.entity.subentity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sci_project_contributor")
public class project2contributor {

    @TableId(value ="id",type = IdType.AUTO)
    private Integer id;

    @TableField("sci_project_id")
    private Integer sci_project_id;

    @TableField("contributor_id")
    private Integer contributor_id;
}
