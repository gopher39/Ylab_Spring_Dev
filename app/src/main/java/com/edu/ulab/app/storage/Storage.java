package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.EntityStorage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Scope("prototype")
public class Storage<T extends EntityStorage> {

    private Map<Long, T> storage = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong(0);

    public Long generateId() {
        return id.getAndIncrement();
    }

    public T saveEntity(T entity) {
        storage.putIfAbsent(entity.getId(), entity);
        return storage.get(entity.getId());
    }

    public T getById(Long id) {
        return storage.get(id);
    }

    public Collection<T> getValues() {
        return storage.values();
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }

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
