package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.IPayoffService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;

@RestController
@RequestMapping("/payoff")
public class PayoffController {
    @Resource
    private IPayoffService payoffService;

    @GetMapping("/getAll")
    public Result queryAllPayoff(){
        return Result.ok(payoffService.list(),(long)payoffService.list().size());
    }

    @GetMapping("/{id}")
    public Result queryPayoffById(@PathVariable("id") int payoff_id){
        return payoffService.findById(payoff_id);
    }

    @PostMapping("/add")
    public Result addPayoff(@Param("id") int id,
                             @Param("name") String name,
                             @Param("type") String type,
                             @Param("date") Date date,
                             @Param("rank") int rank){
        return payoffService.save(id,name,type,date,rank);
    }

    @DeleteMapping("/{id}")
    public Result deletePayoff(@PathVariable("id")int id){
        payoffService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result updatePayoff(@Param("id") int id,
                               @Param("name") String name,
                               @Param("type") String type,
                               @Param("date") Date date,
                               @Param("rank") int rank){
        return payoffService.update(id,name,type,date,rank);
    }

    @GetMapping("/searchByName")
    public Result queryPayoffByName(@Param("name") String name){
        return payoffService.queryPayoffByName(name);
    }

}
