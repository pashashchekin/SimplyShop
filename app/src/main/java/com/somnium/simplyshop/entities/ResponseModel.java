package com.somnium.simplyshop.entities;

import com.somnium.simplyshop.enums.ServerStatus;

public class ResponseModel <T>{
    private T data;
    private String message;
    private ServerStatus status;


    public ServerStatus getStatus() {
        return status;
    }

    public void setStatus(ServerStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}