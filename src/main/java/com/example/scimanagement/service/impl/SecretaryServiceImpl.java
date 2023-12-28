package com.example.scimanagement.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.lab;
import com.example.scimanagement.entity.secretary;
import com.example.scimanagement.mapper.labMapper;
import com.example.scimanagement.mapper.secretaryMapper;
import com.example.scimanagement.service.ISecretaryService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
@Repository
public class SecretaryServiceImpl extends ServiceImpl<secretaryMapper, secretary> implements ISecretaryService {

    @Resource
    private labMapper labMapper;
    @Override
    public Result querySecretaryByName(String name) {
        QueryWrapper<secretary> queryWrapper = new QueryWrapper<secretary>()
               .select("*")
               .like("name",name);
        List<secretary> secretaryList = list(queryWrapper);
        if(secretaryList.isEmpty())
            return Result.fail("No such secretary");
        else
            return Result.ok((List<?>) secretaryList,(long)secretaryList.size());
    }

    @Override
    public Result findById(int id) {
        if(getById(id)==null)
            return Result.fail("No such secretary");
        return Result.ok(getById(id));
    }

    @Override
    public Result remove(int id) {
        secretary secretary = getById(id);
        int lab_id;
        if(secretary.getLab_id()!=null)
            lab_id=secretary.getLab_id();
        else{
            removeById(id);
            return Result.ok("No lab info");
        }
        if(labMapper.selectById(lab_id)!=null){
            lab labNew=labMapper.selectById(lab_id);
            labNew.setSecretary_id(null);
            labMapper.updateById(labNew);
            removeById(id);
            return Result.ok("Now lab: "+lab_id+" has no secretary");
        }
        else{
            removeById(id);
            return Result.ok();
        }
    }

    @Override
    public Result save(int secretaryId, int labId, String name, String gender, int age, Date employDate, String work) {
        secretary secretaryNew = new secretary();
        secretaryNew.setLab_id(labId);
        secretaryNew.setName(name);
        secretaryNew.setGender(gender);
        secretaryNew.setAge(age);
        secretaryNew.setEmploy_date(employDate);
        secretaryNew.setWork(work);
        StringBuffer WarningMsg= new StringBuffer();
        //1.先判断实验室是否存在
        if(labMapper.selectById(labId)==null){
            WarningMsg.append("No such lab.Transferred to null.");
            secretaryNew.setLab_id(null);
        }
        if(labMapper.selectById(labId).getSecretary_id()!=null){
            WarningMsg.append("Lab has a secretary.Transferred to null.");
            secretaryNew.setLab_id(null);
        }
        //2.再判断secretary id是否重复
        if(getById(secretaryId)==null){
            secretaryNew.setSecretary_id(secretaryId);
            save(secretaryNew);
        }
        else{
            save(secretaryNew);
            WarningMsg.append("Id duplicated");
        }
        if(WarningMsg.length()==0)
            return Result.ok();
        else return Result.ok(new String(WarningMsg));
    }

    @Override
    public Result update(int secretaryId, int labId, String name, String gender, int age, Date employDate, String work) {
        secretary secretary = getById(secretaryId);
        String WarningMsg= "";
        //1.先判断实验室是否存在
        if(labMapper.selectById(labId)==null){
            WarningMsg="No such lab.Transferred to null.";
            secretary.setLab_id(null);
        }
        else if(labMapper.selectById(labId).getSecretary_id()!=null){
            WarningMsg="Lab has a secretary.Transferred to null.";
            secretary.setLab_id(null);
        }
        else secretary.setLab_id(labId);
        secretary.setAge(age);
        secretary.setGender(gender);
        secretary.setName(name);
        secretary.setEmploy_date(employDate);
        secretary.setWork(work);
        updateById(secretary);
        if(labMapper.selectById(labId)!=null){
            lab labNew=labMapper.selectById(labId);
            labNew.setSecretary_id(secretaryId);
            labMapper.updateById(labNew);
        }
        if(WarningMsg.isEmpty())
            return Result.ok();
        else return Result.ok(WarningMsg);
    }
}
