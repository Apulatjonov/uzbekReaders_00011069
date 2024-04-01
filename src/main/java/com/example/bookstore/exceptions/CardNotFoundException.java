package com.example.bookstore.exceptions;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 02:06
 */

public class CardNotFoundException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public CardNotFoundException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
