package com.example.scimanagement.controller;


import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.lab;
import com.example.scimanagement.service.ILabService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/lab")
public class LabController {
    @Resource
    private ILabService labService;

    @PostMapping("/add")
    public Result addLab(@Param("lab_id") int lab_id,
                         @Param("name") String name,
                         @Param("introduction") String introduction,
                         @Param("director_id") int director_id,
                         @Param("secretary_id") int secretary_id,
                         @RequestParam(value = "offices") List<Integer> offices){
        return labService.save(lab_id,name,introduction,director_id,secretary_id,offices);
    }

    @DeleteMapping("/{id}")
    public Result deleteLab(@PathVariable("id")int id){
        labService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result updateLab(@Param("lab_id") int lab_id,
                            @Param("name") String name,
                            @Param("introduction") String introduction,
                            @Param("director_id") int director_id,
                            @Param("secretary_id") int secretary_id,
                            @RequestParam(value = "offices") List<Integer> offices){
        return labService.update(lab_id,name,introduction,director_id,secretary_id,offices);
    }

    @GetMapping ("/{id}")
    public Result queryLabById(@PathVariable("id") int id){
        return labService.findById(id);
    }

    @GetMapping("/searchByName")
    public Result queryLabByName(@Param("name") String name){
        return labService.queryLabByName(name);
    }

    @GetMapping("/getAll")
    public Result queryAllLab(){
        return Result.ok((List<lab>) labService.list(), (long)labService.list().size());
    }
}
