package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Storage {
    private long userIncrement = 1;
    private long bookIncrement = 1;
    private final Map<Long, User> userRepository = new HashMap<>();
    private final Map<Long, Book> bookRepository = new HashMap<>();

    public User getUser(Long id) {

        User user = userRepository.get(id);
        if (user != null) {
            user.setBookList(user.getBookList()
                    .stream().filter(n -> getBookById(n) != null)
                    .toList());
        }
        return user;
    }

    public User saveUser(User user) {
        if (user.getId() != null) {
            User userFound = getUser(user.getId());
            if (userFound != null) {
                return user;
            }
        }
        user.setId(userIncrement);
        user.setBookList(new ArrayList<>());
        userRepository.put(userIncrement, user);
        userIncrement++;
        return user;
    }

    public User updateUser(User user) {
        if (user.getId() == null) {
            throw new NotFoundException(String.format("User with id:%d not found", user.getId()));
        } else {
            User userFound = getUser(user.getId());
            if (userFound == null) {
                throw new NotFoundException(String.format("User with id:%d not found", user.getId()));
            }
            if (user.getAge() == 0) {
                user.setAge(userFound.getAge());
            }
            if (user.getTitle() == null) {
                user.setTitle(userFound.getTitle());
            }
            if (user.getFullName() == null) {
                user.setFullName(userFound.getFullName());
            }
            user.setBookList(userFound.getBookList());
        }
        userRepository.remove(user.getId());
        userRepository.put(user.getId(), user);
        return user;
    }

    public void deleteUser(Long id) {
        User user = userRepository.get(id);
        if (user != null) {
            user.getBookList()
                    .stream()
                    .map(bookRepository::get)
                    .filter(Objects::nonNull)
                    .forEach(n -> n.setUserId(null));
            userRepository.remove(id);
        }
    }

    public Book getBookById(Long id) {
        return bookRepository.get(id);
    }

    public Book saveBook(Book book) {
        if (book.getId() != null) {
            Book bookFound = getBookById(book.getId());
            if (bookFound != null && bookFound.getId() == bookIncrement) {
                bookIncrement++;
                return updateBook(bookFound);
            }
        }
        book.setId(bookIncrement);
        List<Long> list = new ArrayList<>(userRepository.get(book.getUserId()).getBookList());
        list.add(bookIncrement);
        userRepository.get(book.getUserId())
                .setBookList(list);
        bookRepository.put(bookIncrement, book);
        bookIncrement++;
        return book;
    }

    public Book updateBook(Book book) {
        boolean flag = getUser(book.getUserId()).getBookList()
                .stream()
                .anyMatch(n -> Objects.equals(n, book.getId()));
        if (flag) {
            Book bookFound = getBookById(book.getId());
            if (bookFound != null) {
                if (book.getAuthor() == null) {
                    book.setAuthor(bookFound.getAuthor());
                }
                if (book.getTitle() == null) {
                    book.setTitle(bookFound.getTitle());
                }
                if (book.getPageCount() == 0) {
                    book.setPageCount(bookFound.getPageCount());
                }
                if (book.getUserId() == null) {
                    book.setUserId(bookFound.getUserId());
                }
            }
        } else {
            if(bookRepository.get(book.getId()) == null){
                return saveBook(book);
            }
            throw new NotFoundException(String.format("This book id: %d, do not belong user id %d", book.getId(), book.getUserId()));
        }
        bookRepository.remove(book.getId());
        bookRepository.put(book.getId(), book);
        return book;
    }

    public void deleteBook(Long id) {
        bookRepository.remove(id);
    }
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции(не storage.get, storage.save)
    // фасады, сервисы, слои данных - имитация БД(репозиторий)
    // через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения записей
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь one to many связь
    // так же учесть, что методы хранилища принимают другой тип данных - учесть это в абстракции
    // папка entity будут лежать объекты которые используются для БД.
    // получить объект entity и использовать уровень абстракции.
}
