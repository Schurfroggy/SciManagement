package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.sub_project;

public interface ISubProjectService extends IService<sub_project> {
    Result querySubProjectBySciProjectId(int sciProjectId);

    Result findById(int id);
}
