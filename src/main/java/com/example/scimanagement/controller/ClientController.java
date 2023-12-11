package com.example.scimanagement.controller;

import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.IClientService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Resource
    private IClientService clientService;

    @GetMapping("/getAll")
    public Result queryAllClient(){
        return Result.ok(clientService.list(),(long)clientService.list().size());
    }

    @GetMapping("/{id}")
    public Result queryClientById(@PathVariable("id") int client_id){
        return clientService.findById(client_id);
    }

    @PostMapping("/add")
    public Result addClient(@Param("id") int id,
                             @Param("name") String name,
                             @Param("address") String address,
                             @Param("head_id") int head_id,
                             @RequestParam(value = "links") List<Integer> links){
        return clientService.save(id,name,address,head_id,links);
    }

    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable("id")int id){
        clientService.removeById(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result updateClient(@Param("id") int id,
                               @Param("name") String name,
                               @Param("address") String address,
                               @Param("head_id") int head_id,
                               @RequestParam(value = "links") List<Integer> links){
        return clientService.update(id,name,address,head_id,links);
    }

    @GetMapping("/searchByName")
    public Result queryClientByName(@Param("name") String name){
        return clientService.queryClientByName(name);
    }


}
