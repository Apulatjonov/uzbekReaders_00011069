package com.example.bookstore.exceptions;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 03:53
 */

public class NotFoundException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public NotFoundException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
