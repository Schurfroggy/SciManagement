package com.example.scimanagement.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("testing")
public class testing {

    @TableId(value ="testing_id",type = IdType.AUTO)
    private Integer testing_id;

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
