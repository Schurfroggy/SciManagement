package com.example.scimanagement.controller;

import com.example.scimanagement.service.IClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/client")
public class clientController {

    @Resource
    private IClientService clientService;

    


}
