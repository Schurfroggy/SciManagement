package com.example.scimanagement.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.CompanyDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.Sci_project;
import com.example.scimanagement.entity.client;
import com.example.scimanagement.entity.links;
import com.example.scimanagement.entity.subentity.client2link;
import com.example.scimanagement.mapper.*;
import com.example.scimanagement.service.IClientService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.example.scimanagement.utils.QueryUtils.*;

@Service
@Repository
public class ClientServiceImpl extends ServiceImpl<clientMapper, client> implements IClientService {

    @Resource
    private headsMapper headsMapper;

    @Resource
    private linksMapper linksMapper;

    @Resource
    private client2linkMapper client2linkMapper;

    @Resource
    private sci_projectMapper sci_projectMapper;
    @Override
    public Result findById(int clientId) {
        client client=getById(clientId);
        if(getById(clientId)==null){
            return Result.fail("No such client");
        }
        CompanyDTO clientDTO=new CompanyDTO();
        clientDTO.setId(client.getClient_id());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setHead(headsMapper.selectById(client.getHead_id()));
        clientDTO.setName(client.getName());
        clientDTO.setSci_project_id(QueryIdClass(sci_projectMapper,client.getClient_id(), Sci_project.class,"client_id").getSci_project_id());
        List<client2link> client2linkList=QueryMiddleTables(client2linkMapper,clientId,client2link.class,"client_id");
        if(!client2linkList.isEmpty()){
            List<Integer> linkIdList=new ArrayList<>();
            for (client2link client2link : client2linkList) {
                linkIdList.add(client2link.getLink_id());
            }
            List<links> linksList=QueryThirdTables(linksMapper,linkIdList,links.class,"link_id");
            clientDTO.setLink(linksList);
        }
        return Result.ok(clientDTO);
    }

    @Override
    public Result save(int id, String name, String address, int headId,List<Integer> links) {
        StringBuilder WarningMsg= new StringBuilder();
        client client=new client();
        if(getById(id)!=null)
            client.setClient_id(null);
        else client.setClient_id(id);
        client.setName(name);
        client.setAddress(address);
        if(headsMapper.selectById(headId)==null){
            client.setHead_id(null);
            WarningMsg.append("No such head. Transferred to null. ");
        }
        else client.setHead_id(headId);
        boolean errorflag=false;
        for(int linkId:links){
            if(linksMapper.selectById(linkId)==null){
                errorflag=true;
            }
            else{
                client2link client2link=new client2link();
                client2link.setClient_id(id);
                client2link.setLink_id(linkId);
                client2linkMapper.insert(client2link);
            }
        }
        if(errorflag){
            WarningMsg.append("Element(s) of links list is invalid. Filtered. ");
        }
        if(WarningMsg.length()==0){
            save(client);
            return Result.ok();
        }
        save(client);
        return Result.ok(WarningMsg);
    }

    @Override
    public Result update(int id, String name, String address, int headId, List<Integer> links) {
        StringBuilder WarningMsg= new StringBuilder();
        client client=getById(id);
        client.setName(name);
        client.setAddress(address);
        if(headsMapper.selectById(headId)==null){
            client.setHead_id(null);
            WarningMsg.append("No such head. Transferred to null. ");
        }
        else client.setHead_id(headId);

        QueryWrapper<client2link> queryWrapper=new QueryWrapper<client2link>()
                .eq("client_id",id);
        client2linkMapper.delete(queryWrapper);
        boolean errorflag=false;
        for(int linkId:links){
            if(linksMapper.selectById(linkId)==null){
                errorflag=true;
            }
            else{
                client2link client2link=new client2link();
                client2link.setClient_id(id);
                client2link.setLink_id(linkId);
                client2linkMapper.insert(client2link);
            }
        }
        if(errorflag){
            WarningMsg.append("Element(s) of links list is invalid. Filtered. ");
        }
        if(WarningMsg.length()==0){
            updateById(client);
            return Result.ok();
        }
        updateById(client);
        return Result.ok(WarningMsg);
    }
}
