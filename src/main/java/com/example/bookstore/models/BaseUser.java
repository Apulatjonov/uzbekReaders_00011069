package com.example.bookstore.models;

import lombok.*;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 11:29
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseUser {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
