package com.example.scimanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scimanagement.dto.LabDetail;
import com.example.scimanagement.entity.lab;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.ArrayList;
import java.util.List;

public interface labMapper extends BaseMapper<lab> {

}
