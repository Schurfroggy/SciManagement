package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.lab;

import java.util.List;

public interface ILabService extends IService<lab> {
    Result findById(int id);

    Result update(int id,String name, String introduction, int directorId, int secretaryId, List<Integer> officeIds);

    Result save(int labId, String name, String introduction, int directorId, int secretaryId, List<Integer> officeIds);

    Result queryLabByName(String name);
}
