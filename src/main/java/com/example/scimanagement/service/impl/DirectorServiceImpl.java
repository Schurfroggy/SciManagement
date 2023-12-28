package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.director;
import com.example.scimanagement.entity.lab;
import com.example.scimanagement.mapper.directorMapper;
import com.example.scimanagement.mapper.labMapper;
import com.example.scimanagement.mapper.researcherMapper;
import com.example.scimanagement.service.IDirectorService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;

@Service
@Repository
public class DirectorServiceImpl extends ServiceImpl<directorMapper, director> implements IDirectorService {
    @Resource
    private researcherMapper researcherMapper;

    @Resource
    private labMapper labMapper;

    @Override
    public Result findById(int directorId) {
        director director = getById(directorId);
        if(director==null)
            return Result.fail("No such director");
        if(director.getResearcher_id()==null){
            director.setResearcherDetail(null);
            return Result.ok(director,"No researcher info, may be deleted unsafely");
        }
        director.setResearcherDetail(researcherMapper.selectById(director.getResearcher_id()));
        return Result.ok(director);
    }

    @Override
    public Result save(int directorId, int lab_id, int researcher_id, Date employDate, String tenure) {
        director director = new director();
        director.setLab_id(lab_id);
        director.setResearcher_id(researcher_id);
        director.setEmploy_date(employDate);
        director.setTenure(tenure);
        StringBuffer WarningMsg= new StringBuffer();
        //1.先判断实验室是否存在
        if(labMapper.selectById(lab_id)==null){
            WarningMsg.append("No such lab.Transferred to null.");
            director.setLab_id(null);
        }
        if(director.getLab_id()!=null && labMapper.selectById(lab_id).getDirector_id()!=null){
            WarningMsg.append("Lab has a director.Transferred to null.");
            director.setLab_id(null);
        }
        //2.再判断director id是否重复
        if(getById(directorId)==null){
            director.setDirector_id(directorId);
            save(director);
        }
        else{
            save(director);
            WarningMsg.append("Id duplicated");
        }
        if(WarningMsg.length()==0)
            return Result.ok();
        else return Result.ok(new String(WarningMsg));
    }

    @Override
    public Result remove(int id) {
        director director = getById(id);
        int lab_id;
        if(director.getLab_id()!=null)
            lab_id = director.getLab_id();
        else {
            removeById(id);
            return Result.ok("No lab info");
        }
        if(labMapper.selectById(lab_id)!=null){
            lab labNew=labMapper.selectById(lab_id);
            labNew.setDirector_id(null);
            labMapper.updateById(labNew);
            removeById(id);
            return Result.ok("Now lab: "+lab_id+" has no director");
        }
        removeById(id);
        return Result.ok();
    }

    @Override
    public Result update(int directorId, int labId, int researcherId, Date employDate, String tenure) {
        director director = getById(directorId);
        String WarningMsg= "";
        //1.先判断实验室是否存在
        if(labMapper.selectById(labId)==null){
            WarningMsg="No such lab.Transferred to null.";
            director.setLab_id(null);
        }
        else if(labMapper.selectById(labId).getDirector_id()!=null){
            WarningMsg="Lab has a director.Transferred to null.";
            director.setLab_id(null);
        }
        else director.setLab_id(labId);
        director.setResearcher_id(researcherId);
        director.setEmploy_date(employDate);
        director.setTenure(tenure);
        updateById(director);
        if(WarningMsg.isEmpty())
            return Result.ok();
        else return Result.ok(WarningMsg);
    }
}
