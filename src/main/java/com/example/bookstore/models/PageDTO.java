package com.example.bookstore.models;

import com.example.bookstore.entities.BookPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/24/2024 16:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO implements Serializable {
    private String id;
    private String content;
    private Long pageNumber;
    private Long bookId;
    private Long totalPages;
    private String bookTitle;
    private String authorName;
    private Long authorId;

    public PageDTO(BookPage page) {
        this.setId(page.get_id());
        this.setContent(page.getContent());
        this.setPageNumber(page.getPageNumber());
        this.setBookId(page.getBookId());
    }
}
