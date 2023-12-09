package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.researcher;
import com.example.scimanagement.mapper.labMapper;
import com.example.scimanagement.mapper.researcherMapper;
import com.example.scimanagement.mapper.sub_projectMapper;
import com.example.scimanagement.service.IResearcherService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Repository
public class ResearcherServiceImpl extends ServiceImpl<researcherMapper, researcher> implements IResearcherService {
    @Resource
    private labMapper labMapper;
    @Resource
    private sub_projectMapper sub_projectMapper;

    @Override
    public Result queryResearcherByName(String name) {
        QueryWrapper<researcher> queryWrapper = new QueryWrapper<researcher>()
                .select("*")
                .like("name",name);
        List<researcher> ResearcherList = list(queryWrapper);
        if(ResearcherList.isEmpty())
            return Result.fail("No such researcher");
        else
            return Result.ok((List<?>) ResearcherList,(long)ResearcherList.size());
    }

    @Override
    public Result save(int researcherId, int labId, int subProjectId, String name, String gender, int age, String professionGrade, String researchDirection) {
        if(labMapper.selectById(labId)==null)
            return Result.fail("No such lab:"+labId);
        else if(sub_projectMapper.selectById(subProjectId)==null)
            return Result.fail("No such subproject:"+subProjectId);
        researcher researcher = new researcher();
        researcher.setLab_id(labId);
        researcher.setSub_project_id(subProjectId);
        researcher.setName(name);
        researcher.setGender(gender);
        researcher.setAge(age);
        researcher.setProfession_grade(professionGrade);
        researcher.setResearch_direction(researchDirection);
        if(getById(researcherId)!=null){
            save(researcher);
            return Result.ok("Id duplicated");
        }
        researcher.setResearcher_id(researcherId);
        save(researcher);
        return Result.ok();
    }

    @Override
    public Result update(int researcherId, int labId, int subProjectId, String name, String gender, int age, String professionGrade, String researchDirection) {
        if(labMapper.selectById(labId)==null)
            return Result.fail("No such lab:"+labId);
        else if(sub_projectMapper.selectById(subProjectId)==null)
            return Result.fail("No such subproject:"+subProjectId);
        update(new UpdateWrapper<researcher>().eq("researcher_id",researcherId)
                        .set("lab_id",labId)
                        .set("sub_project_id",subProjectId)
                        .set("name",name)
                        .set("gender",gender)
                        .set("age",age)
                        .set("profession_grade",professionGrade)
                        .set("research_direction",researchDirection));
        return Result.ok();
    }


}
