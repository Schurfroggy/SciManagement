package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.ITestingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/testing")
public class TestingController {
    @Resource
    private ITestingService testingService;

    @GetMapping("/getAll")
    public Result queryAllTesting(){
        return Result.ok(testingService.list(),(long)testingService.list().size());
    }

    @GetMapping("/{id}")
    public Result queryTestingById(@PathVariable("id") int testing_id){
        return testingService.findById(testing_id);
    }

    @PostMapping("/add")
    public Result addTesting(@Param("id") int id,
                             @Param("name") String name,
                             @Param("address") String address,
                             @Param("head_id") int head_id,
                             @RequestParam(value = "links") List<Integer> links){
        return testingService.save(id,name,address,head_id,links);
    }

    @DeleteMapping("/{id}")
    public Result deleteTesting(@PathVariable("id")int id){
        testingService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result updateTesting(@Param("id") int id,
                               @Param("name") String name,
                               @Param("address") String address,
                               @Param("head_id") int head_id,
                               @RequestParam(value = "links") List<Integer> links){
        return testingService.update(id,name,address,head_id,links);
    }
}
