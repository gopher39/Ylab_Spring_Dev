package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class StorageBooks {
    Storage<BookEntity> storage;

    StorageBooks(Storage<BookEntity> storage) {
        this.storage = storage;
    }

    public Long generateId() {
        return storage.generateId();
    }

    public BookEntity save(BookEntity book) {
        return storage.saveEntity(book);
    }

    public List<BookEntity> getByUserId(Long id) {
        return getAllBooks()
                .stream()
                .filter(book -> Objects.equals(book.getUserId(), id))
                .toList();
    }

    public Collection<BookEntity> getAllBooks() {
        return storage.getValues();
    }

    public BookEntity updateBook(BookEntity book) {
        BookEntity bookUpdate = storage.getById(book.getId());
        bookUpdate.update(book);
        return bookUpdate;
    }

    public void deleteBookById(Long id) {
        storage.deleteById(id);
    }

    public void deleteByUserId(Long id) {
        getByUserId(id).stream().map(BookEntity::getUserId).forEach(this::deleteBookById);
    }
}
