package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.dto.Sci_projectDetail;
import com.example.scimanagement.entity.*;
import com.example.scimanagement.entity.subentity.project2partner;
import com.example.scimanagement.entity.subentity.project2payoffs;
import com.example.scimanagement.entity.subentity.project2sub;
import com.example.scimanagement.entity.subentity.researcher2project;
import com.example.scimanagement.mapper.*;
import com.example.scimanagement.service.ISci_projectService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static com.example.scimanagement.utils.QueryUtils.*;

@Service
@Repository
public class Sci_projectServiceImpl extends ServiceImpl<sci_projectMapper, Sci_project> implements ISci_projectService{
    @Resource
    private clientMapper clientMapper;
    @Resource
    private testingMapper testingMapper;
    @Resource
    private project2partnerMapper sci_project2partnerMapper;
    @Resource
    private partnerMapper partnerMapper;
    @Resource
    private researcher2projectMapper researcher2projectMapper;
    @Resource
    private project2subMapper project2subMapper;
    @Resource
    private sub_projectMapper sub_projectMapper;
    @Resource
    private project2payoffsMapper project2payoffsMapper;
    @Resource
    private payoffsMapper payoffsMapper;
    @Override
    public Result findById(int id) {
        Sci_project sci_project = getById(id);
        Sci_projectDetail sci_projectDetail = new Sci_projectDetail();
        if(sci_project!=null){
            sci_projectDetail.setId(sci_project.getSci_project_id());
            sci_projectDetail.setClient_id(sci_project.getClient_id());
            sci_projectDetail.setTesting_id(sci_project.getTesting_id());
            sci_projectDetail.setHead_name(sci_project.getHead_name());
            sci_projectDetail.setName(sci_project.getName());
            sci_projectDetail.setContent(sci_project.getContent());
            sci_projectDetail.setFund(sci_project.getFund());
            sci_projectDetail.setStart_date(sci_project.getStart_date());
            sci_projectDetail.setEnd_date(sci_project.getEnd_date());
        }
        else return Result.fail("No such project");
        //查询关联表
        //client
        sci_projectDetail.setClient(QueryIdClass(clientMapper,sci_projectDetail.getClient_id(), client.class,"client_id"));
        //testing
        sci_projectDetail.setTesting(QueryIdClass(testingMapper,sci_projectDetail.getTesting_id(), testing.class,"testing_id"));
        //partner
        List<project2partner> project2partners = QueryMiddleTables(sci_project2partnerMapper,id,project2partner.class,"sci_project_id");
        if(!project2partners.isEmpty()){
            List<Integer> partnerIds = new ArrayList<>();
            for(project2partner project2partner : project2partners){
                partnerIds.add(project2partner.getPartner_id());
            }
            List<partner> partners = QueryThirdTables(partnerMapper,partnerIds,partner.class,"partner_id");
            sci_projectDetail.setPartners(partners);
        }
        //researcher
        sci_projectDetail.setResearchers(QueryIdClassList(researcher2projectMapper,id,researcher2project.class,"sci_project_id"));
        //sub_project
        List<project2sub> project2subs = QueryMiddleTables(project2subMapper,id, project2sub.class,"sci_project_id");
        if(!project2subs.isEmpty()){
            List<Integer> subProjectIds = new ArrayList<>();
            for(project2sub project2sub : project2subs){
                subProjectIds.add(project2sub.getSub_project_id());
            }
            List<sub_project> sub_projects = QueryThirdTables(sub_projectMapper,subProjectIds,sub_project.class,"sub_project_id");
            sci_projectDetail.setSub_projects(sub_projects);
        }
        //payoffs
        List<project2payoffs> project2payoffs = QueryMiddleTables(project2payoffsMapper,id,project2payoffs.class,"sci_project_id");
        if(!project2payoffs.isEmpty()){
            List<Integer> payoffIds = new ArrayList<>();
            for(project2payoffs project2payoff : project2payoffs){
                payoffIds.add(project2payoff.getPayoff_id());
            }
            List<payoffs> payoffs = QueryThirdTables(payoffsMapper,payoffIds,payoffs.class,"payoff_id");
            sci_projectDetail.setPayoffs(payoffs);
        }

        return Result.ok(sci_projectDetail);
    }

    @Override
    public Result save(int sciProjectId, int clientId, int testingId, String headName, String name, String content, double fund, Date startDate, Date endDate) {
        Sci_project sci_project = new Sci_project();
        sci_project.setClient_id(clientId);
        sci_project.setTesting_id(testingId);
        sci_project.setHead_name(headName);
        sci_project.setName(name);
        sci_project.setContent(content);
        sci_project.setFund(fund);
        sci_project.setStart_date(startDate);
        sci_project.setEnd_date(endDate);
        if(clientMapper.selectById(clientId)==null)
            return Result.fail("No such client:"+clientId);
        else if(testingMapper.selectById(testingId)==null)
            return Result.fail("No such testing:"+testingId);
        else{
            for(Sci_project sci_project1 : list()){
                if(sci_project1.getClient_id()==clientId)
                    return Result.fail("Client "+ clientId + " is the unique client of project:"+sci_project1.getSci_project_id());
                if(sci_project1.getTesting_id()==testingId){
                    return Result.fail("Testing "+ testingId + " is the unique testing of project:"+sci_project1.getSci_project_id());
                }
            }
        }
        if(getById(sciProjectId)== null){
            sci_project.setSci_project_id(sciProjectId);
            save(sci_project);
            return Result.ok();
        }
        save(sci_project);
        return Result.ok("Id duplicated");
    }

    @Override
    public Result update(int sciProjectId, int clientId, int testingId, String headName, String name, String content, double fund, Date startDate, Date endDate) {
        if(clientMapper.selectById(clientId)==null)
            return Result.fail("No such client:"+clientId);
        if(testingMapper.selectById(testingId)==null)
            return Result.fail("No such testing:"+testingId);
        else{
            for(Sci_project sci_project1 : list()){
                if(sci_project1.getClient_id()==clientId)
                    return Result.fail("Client "+ clientId + " is the unique client of project:"+sci_project1.getSci_project_id());
                if(sci_project1.getTesting_id()==testingId){
                    return Result.fail("Testing "+ testingId + " is the unique testing of project:"+sci_project1.getSci_project_id());
                }
            }
        }
        update(new UpdateWrapper<Sci_project>().eq("sci_project_id",sciProjectId)
                .set("client_id",clientId)
                .set("testing_id",testingId)
                .set("head_name",headName)
                .set("name",name)
                .set("content",content)
                .set("fund",fund)
                .set("start_date",startDate)
                .set("end_date",endDate));
        return Result.ok();
    }

    @Override
    public Result queryProjectByName(String name) {
        QueryWrapper<Sci_project> queryWrapper = new QueryWrapper<Sci_project>()
                .select("*")
                .like("name",name);
        List<Sci_project> ProjectList = list(queryWrapper);
        if(ProjectList.isEmpty())
            return Result.fail("No such project");
        else
            return Result.ok((List<?>) ProjectList,(long)ProjectList.size());
    }

    @Override
    public Result queryProjectByContent(String content) {
        QueryWrapper<Sci_project> queryWrapper = new QueryWrapper<Sci_project>()
                .select("*")
                .like("content",content);
        List<Sci_project> ProjectList = list(queryWrapper);
        if(ProjectList.isEmpty())
            return Result.fail("No such project");
        else
            return Result.ok((List<?>) ProjectList,(long)ProjectList.size());
    }


}



