package com.qf.entity;


import lombok.Data;

@Data
public class ApiResponse<T> {
    // 状态码：200 成功，500 失败
    private int code;
    // 响应数据（AI 回复内容）
    private T data;
    // 提示信息
    private String msg;

    // 成功响应静态方法
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setData(data);
        response.setMsg("success");
        return response;
    }

    // 失败响应静态方法
    public static <T> ApiResponse<T> fail(String msg) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(500);
        response.setData(null);
        response.setMsg(msg);
        return response;
    }
}

