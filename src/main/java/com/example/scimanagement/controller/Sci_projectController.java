package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.Sci_project;
import com.example.scimanagement.entity.sub_project;
import com.example.scimanagement.service.ISci_projectService;
import com.example.scimanagement.service.ISubProjectService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import static com.example.scimanagement.utils.DateUtils.formatString;

@RestController
@RequestMapping("/sci_project")
public class Sci_projectController {
    @Resource
    private ISci_projectService ISci_projectService;

    @Resource
    private ISubProjectService ISubProjectService;

    @PostMapping("/add")
    public Result addProject(@Param("sci_project_id") int sci_project_id,
                         @Param("client_id") int client_id,
                         @Param("testing_id") int testing_id,
                         @Param("head_name") String head_name,
                         @Param("name") String name,
                         @Param("content") String content,
                         @Param("fund") double fund,
                         @Param("start_date") String start_date,
                         @Param("end_date") String end_date) throws ParseException {
        return ISci_projectService.save(sci_project_id,client_id,testing_id,head_name,name,content,fund,formatString(start_date),formatString(end_date));
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
                            @Param("start_date") String start_date,
                            @Param("end_date") String end_date) throws ParseException {
        return ISci_projectService.update(sci_project_id,client_id,testing_id,head_name,name,content,fund,formatString(start_date),formatString(end_date));
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

    @GetMapping("/searchByDouble")
    public Result queryProjectByDouble(@Param("name") String name, @Param("content") String content){
        return ISci_projectService.queryProjectByDouble(name,content);
    }








    @GetMapping ("/sub/{id}")
    public Result querySubProjectById(@PathVariable("id") int id){
        return ISubProjectService.findById(id);
    }
    @PostMapping("/addSub")
    public Result addSubProject(@Param("sci_project_id") int sci_project_id,
                         @Param("sub_project_id") int sub_project_id,
                         @Param("head_name") String head_name,
                         @Param("sequence_id") int sequence_id,
                         @Param("deadline") String deadline,
                         @Param("fund") double fund,
                         @Param("tech_indicator") String tech_indicator) throws ParseException {
        return ISci_projectService.saveSubProject(sci_project_id,sub_project_id,head_name,sequence_id,formatString(deadline),fund,tech_indicator);
    }

    @GetMapping("/getAllSub")
    public Result queryAllSubProject(){
        return Result.ok((List<sub_project>) ISubProjectService.list(), (long) ISubProjectService.list().size());
    }


    @PutMapping("/updateSub")
    public Result updateSubProject(@Param("sci_project_id") int sci_project_id,
                            @Param("sub_project_id") int sub_project_id,
                            @Param("head_name") String head_name,
                            @Param("sequence_id") int sequence_id,
                            @Param("deadline") String deadline,
                            @Param("fund") double fund,
                            @Param("tech_indicator") String tech_indicator) throws ParseException {
        return ISci_projectService.updateSubProject(sci_project_id,sub_project_id,head_name,sequence_id,formatString(deadline),fund,tech_indicator);
    }

    @DeleteMapping("/deleteSub")
    public Result deleteSubProject(@Param("sub_project_id") int sub_project_id){
        return ISci_projectService.removeSubProject(sub_project_id);
    }

    @GetMapping("/searchByProjectId")
    public Result querySubProjectByProjectId(@Param("sci_project_id") int sci_project_id){
        return ISubProjectService.querySubProjectBySciProjectId(sci_project_id);
    }

}
