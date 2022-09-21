package com.edu.ulab.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityBook extends EntityStorage {

    private Long id;
    private String title;
    private String author;
    private long pageCount;
    private Long userId;

    public void update(EntityBook book){
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.userId = book.getUserId();
        this.pageCount = book.getPageCount();
        this.id = book.getId();
    }
}
