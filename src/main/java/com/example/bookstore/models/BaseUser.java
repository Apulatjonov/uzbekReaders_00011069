package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 11:29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUser {
    private Long id;
    @NotNull
    private String email;
    private String firstName;
    private String lastName;
    @NotNull
    private String password;
}
