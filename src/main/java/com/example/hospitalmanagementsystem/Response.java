package com.example.hospitalmanagementsystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private String message;
    private boolean isSuccess;

    public Response(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

}
