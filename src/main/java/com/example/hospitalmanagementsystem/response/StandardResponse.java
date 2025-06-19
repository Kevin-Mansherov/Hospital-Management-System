package com.example.hospitalmanagementsystem.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse {
    private String status;
    private Object data;
    private Object error;
    private LocalDateTime timestamp;

    public StandardResponse(String status, Object data, Object error) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
}
