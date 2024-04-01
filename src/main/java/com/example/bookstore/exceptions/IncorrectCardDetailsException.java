package com.example.bookstore.exceptions;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:55
 */

public class IncorrectCardDetailsException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public IncorrectCardDetailsException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
