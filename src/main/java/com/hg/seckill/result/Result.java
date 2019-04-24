package com.hg.seckill.result;

import lombok.Data;

/**
 * Created by YE
 * 2019-04-18 21:42
 */
@Data
public class Result<T> {

    private int code;

    private T data;

    private String message;


    public Result(CodeMessageEnum codeMessageEnum){
        code = codeMessageEnum.getCode();
        message = codeMessageEnum.getMessage();
    }

    public Result(T data){
        code = CodeMessageEnum.SUCCESS.getCode();
        message = CodeMessageEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public Result(int code, String message){
        this.code = code;
        this.message = message;
    }
}
