package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.OfficeDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.office;
import com.example.scimanagement.entity.subentity.lab2office;
import com.example.scimanagement.mapper.lab2officeMapper;
import com.example.scimanagement.mapper.officeMapper;
import com.example.scimanagement.service.IOfficeService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.example.scimanagement.utils.QueryUtils.QueryIdClass;

@Service
@Repository
public class OfficeServiceImpl extends ServiceImpl<officeMapper, office> implements IOfficeService {

    @Resource
    private lab2officeMapper lab2officeMapper;

    @Override
    public Result findById(int officeId) {
        office office=getById(officeId);
        if(office==null)
            return Result.fail("No such office. ");
        OfficeDTO officeDTO=new OfficeDTO();
        officeDTO.setOffice_id(office.getOffice_id());
        officeDTO.setArea(office.getArea());
        officeDTO.setAddress(office.getAddress());
        officeDTO.setLab_id(QueryIdClass(lab2officeMapper,officeId, lab2office.class,"office_id").getLab_id());
        return Result.ok(officeDTO);
    }

    @Override
    public Result save(int id, double area, String address) {
        office office=new office();
        if(getById(id)!=null)
            office.setOffice_id(null);
        else office.setOffice_id(id);
        office.setArea(area);
        office.setAddress(address);
        save(office);
        return Result.ok();
    }

    @Override
    public Result update(int id, double area, String address) {
        office office=getById(id);
        if(office==null)
            return Result.fail("No such office. ");
        else {
            office.setArea(area);
            office.setAddress(address);
            updateById(office);
            return Result.ok();
        }
    }

    @Override
    public Result queryOfficeByLabId(int labId) {
        QueryWrapper<lab2office> queryWrapper=new QueryWrapper<lab2office>()
                .eq("lab_id",labId);
        List<lab2office> lab2offices=lab2officeMapper.selectList(queryWrapper);
        if(lab2offices.isEmpty())
            return Result.fail("Lab not found or lab has no offices. ");
        List<Integer> officeIds=new ArrayList<>();
        for(lab2office lab2office:lab2offices)
            officeIds.add(lab2office.getOffice_id());
        QueryWrapper<office> queryWrapper1=new QueryWrapper<office>()
                .in("office_id",officeIds);
        List<office> offices=list(queryWrapper1);
        return Result.ok(offices,(long)offices.size());
    }


}
