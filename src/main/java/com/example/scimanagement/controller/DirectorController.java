package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.IDirectorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;

import static com.example.scimanagement.utils.DateUtils.formatString;

@RestController
@RequestMapping("/director")
public class DirectorController {
    @Resource
    private IDirectorService directorService;

    @GetMapping("/getAll")
    public Result queryAllDirector(){
        return Result.ok(directorService.list(),(long)directorService.list().size());
    }

    @GetMapping("/{id}")
    public Result queryDirectorById(@PathVariable ("id") int director_id){
        return directorService.findById(director_id);
    }

    @PostMapping("/add")
    public Result addDirector(@Param("director_id") int director_id,
                             @Param("lab_id") int lab_id,
                             @Param("researcher_id") int researcher_id,
                             @Param("date") String employ_date,
                             @Param("tenure") String tenure) throws ParseException {
        return directorService.save(director_id,lab_id,researcher_id,formatString(employ_date),tenure);
    }

    @DeleteMapping("/{id}")
    public Result deleteDirector(@PathVariable("id")int id){
        return directorService.remove(id);
    }

    @PutMapping("/update")
    public Result updateDirector(@Param("director_id") int director_id,
                                @Param("lab_id") int lab_id,
                                @Param("researcher_id") int researcher_id,
                                @Param("date") String employ_date,
                                @Param("tenure") String tenure) throws ParseException {
        return directorService.update(director_id,lab_id,researcher_id,formatString(employ_date),tenure);
    }
}
