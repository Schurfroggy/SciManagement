package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.client;

import java.util.List;

public interface IClientService extends IService<client> {
    Result findById(int clientId);

    Result save(int id, String name, String address, int headId, List<Integer> links);

    Result update(int id, String name, String address, int headId, List<Integer> links);
}
