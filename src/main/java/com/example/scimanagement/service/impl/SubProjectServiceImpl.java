package com.example.scimanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scimanagement.dto.Result;
import com.example.scimanagement.entity.sub_project;
import com.example.scimanagement.entity.subentity.project2sub;
import com.example.scimanagement.mapper.project2subMapper;
import com.example.scimanagement.mapper.sci_projectMapper;
import com.example.scimanagement.mapper.sub_projectMapper;
import com.example.scimanagement.service.ISubProjectService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.example.scimanagement.utils.QueryUtils.QueryIdClass;

@Service
@Repository
public class SubProjectServiceImpl extends ServiceImpl<sub_projectMapper, sub_project> implements ISubProjectService {
    @Resource
    private sci_projectMapper sci_projectMapper;

    @Resource
    private project2subMapper project2subMapper;
    @Override
    public Result querySubProjectBySciProjectId(int sciProjectId) {
        QueryWrapper<project2sub> queryWrapper=new QueryWrapper<project2sub>()
                .eq("sci_project_id",sciProjectId);
        List<project2sub> project2subs=project2subMapper.selectList(queryWrapper);
        if(project2subs.isEmpty())
            return Result.fail("Sub not found or project has no subs. ");
        List<Integer> subIds=new ArrayList<>();
        for(project2sub project2sub:project2subs)
            subIds.add(project2sub.getSub_project_id());
        QueryWrapper<sub_project> queryWrapper1=new QueryWrapper<sub_project>()
                .in("sub_project_id",subIds);
        List<sub_project> sub_projects=list(queryWrapper1);
        return Result.ok(sub_projects,(long)sub_projects.size());
    }

    @Override
    public Result findById(int id) {
        int sci_project_id=QueryIdClass(project2subMapper,id, project2sub.class,"sub_project_id").getSci_project_id();
        return Result.ok(sci_project_id);
    }
}
