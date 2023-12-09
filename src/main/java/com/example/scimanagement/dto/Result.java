package com.example.scimanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String Msg;
    private Object data;
    private Long total;

    public static Result ok(){
        return new Result(true, null, null, null);
    }
    public static Result ok(Object data){
        return new Result(true, null, data, null);
    }
    public static Result ok(String warningMsg){return new Result(true, warningMsg, null, null);}
    public static Result ok(Object data,String warningMsg){return new Result(true, warningMsg, data, null);}
    public static Result ok(List<?> data, Long total,String warningMsg){return new Result(true, warningMsg, data, total);}
    public static Result ok(List<?> data, Long total){return new Result(true, null, data, total);}
    public static Result fail(String errorMsg){
        return new Result(false, errorMsg, null, null);
    }
}

