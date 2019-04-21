package com.hg.seckill.result;

/**
 * Created by YE
 * 2019-04-18 22:07
 */
public enum CodeMessageEnum {
    SUCCESS(0, "success"),
    SECKILL_WAIT(2001, "排队中"),

    SERVER_ERROR(5000, "服务端异常"),
    ACCESS_LIMIT_REACHED(5001, "访问高峰期"),

    SECKILL_REPEATE(5002, "重复秒杀"),
    SECKILL_OVER(5003, "秒杀结束"),

    NOT_LOGIN(5004, "未登录");

    private int code;
    private String message;

    CodeMessageEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
