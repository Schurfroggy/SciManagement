package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.payoffs;

import java.sql.Date;

public interface IPayoffService extends IService<payoffs> {
    Result findById(int payoffId);

    Result save(int id, String name, String type, Date date, int rank);

    Result update(int id, String name, String type, Date date, int rank);

    Result queryPayoffByName(String name);
}
