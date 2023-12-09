package com.example.scimanagement.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.LabDetail;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.*;
import com.example.scimanagement.entity.subentity.lab2office;
import com.example.scimanagement.mapper.labMapper;
import com.example.scimanagement.mapper.lab2officeMapper;
import com.example.scimanagement.mapper.officeMapper;
import com.example.scimanagement.mapper.secretaryMapper;
import com.example.scimanagement.mapper.directorMapper;
import com.example.scimanagement.mapper.researcherMapper;
import com.example.scimanagement.service.ILabService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.example.scimanagement.utils.QueryUtils.*;

@Service
@Repository
public class LabServiceImpl extends ServiceImpl<labMapper, lab> implements ILabService {

    @Resource
    private lab2officeMapper lab2officeMapper;

    @Resource
    private officeMapper officeMapper;

    @Resource
    private secretaryMapper secretaryMapper;

    @Resource
    private directorMapper directorMapper;

    @Resource
    private researcherMapper researcherMapper;


    @Override
    public Result findById(int id) {
        lab labDTO = getById(id);
        LabDetail labDetail = new LabDetail();
        if (labDTO!= null) {
            labDetail.setLab_id(labDTO.getLab_id());
            labDetail.setName(labDTO.getName());
            labDetail.setIntroduction(labDTO.getIntroduction());
            if(labDTO.getDirector_id()!=null)
                labDetail.setDirector_id(labDTO.getDirector_id());
            if(labDTO.getSecretary_id()!=null)
                labDetail.setSecretary_id(labDTO.getSecretary_id());
        } else {
            return Result.fail("No such lab");
        }

        //一对多处理
        //1.查询中间表
        List <lab2office> lab2officeList = QueryMiddleTables(lab2officeMapper,id,lab2office.class,"lab_id");
        if(!lab2officeList.isEmpty()){
            //2.获得数据，拿取其中的office_id
            List<Integer> officeIdList = new ArrayList<>();
            for (lab2office lab2office : lab2officeList) {
                officeIdList.add(lab2office.getOffice_id());
            }
            //3.获得第三表的结果
            List<office> officeList=QueryThirdTables(officeMapper,officeIdList,office.class,"office_id");
            labDetail.setOfficeList(officeList);
        }
        //一对一属性
        if(labDTO.getDirector_id()!=null){
            labDetail.setDirector(QueryIdClass(directorMapper,labDetail.getDirector_id(), director.class,"director_id"));
            labDetail.getDirector().setResearcherDetail(QueryIdClass(researcherMapper,labDetail.getDirector().getResearcher_id(), researcher.class,"researcher_id"));
        }
        if(labDTO.getSecretary_id()!=null)
            labDetail.setSecretary(QueryIdClass(secretaryMapper,labDetail.getSecretary_id(),secretary.class,"secretary_id"));
        return Result.ok(labDetail);
    }

    @Override
    public Result update(int id, String name, String introduction, int directorId, int secretaryId) {
        StringBuilder WarningMsg = new StringBuilder();
        lab labDTO = getById(id);
        labDTO.setName(name);
        labDTO.setIntroduction(introduction);
        if(directorMapper.selectById(directorId)==null){
            WarningMsg.append("No such director: ").append(directorId).append(".");
            labDTO.setDirector_id(null);
        }
        else{
            //遍历所有的lab，查看是否已经有这个director_id
            for (lab lab : list()) {
                if(lab.getDirector_id()!=null&&lab.getDirector_id()==directorId&&lab.getLab_id()!=id){
                    WarningMsg.append("Director ").append(directorId).append(" was employed by lab: ").append(lab.getLab_id()).append(".");
                    labDTO.setDirector_id(null);
                }
            }
        }
        //触发director更新
        //1.更新旧的director
        if(labDTO.getDirector_id()!=null&&directorId!=labDTO.getDirector_id()){
            if(directorMapper.selectById(labDTO.getDirector_id())!=null){
                director directorDTO = directorMapper.selectById(labDTO.getDirector_id());
                directorDTO.setLab_id(null);
                directorMapper.updateById(directorDTO);
            }
            labDTO.setDirector_id(directorId);
        }
        //2.更新新的director
        if(directorMapper.selectById(directorId)!=null){
            director directorDTO = directorMapper.selectById(directorId);
            directorDTO.setLab_id(id);
            directorMapper.updateById(directorDTO);
        }

        if(secretaryMapper.selectById(secretaryId)==null){
            WarningMsg.append("No such secretary: ").append(secretaryId).append(".");
            labDTO.setSecretary_id(null);
        }
        //触发secretary更新
        //1.更新旧的secretary
        if(labDTO.getSecretary_id()!=null&&secretaryId!=labDTO.getSecretary_id()){
            if(secretaryMapper.selectById(labDTO.getSecretary_id())!=null){
                secretary secretaryDTO = secretaryMapper.selectById(labDTO.getSecretary_id());
                secretaryDTO.setLab_id(null);
                secretaryMapper.updateById(secretaryDTO);
            }
            labDTO.setSecretary_id(secretaryId);
        }
        //2.更新新的secretary
        if(secretaryMapper.selectById(secretaryId)!=null){
            secretary secretaryDTO = secretaryMapper.selectById(secretaryId);
            secretaryDTO.setLab_id(id);
            secretaryMapper.updateById(secretaryDTO);
        }
        updateById(labDTO);
        if(WarningMsg.length()>0)
            return Result.ok(WarningMsg.toString());
        return Result.ok();
    }

    @Override
    public Result save(int labId, String name, String introduction, int directorId, int secretaryId) {
        StringBuilder WarningMsg = new StringBuilder();
        lab labDTO = new lab();
        labDTO.setName(name);
        labDTO.setIntroduction(introduction);
        labDTO.setDirector_id(null);
        labDTO.setSecretary_id(null);
        //先查lab的id是否存在，如果存在则先更新再触发director和secretary更新
        if(findById(labId)!=null){
            WarningMsg.append("Id duplicated. ");
            labDTO.setLab_id(null);
        }
        else labDTO.setLab_id(labId);
        //先保存
        save(labDTO);
        lab labDTO_ = queryLabByNameOne(name);
        //直接调用update触发director和secretary更新
        Result result=update(labDTO_.getLab_id(),name,introduction,directorId,secretaryId);
        WarningMsg.append(result.getMsg());
        return Result.ok(WarningMsg.toString());
    }

    @Override
    public Result queryLabByName(String name) {
        QueryWrapper<lab> queryWrapper = new QueryWrapper<lab>()
                .select("*")
                .like("name",name);
        List<lab> labList = list(queryWrapper);
        if(labList.isEmpty())
            return Result.fail("No such lab");
        else
            return Result.ok((List<?>) labList,(long)labList.size());
    }

    public lab queryLabByNameOne(String name) {
        QueryWrapper<lab> queryWrapper = new QueryWrapper<lab>()
                .select("*")
                .like("name",name);
        lab labDTO = list(queryWrapper).get(0);
        return labDTO;
    }

}

//生成测试数据


