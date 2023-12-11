package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.IPhoneService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Resource
    private IPhoneService phoneService;

    @GetMapping("/getAll")
    public Result queryAllPhone(){
        return phoneService.queryAllPhone();
    }

    @GetMapping("/searchByName")
    public Result queryPhoneByName(@Param("name") String name){
        return phoneService.queryPhoneByName(name);
    }
}
