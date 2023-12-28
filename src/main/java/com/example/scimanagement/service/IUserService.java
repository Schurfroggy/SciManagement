package com.example.scimanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scimanagement.dto.LoginFormDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.User;

public interface IUserService extends IService<User>{
    Result login(LoginFormDTO loginForm);

    Result register(LoginFormDTO loginForm);


}
