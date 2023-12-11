package com.example.scimanagement.dto;

import com.example.scimanagement.entity.heads;
import com.example.scimanagement.entity.links;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyDTO {
    private Integer id;

    private Integer sci_project_id;

    private String name;

    private String address;

    private heads head;

    private List<links> link=new ArrayList<>();

}
