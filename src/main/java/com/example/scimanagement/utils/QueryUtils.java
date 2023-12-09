package com.example.scimanagement.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public class QueryUtils {
    public static <T> List<T> QueryMiddleTables(BaseMapper middleMapper,int id,Class<T> Clazz,String eq){
        QueryWrapper<T> wrapper = new QueryWrapper<T>()
                .select("*")
                .eq(eq, id);
        List<T> tList = middleMapper.selectList(wrapper);
        return tList;
    }

    public static <T> List<T> QueryThirdTables(BaseMapper thirdMapper, List<Integer> IdList, Class<T> Clazz, String eq){
        QueryWrapper<T> wrapper = new QueryWrapper<T>()
                .select("*")
                .in(eq, IdList);
        List<T> tList = thirdMapper.selectList(wrapper);
        return tList;
    }

    public static <T> T QueryIdClass(BaseMapper secondMapper,int id,Class<T> Clazz,String eq){
        QueryWrapper<T> wrapper = new QueryWrapper<T>()
                .eq(eq,id);
        T tOne= (T) secondMapper.selectOne(wrapper);
        return tOne;
    }

    public static <T> List<T> QueryIdClassList(BaseMapper secondMapper,int id,Class<T> Clazz,String eq){
        QueryWrapper<T> wrapper = new QueryWrapper<T>()
                .eq(eq,id);
        List<T> tList= (List<T>) secondMapper.selectList(wrapper);
        return tList;
    }

}
