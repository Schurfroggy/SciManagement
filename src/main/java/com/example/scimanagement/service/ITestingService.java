package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.testing;

import java.util.List;

public interface ITestingService extends IService<testing> {
    Result findById(int partnerId);

    Result save(int id, String name, String address, int headId, List<Integer> links);

    Result update(int id, String name, String address, int headId, List<Integer> links);
}
