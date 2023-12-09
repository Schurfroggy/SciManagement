package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.secretary;

import java.sql.Date;

public interface ISecretaryService extends IService<secretary> {
    Result querySecretaryByName(String name);

    Result findById(int id);

    Result remove(int id);

    Result save(int secretaryId, int labId, String name, String gender, int age, Date employDate, String work);

    Result update(int secretaryId, int labId, String name, String gender, int age, Date employDate, String work);
}
