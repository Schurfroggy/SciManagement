package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.office;

public interface IOfficeService extends IService<office> {
    Result findById(int officeId);

    Result save(int id, double area, String address);

    Result update(int id, double area, String address);

    Result queryOfficeByLabId(int labId);
}
