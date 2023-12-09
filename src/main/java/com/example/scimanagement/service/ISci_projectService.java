package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.Sci_project;

import java.sql.Date;

public interface ISci_projectService extends IService<Sci_project> {

    Result findById(int id);

    Result save(int sciProjectId, int clientId, int testingId, String headName, String name, String content, double fund, Date startDate, Date endDate);

    Result update(int sciProjectId, int clientId, int testingId, String headName, String name, String content, double fund, Date startDate, Date endDate);

    Result queryProjectByName(String name);

    Result queryProjectByContent(String content);
}
