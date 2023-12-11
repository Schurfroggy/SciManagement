package com.example.scimanagement.service;

import com.example.scimanagement.dto.Result;

public interface IPhoneService{
    Result queryAllPhone();

    Result queryPhoneByName(String name);
}
