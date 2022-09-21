package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.EntityBook;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class StorageBooks {
    Storage<EntityBook> storage;

    StorageBooks(Storage<EntityBook> storage) {
        this.storage = storage;
    }

    public Long generateId() {
        return storage.generateId();
    }

    public EntityBook save(EntityBook book) {
        return storage.saveEntity(book);
    }

    public List<EntityBook> getByUserId(Long id) {
        return getAllBooks()
                .stream()
                .filter(book -> Objects.equals(book.getUserId(), id))
                .toList();
    }

    public Collection<EntityBook> getAllBooks() {
        return storage.getValues();
    }

    public EntityBook updateBook(EntityBook book) {
        EntityBook bookUpdate = storage.getById(book.getId());
        bookUpdate.update(book);
        return bookUpdate;
    }

    public void deleteBookById(Long id) {
        storage.deleteById(id);
    }

    public void deleteByUserId(Long id) {
        getByUserId(id).stream()
                .map(EntityBook::getUserId)
                .forEach(this::deleteBookById);
    }
}
