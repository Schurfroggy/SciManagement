package com.example.scimanagement.dto;

import com.example.scimanagement.entity.office;
import com.example.scimanagement.entity.secretary;
import com.example.scimanagement.entity.director;

import com.example.scimanagement.entity.sub_project;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class LabDetail {

    private Integer lab_id;

    private String name;

    private String introduction;

    private int director_id;

    private int secretary_id;

    private List<office> officeList=new ArrayList<>();

    private director director;

    private secretary secretary;

}
