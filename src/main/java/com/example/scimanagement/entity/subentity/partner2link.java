package com.example.scimanagement.entity.subentity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("partner_link")
public class partner2link {
    @TableId(value ="id",type = IdType.AUTO)
    private Integer id;

    @TableField("partner_id")
    private Integer partner_id;

    @TableField("link_id")
    private Integer link_id;
}
