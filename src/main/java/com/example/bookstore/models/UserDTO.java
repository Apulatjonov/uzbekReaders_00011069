package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 11:32
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseUser{
    private String cardPan;
    private String cardDetail;
    private String address;
    private String phoneNumber;
    private List<BaseBook> books;
}
