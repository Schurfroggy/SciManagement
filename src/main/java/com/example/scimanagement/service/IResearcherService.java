package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.researcher;

public interface IResearcherService extends IService<researcher> {
    Result queryResearcherByName(String name);

    Result save(int researcherId, int labId, int subProjectId, String name, String gender, int age, String professionGrade, String researchDirection);

    Result update(int researcherId, int labId, int subProjectId, String name, String gender, int age, String professionGrade, String researchDirection);
}
