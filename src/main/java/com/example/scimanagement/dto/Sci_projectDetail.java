package com.example.scimanagement.dto;

import com.example.scimanagement.entity.*;
import com.example.scimanagement.entity.subentity.researcher2project;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Sci_projectDetail {
    private Integer id;

    private Integer client_id;

    private Integer testing_id;

    private String head_name;

    private String name;

    private String content;

    private double fund;

    private Date start_date;

    private Date end_date;
    //details:
    private client client;

    private testing testing;

    private List<partner> partners;

    private List<researcher2project> researchers;

    private List<sub_project> sub_projects;

    private List<payoffs> payoffs;
}
