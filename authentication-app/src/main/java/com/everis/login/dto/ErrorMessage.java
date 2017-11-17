package com.everis.login.dto;

import javax.ws.rs.core.Response;

public class ErrorMessage {
    private Response.Status code;
    private String message;

    public ErrorMessage(Response.Status code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response.Status getCode() {
        return code;
    }

    public void setCode(Response.Status code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
