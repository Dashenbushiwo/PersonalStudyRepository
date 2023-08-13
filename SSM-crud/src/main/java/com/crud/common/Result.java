package com.crud.common;

import lombok.Data;

@Data
public class Result {

    private boolean flag;
    private String message;
    private Object data;

    private Result() {}

    public static Result of(Object data){
        return Result.of(true, null, data);
    }

    public static Result of(boolean flag, String message){
        return Result.of(flag, message, null);
    }

    public static Result of(boolean flag, String message, Object data) {
        Result result = new Result();
        result.setFlag(flag);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
