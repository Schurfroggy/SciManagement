package com.example.scimanagement.entity.subentity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("testing_link")
public class testing2link {
    @TableId(value ="id",type = IdType.AUTO)
    private Integer id;

    @TableField("testing_id")
    private Integer testing_id;

    @TableField("link_id")
    private Integer link_id;
}
