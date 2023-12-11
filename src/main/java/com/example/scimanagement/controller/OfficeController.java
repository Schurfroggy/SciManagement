package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.IOfficeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/office")
public class OfficeController {
    @Resource
    private IOfficeService officeService;

    @GetMapping("/getAll")
    public Result queryAllOffice(){
        return Result.ok(officeService.list(),(long)officeService.list().size());
    }

    @GetMapping("/{id}")
    public Result queryOfficeById(@PathVariable("id") int Office_id){
        return officeService.findById(Office_id);
    }

    @GetMapping("/searchByLabId")
    public Result queryOfficeByLabId(@Param("lab_id") int lab_id){
        return officeService.queryOfficeByLabId(lab_id);
    }

    @PostMapping("/add")
    public Result addOffice(@Param("id") int id,
                             @Param("area") double area,
                             @Param("address") String address){
        return officeService.save(id,area,address);
    }

    @DeleteMapping("/{id}")
    public Result deleteOffice(@PathVariable("id")int id){
        officeService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result updateOffice(@Param("id") int id,
                               @Param("area") double area,
                               @Param("address") String address){
        return officeService.update(id,area,address);
    }
}
