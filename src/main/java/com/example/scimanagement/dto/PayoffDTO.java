package com.example.scimanagement.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class PayoffDTO {

    private Integer payoff_id;

    private Integer sci_project_id;

    private String type;

    private String name;

    private Date date;

    private Integer rank;
}
