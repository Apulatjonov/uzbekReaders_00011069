package com.example.bookstore.exceptions;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/09/2024 17:18
 */

public class AlreadyRatedException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public AlreadyRatedException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
