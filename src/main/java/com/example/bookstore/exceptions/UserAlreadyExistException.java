package com.example.bookstore.exceptions;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 14:18
 */

public class UserAlreadyExistException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public UserAlreadyExistException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
