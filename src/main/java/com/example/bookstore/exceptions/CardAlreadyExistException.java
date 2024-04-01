package com.example.bookstore.exceptions;

import lombok.AllArgsConstructor;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:50
 */

public class CardAlreadyExistException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public CardAlreadyExistException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
