package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.ISecretaryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;

import static com.example.scimanagement.utils.DateUtils.formatString;


@RestController
@RequestMapping("/secretary")
public class SecretaryController {
    @Resource
    private ISecretaryService secretaryService;
    @GetMapping("/getAll")
    public Result queryAllSecretary(){
        return Result.ok(secretaryService.list(),(long)secretaryService.list().size());
    }

    @GetMapping("searchByName")
    public Result querySecretaryByName(@Param("name") String name){
        return secretaryService.querySecretaryByName(name);
    }

    @GetMapping("/{id}")
    public Result querySecretaryById(@PathVariable("id") int id){
        return secretaryService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteSecretary(@PathVariable("id")int id){
        return secretaryService.remove(id);
    }

    @PostMapping("/add")
    public Result addSecretary(@Param("secretary_id") int secretary_id,
                               @Param("lab_id") int lab_id,
                               @Param("name") String name,
                               @Param("gender") String gender,
                               @Param("age") int age,
                               @Param("employ_date")String employ_date,
                               @Param("work")String work) throws ParseException {
        return secretaryService.save(secretary_id, lab_id, name, gender, age, formatString(employ_date), work);
    }

    @PutMapping("/update")
    public Result updateSecretary(@Param("secretary_id") int secretary_id,
                                  @Param("lab_id") int lab_id,
                                  @Param("name") String name,
                                  @Param("gender") String gender,
                                  @Param("age") int age,
                                  @Param("employ_date")String employ_date,
                                  @Param("work")String work) throws ParseException {
        return secretaryService.update(secretary_id, lab_id, name, gender, age, formatString(employ_date) , work);

    }
}
