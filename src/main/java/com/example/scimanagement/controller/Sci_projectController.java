package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.Sci_project;
import com.example.scimanagement.service.ISci_projectService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/sci_project")
public class Sci_projectController {
    @Resource
    private ISci_projectService ISci_projectService;

    @PostMapping("/add")
    public Result addProject(@Param("sci_project_id") int sci_project_id,
                         @Param("client_id") int client_id,
                         @Param("testing_id") int testing_id,
                         @Param("head_name") String head_name,
                         @Param("name") String name,
                         @Param("content") String content,
                         @Param("fund") double fund,
                         @Param("start_date") Date start_date,
                         @Param("end_date") Date end_date) {
        return ISci_projectService.save(sci_project_id,client_id,testing_id,head_name,name,content,fund,start_date,end_date);
    }

    @DeleteMapping("/{id}")
    public Result deleteProject(@PathVariable("id")int id){
        ISci_projectService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result updateProject(@Param("sci_project_id") int sci_project_id,
                            @Param("client_id") int client_id,
                            @Param("testing_id") int testing_id,
                            @Param("head_name") String head_name,
                            @Param("name") String name,
                            @Param("content") String content,
                            @Param("fund") double fund,
                            @Param("start_date") Date start_date,
                            @Param("end_date") Date end_date){
        return ISci_projectService.update(sci_project_id,client_id,testing_id,head_name,name,content,fund,start_date,end_date);
    }

    @GetMapping ("/{id}")
    public Result queryProjectById(@PathVariable("id") int id){
        return ISci_projectService.findById(id);
    }

    @GetMapping("/getAll")
    public Result queryAllProject(){
        return Result.ok((List<Sci_project>) ISci_projectService.list(), (long) ISci_projectService.list().size());
    }

    @GetMapping("/searchByName")
    public Result queryProjectByName(@Param("name") String name){
        return ISci_projectService.queryProjectByName(name);
    }

    @GetMapping("/searchByContent")
    public Result queryProjectByContent(@Param("content") String content){
        return ISci_projectService.queryProjectByContent(content);
    }


}
