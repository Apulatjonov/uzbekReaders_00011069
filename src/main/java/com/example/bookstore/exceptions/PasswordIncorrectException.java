package com.example.bookstore.exceptions;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 14:39
 */

public class PasswordIncorrectException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public PasswordIncorrectException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
