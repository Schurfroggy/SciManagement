package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.director;

import java.sql.Date;

public interface IDirectorService extends IService<director> {

    Result findById(int directorId);

    Result save(int directorId, int lab_id, int researcher_id, Date employDate, String tenure);

    Result remove(int id);

    Result update(int directorId, int labId, int researcherId, Date employDate, String tenure);
}
