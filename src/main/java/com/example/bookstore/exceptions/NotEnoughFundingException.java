package com.example.bookstore.exceptions;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/29/2024 16:28
 */

public class NotEnoughFundingException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public NotEnoughFundingException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

}