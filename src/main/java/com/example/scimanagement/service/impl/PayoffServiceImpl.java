package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.PayoffDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.payoffs;
import com.example.scimanagement.entity.subentity.project2payoffs;
import com.example.scimanagement.mapper.payoffsMapper;
import com.example.scimanagement.mapper.project2payoffsMapper;
import com.example.scimanagement.service.IPayoffService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.Date;
import java.util.List;

import static com.example.scimanagement.utils.QueryUtils.QueryIdClass;

@Service
@Repository
public class PayoffServiceImpl extends ServiceImpl<payoffsMapper, payoffs> implements IPayoffService {

    @Resource
    private project2payoffsMapper project2payoffsMapper;
    @Override
    public Result findById(int payoffId) {
        payoffs payoff=getById(payoffId);
        if(payoff==null)
            return Result.fail("No such payoff");
        PayoffDTO payoffDTO=new PayoffDTO();
        payoffDTO.setPayoff_id(payoff.getPayoff_id());
        payoffDTO.setDate(payoff.getDate());
        payoffDTO.setName(payoff.getName());
        payoffDTO.setRank(payoff.getRank());
        payoffDTO.setType(payoff.getType());
        payoffDTO.setSci_project_id(QueryIdClass(project2payoffsMapper,payoffId, project2payoffs.class,"payoff_id").getSci_project_id());
        return Result.ok(payoffDTO);
    }

    @Override
    public Result save(int id, String name, String type, Date date, int rank) {
        payoffs payoff=new payoffs();
        if(getById(id)!=null)
            payoff.setPayoff_id(null);
        else payoff.setPayoff_id(id);
        payoff.setName(name);
        payoff.setType(type);
        payoff.setDate(date);
        payoff.setRank(rank);
        save(payoff);
        return Result.ok();
    }

    @Override
    public Result update(int id, String name, String type, Date date, int rank) {
        payoffs payoff=getById(id);
        payoff.setName(name);
        payoff.setType(type);
        payoff.setDate(date);
        payoff.setRank(rank);
        updateById(payoff);
        return Result.ok();
    }

    @Override
    public Result queryPayoffByName(String name) {
        QueryWrapper<payoffs> queryWrapper = new QueryWrapper<payoffs>()
                .select("*")
                .like("name",name);
        List<payoffs> payoffsList = list(queryWrapper);
        if(payoffsList.isEmpty())
            return Result.fail("No such payoffs");
        else
            return Result.ok((List<?>) payoffsList,(long)payoffsList.size());
    }
}
