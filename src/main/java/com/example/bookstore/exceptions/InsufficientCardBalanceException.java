package com.example.bookstore.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 03:05
 */

@Getter
@Setter
public class InsufficientCardBalanceException extends RuntimeException{
    private final String msg;
    private final Integer code;

    public InsufficientCardBalanceException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
