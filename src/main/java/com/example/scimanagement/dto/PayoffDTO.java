package com.example.scimanagement.dto;


import com.example.scimanagement.entity.Sci_project;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class PayoffDTO {

    private Integer payoff_id;

    private Integer sci_project_id;

    private Sci_project sci_project;

    private String type;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer ranks;
}
