package com.example.bookstore.entities;

import com.example.bookstore.models.BaseUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 13:45
 */

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user")
    @Column(name = "cards")
    private Set<Card> cards;
    @Column(name = "paid_till")
    private LocalDateTime paidTill;
    @ManyToMany
    private Set<Book> books;

    public User(BaseUser userDTO) {
        this.email = userDTO.getEmail();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.password = userDTO.getPassword();
    }

    public BaseUser toBaseUser() {
        BaseUser user = new BaseUser();
        user.setEmail(this.getEmail());
        user.setId(this.getId());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        return user;
    }
}
