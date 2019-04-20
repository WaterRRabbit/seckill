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

    private String msg;


    public Result(CodeMessageEnum codeMessageEnum){
        code = codeMessageEnum.getCode();
        msg = codeMessageEnum.getMessage();
    }

    public Result(T data){
        code = CodeMessageEnum.SUCCESS.getCode();
        msg = CodeMessageEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
