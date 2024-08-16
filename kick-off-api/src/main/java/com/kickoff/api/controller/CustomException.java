package com.kickoff.api.controller;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }
}
