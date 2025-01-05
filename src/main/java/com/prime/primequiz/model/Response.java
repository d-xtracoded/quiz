package com.prime.primequiz.model;

import lombok.Data;

@Data
public class Response {

    private Integer id;
    private String response;

    public Integer getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public Response(Integer id, String response) {
        this.id = id;
        this.response = response;
    }
}
