package com.example.hospitalmanagementsystem.exceptions;

public class AlreadyExists extends RuntimeException{
    public AlreadyExists(String message) {
        super(message);
    }
}
