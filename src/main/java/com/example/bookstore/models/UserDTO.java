package com.example.bookstore.models;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 11:32
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseUser {
    private String cardPan;
    private String cardDetail;
    private String address;
    private String phoneNumber;
    private boolean subscriptionActive = false;
    private String paidTill;
    private List<BaseBook> books;

    public UserDTO fromEntity(User user) {
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setBooks(user.getBooks().stream()
                .map(Book::toBaseBook)
                .collect(Collectors.toList()));
        if (user.getPaidTill().isBefore(LocalDateTime.now())){
            this.setSubscriptionActive(false);
        }else{
            this.setSubscriptionActive(true);
        }
        this.setPaidTill(user.getPaidTill().getDayOfMonth() + "-" + user.getPaidTill().getMonth().name() + " " + user.getPaidTill().getYear());
        return this;
    }
}
