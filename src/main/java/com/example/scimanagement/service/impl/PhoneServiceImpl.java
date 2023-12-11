package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.scimanagement.dto.ContactDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.heads;
import com.example.scimanagement.entity.links;
import com.example.scimanagement.mapper.headsMapper;
import com.example.scimanagement.mapper.linksMapper;
import com.example.scimanagement.service.IPhoneService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class PhoneServiceImpl implements IPhoneService {

    @Resource
    private linksMapper linksMapper;

    @Resource
    private headsMapper headsMapper;

    @Override
    public Result queryAllPhone() {
        List<ContactDTO> contactDTOS = new ArrayList<>();
        List<links> links = linksMapper.selectList(null);
        List<heads> heads = headsMapper.selectList(null);
        for(links link:links)
            contactDTOS.add(new ContactDTO(link));
        for(heads head:heads)
            contactDTOS.add(new ContactDTO(head));
        return Result.ok(contactDTOS,(long)contactDTOS.size());
    }

    @Override
    public Result queryPhoneByName(String name) {
        QueryWrapper<links> queryWrapper = new QueryWrapper<links>()
                .like("name",name);
        QueryWrapper<heads> queryWrapper1 = new QueryWrapper<heads>()
                .like("name",name);
        List<ContactDTO> contactDTOS = new ArrayList<>();
        List<links> links = linksMapper.selectList(queryWrapper);
        List<heads> heads = headsMapper.selectList(queryWrapper1);
        for(links link:links)
            contactDTOS.add(new ContactDTO(link));
        for(heads head:heads)
            contactDTOS.add(new ContactDTO(head));
        return Result.ok(contactDTOS,(long)contactDTOS.size());
    }
}
