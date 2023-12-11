package com.example.scimanagement.dto;

import com.example.scimanagement.entity.heads;
import com.example.scimanagement.entity.links;
import lombok.Data;

@Data
public class ContactDTO {
    private int id;

    private String name;

    private String office_number;

    private String mobile_number;

    private String email_address;

    private String type;

    public ContactDTO(links link){
        this.id = link.getLink_id();
        this.name = link.getName();
        this.office_number = link.getOffice_number();
        this.mobile_number = link.getMobile_number();
        this.email_address = link.getEmail_address();
        this.type="link";
    }

    public ContactDTO(heads head){
        this.id = head.getHead_id();
        this.name = head.getName();
        this.office_number = head.getOffice_number();
        this.mobile_number = head.getMobile_number();
        this.email_address = head.getEmail_address();
        this.type="head";
    }
}
