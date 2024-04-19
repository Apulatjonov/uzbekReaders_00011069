package com.example.bookstore.exceptions;

import lombok.Data;
import lombok.Getter;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 14:39
 */

@Getter
public class PasswordIncorrectException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public PasswordIncorrectException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
