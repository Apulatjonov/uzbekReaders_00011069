package com.example.bookstore.exceptions;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 14:30
 */

public class InvalidEmailAddressException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public InvalidEmailAddressException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

}
