package com.example.scimanagement.controller;


import com.example.scimanagement.dto.LoginFormDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.service.IUserService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginForm) {
        return userService.login(loginForm);
    }

    @PostMapping("/register")
    public Result register(@RequestBody LoginFormDTO loginForm) {
        return userService.register(loginForm);
    }

    @PostMapping("/logout")
    public Result logout() {
        //TODO
        return Result.ok();
    }

    @GetMapping("/me")
    public Result info() {
        //TODO
        return Result.ok();
    }


}
