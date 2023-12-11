package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.IPartnerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    @Resource
    private IPartnerService partnerService;

    @GetMapping("/getAll")
    public Result queryAllPartner(){
        return Result.ok(partnerService.list(),(long)partnerService.list().size());
    }

    @GetMapping("/{id}")
    public Result queryPartnerById(@PathVariable("id") int partner_id){
        return partnerService.findById(partner_id);
    }

    @PostMapping("/add")
    public Result addPartner(@Param("id") int id,
                             @Param("name") String name,
                             @Param("address") String address,
                             @Param("head_id") int head_id,
                             @RequestParam(value = "links") List<Integer> links){
        return partnerService.save(id,name,address,head_id,links);
    }

    @DeleteMapping("/{id}")
    public Result deletePartner(@PathVariable("id")int id){
        partnerService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result updatePartner(@Param("id") int id,
                               @Param("name") String name,
                               @Param("address") String address,
                               @Param("head_id") int head_id,
                               @RequestParam(value = "links") List<Integer> links){
        return partnerService.update(id,name,address,head_id,links);
    }

    @GetMapping("/searchByName")
    public Result queryPartnerByName(@Param("name") String name){
        return partnerService.queryPartnerByName(name);
    }
}
