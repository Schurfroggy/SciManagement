package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.CompanyDTO;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.links;
import com.example.scimanagement.entity.partner;
import com.example.scimanagement.entity.subentity.partner2link;
import com.example.scimanagement.entity.subentity.project2partner;
import com.example.scimanagement.mapper.*;
import com.example.scimanagement.service.IPartnerService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.example.scimanagement.utils.QueryUtils.*;

@Service
@Repository
public class PartnerServiceImpl extends ServiceImpl<partnerMapper, partner> implements IPartnerService {
    @Resource
    private headsMapper headsMapper;
    @Resource
    private linksMapper linksMapper;
    @Resource
    private partner2linkMapper partner2linkMapper;
    @Resource
    private project2partnerMapper project2partnerMapper;

    @Override
    public Result findById(int partnerId) {
        partner partner=getById(partnerId);
        if(getById(partnerId)==null){
            return Result.fail("No such partner");
        }
        CompanyDTO partnerDTO=new CompanyDTO();
        partnerDTO.setId(partner.getPartner_id());
        partnerDTO.setAddress(partner.getAddress());
        partnerDTO.setHead(headsMapper.selectById(partner.getHead_id()));
        partnerDTO.setName(partner.getName());
        partnerDTO.setSci_project_id(QueryIdClass(project2partnerMapper,partner.getPartner_id(), project2partner.class,"partner_id").getSci_project_id());
        List<partner2link> partner2linkList=QueryMiddleTables(partner2linkMapper,partnerId,partner2link.class,"partner_id");
        if(!partner2linkList.isEmpty()){
            List<Integer> linkIdList=new ArrayList<>();
            for (partner2link partner2link : partner2linkList) {
                linkIdList.add(partner2link.getLink_id());
            }
            List<links> linksList=QueryThirdTables(linksMapper,linkIdList,links.class,"link_id");
            partnerDTO.setLink(linksList);
        }
        return Result.ok(partnerDTO);
    }

    @Override
    public Result save(int id, String name, String address, int headId, List<Integer> links) {
        StringBuilder WarningMsg= new StringBuilder();
        partner partner=new partner();
        if(getById(id)!=null)
            partner.setPartner_id(null);
        else partner.setPartner_id(id);
        partner.setName(name);
        partner.setAddress(address);
        if(headsMapper.selectById(headId)==null){
            partner.setHead_id(null);
            WarningMsg.append("No such head. Transferred to null. ");
        }
        else partner.setHead_id(headId);
        boolean errorflag=false;
        for(int linkId:links){
            if(linksMapper.selectById(linkId)==null){
                errorflag=true;
            }
            else{
                partner2link partner2link=new partner2link();
                partner2link.setPartner_id(id);
                partner2link.setLink_id(linkId);
                partner2linkMapper.insert(partner2link);
            }
        }
        if(errorflag){
            WarningMsg.append("Element(s) of links list is invalid. Filtered. ");
        }
        if(WarningMsg.length()==0){
            save(partner);
            return Result.ok();
        }
        save(partner);
        return Result.ok(WarningMsg);
    }

    @Override
    public Result update(int id, String name, String address, int headId, List<Integer> links) {
        StringBuilder WarningMsg= new StringBuilder();
        partner partner=getById(id);
        partner.setName(name);
        partner.setAddress(address);
        if(headsMapper.selectById(headId)==null){
            partner.setHead_id(null);
            WarningMsg.append("No such head. Transferred to null. ");
        }
        else partner.setHead_id(headId);

        QueryWrapper<partner2link> queryWrapper=new QueryWrapper<partner2link>()
                .eq("partner_id",id);
        partner2linkMapper.delete(queryWrapper);
        boolean errorflag=false;
        for(int linkId:links){
            if(linksMapper.selectById(linkId)==null){
                errorflag=true;
            }
            else{
                partner2link partner2link=new partner2link();
                partner2link.setPartner_id(id);
                partner2link.setLink_id(linkId);
                partner2linkMapper.insert(partner2link);
            }
        }
        if(errorflag){
            WarningMsg.append("Element(s) of links list is invalid. Filtered. ");
        }
        if(WarningMsg.length()==0){
            updateById(partner);
            return Result.ok();
        }
        updateById(partner);
        return Result.ok(WarningMsg);
    }

    @Override
    public Result queryPartnerByName(String name) {
        QueryWrapper<partner> queryWrapper = new QueryWrapper<partner>()
                .select("*")
                .like("name",name);
        List<partner> partnerList = list(queryWrapper);
        if(partnerList.isEmpty())
            return Result.fail("No such partner");
        else
            return Result.ok((List<?>) partnerList,(long)partnerList.size());
    }
}
