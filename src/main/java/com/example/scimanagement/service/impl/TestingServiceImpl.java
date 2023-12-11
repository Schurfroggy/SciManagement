package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.CompanyDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.*;
import com.example.scimanagement.entity.subentity.client2link;
import com.example.scimanagement.entity.subentity.testing2link;
import com.example.scimanagement.mapper.*;
import com.example.scimanagement.service.ITestingService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.example.scimanagement.utils.QueryUtils.*;

@Service
@Repository
public class TestingServiceImpl extends ServiceImpl<testingMapper, testing> implements ITestingService {
    @Resource
    private headsMapper headsMapper;
    @Resource
    private linksMapper linksMapper;
    @Resource
    private testing2linkMapper testing2linkMapper;
    @Resource
    private sci_projectMapper sci_projectMapper;
    @Override
    public Result findById(int testingId) {
        testing testing=getById(testingId);
        if(getById(testingId)==null){
            return Result.fail("No such testing");
        }
        CompanyDTO testingDTO=new CompanyDTO();
        testingDTO.setId(testing.getTesting_id());
        testingDTO.setAddress(testing.getAddress());
        testingDTO.setHead(headsMapper.selectById(testing.getHead_id()));
        testingDTO.setName(testing.getName());
        testingDTO.setSci_project_id(QueryIdClass(sci_projectMapper,testing.getTesting_id(), Sci_project.class,"testing_id").getSci_project_id());
        List<testing2link> testing2linkList=QueryMiddleTables(testing2linkMapper,testingId,testing2link.class,"testing_id");
        if(!testing2linkList.isEmpty()){
            List<Integer> linkIdList=new ArrayList<>();
            for (testing2link testing2link : testing2linkList) {
                linkIdList.add(testing2link.getLink_id());
            }
            List<links> linksList=QueryThirdTables(linksMapper,linkIdList,links.class,"link_id");
            testingDTO.setLink(linksList);
        }
        return Result.ok(testingDTO);
    }

    @Override
    public Result save(int id, String name, String address, int headId, List<Integer> links) {
        StringBuilder WarningMsg= new StringBuilder();
        testing testing=new testing();
        if(getById(id)!=null)
            testing.setTesting_id(null);
        else testing.setTesting_id(id);
        testing.setName(name);
        testing.setAddress(address);
        if(headsMapper.selectById(headId)==null){
            testing.setHead_id(null);
            WarningMsg.append("No such head. Transferred to null. ");
        }
        else testing.setHead_id(headId);
        boolean errorflag=false;
        for(int linkId:links){
            if(linksMapper.selectById(linkId)==null){
                errorflag=true;
            }
            else{
                testing2link testing2link=new testing2link();
                testing2link.setTesting_id(id);
                testing2link.setLink_id(linkId);
                testing2linkMapper.insert(testing2link);
            }
        }
        if(errorflag){
            WarningMsg.append("Element(s) of links list is invalid. Filtered. ");
        }
        if(WarningMsg.length()==0){
            save(testing);
            return Result.ok();
        }
        save(testing);
        return Result.ok(WarningMsg);
    }

    @Override
    public Result update(int id, String name, String address, int headId, List<Integer> links) {
        StringBuilder WarningMsg= new StringBuilder();
        testing testing=getById(id);
        testing.setName(name);
        testing.setAddress(address);
        if(headsMapper.selectById(headId)==null){
            testing.setHead_id(null);
            WarningMsg.append("No such head. Transferred to null. ");
        }
        else testing.setHead_id(headId);

        QueryWrapper<testing2link> queryWrapper=new QueryWrapper<testing2link>()
                .eq("testing_id",id);
        testing2linkMapper.delete(queryWrapper);
        boolean errorflag=false;
        for(int linkId:links){
            if(linksMapper.selectById(linkId)==null){
                errorflag=true;
            }
            else{
                testing2link testing2link=new testing2link();
                testing2link.setTesting_id(id);
                testing2link.setLink_id(linkId);
                testing2linkMapper.insert(testing2link);
            }
        }
        if(errorflag){
            WarningMsg.append("Element(s) of links list is invalid. Filtered. ");
        }
        if(WarningMsg.length()==0){
            updateById(testing);
            return Result.ok();
        }
        updateById(testing);
        return Result.ok(WarningMsg);
    }

    @Override
    public Result queryTestingByName(String name) {
        QueryWrapper<testing> queryWrapper = new QueryWrapper<testing>()
                .select("*")
                .like("name",name);
        List<testing> testingList = list(queryWrapper);
        if(testingList.isEmpty())
            return Result.fail("No such testing");
        else
            return Result.ok((List<?>) testingList,(long)testingList.size());
    }
}
