package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.entity.payoffs;
import com.example.scimanagement.mapper.payoffsMapper;
import com.example.scimanagement.service.IPayoffService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class PayoffServiceImpl extends ServiceImpl<payoffsMapper, payoffs> implements IPayoffService {
}
