package com.xms.common;

import lombok.Data;

@Data
public class Result {
    private int code;//编码
    private String msg;//是否成功
    private Long total;//记录数
    private Object data;//数据

    //后端返回数据的封装
    private static Result result(int code, String msg, Long total, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);
        return result;
    }

    //失败
    public static Result fail() {
        return result(400, "失败", 0L, null);
    }

    public static Result suc() {
        return result(200, "成功", 0L, null);
    }

    //成功返回data
    public static Result suc(Object data) {
        return result(200, "成功", 0L, data);
    }

    //成功返回data+条数
    public static Result suc(Object data, Long total) {
        return result(200, "成功", total, data);
    }

}
