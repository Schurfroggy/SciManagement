package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.IResearcherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/researcher")
public class ResearcherController {

    @Resource
    private IResearcherService researcherService;

    @GetMapping("/getAll")
    public Result queryAllResearcher(){
        return Result.ok(researcherService.list(),(long)researcherService.list().size());
    }

    @GetMapping("/searchById")
    public Result queryResearcherById(@Param("researcher_id") int researcher_id){
        return Result.ok(researcherService.getById(researcher_id));
    }

    @GetMapping("/searchByName")
    public Result queryResearcherByName(@Param("name") String name){
        return researcherService.queryResearcherByName(name);
    }

    @PostMapping("/add")
    public Result addResearcher(@Param("researcher_id") int researcher_id,
                               @Param("lab_id") int lab_id,
                               @Param("sub_project_id") int sub_project_id,
                               @Param("name") String name,
                               @Param("gender") String gender,
                               @Param("age") int age,
                               @Param("profession_grade") String profession_grade,
                               @Param("research_direction") String research_direction){
        return researcherService.save(researcher_id,lab_id,sub_project_id,name,gender,age,profession_grade,research_direction);
    }

    @PutMapping("/update")
    public Result updateResearcher(@Param("researcher_id") int researcher_id,
                                   @Param("lab_id") int lab_id,
                                   @Param("sub_project_id") int sub_project_id,
                                   @Param("name") String name,
                                   @Param("gender") String gender,
                                   @Param("age") int age,
                                   @Param("profession_grade") String profession_grade,
                                   @Param("research_direction") String research_direction){
        return researcherService.update(researcher_id,lab_id,sub_project_id,name,gender,age,profession_grade,research_direction);
    }

    @DeleteMapping("/{id}")
    public Result deleteResearcher(@PathVariable("id")int id){
        researcherService.removeById(id);
        return Result.ok();
    }

}
