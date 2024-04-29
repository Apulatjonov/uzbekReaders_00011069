package com.example.bookstore.models;

import com.example.bookstore.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/28/2024 17:44
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String fullName;
    private Set<BookDTO> books;

    public AuthorDTO(Author ent){
        this.setId(ent.getId());
        this.setFullName(ent.getFullName());
    }
}
