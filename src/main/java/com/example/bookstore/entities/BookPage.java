package com.example.bookstore.entities;

import com.example.bookstore.models.PageDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/20/2024 23:02
 */


@Document(collection = "pages")
@Getter
@Setter
@NoArgsConstructor
public class BookPage {
    @Id
    private String _id;

    private String content;

    private Long bookId;
    private Long pageNumber;

    public PageDTO toDTO() {
        PageDTO dto = new PageDTO();
        dto.setId(this.get_id());
        dto.setContent(this.getContent());
        dto.setPageNumber(this.getPageNumber());
        dto.setBookId(this.getBookId());
        return dto;
    }
}
