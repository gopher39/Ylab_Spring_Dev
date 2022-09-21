package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class StorageUsers {
    private final Storage<UserEntity> storage;

    public StorageUsers(Storage<UserEntity> storage) {
        this.storage = storage;
    }

    public Long generateId() {
        return storage.generateId();
    }

    public UserEntity save(UserEntity entity) {
        return storage.saveEntity(entity);
    }

    public UserEntity getById(Long id) {
        return storage.getById(id);
    }

    public UserEntity updateUser(UserEntity user) {
        UserEntity userUpdate = getById(user.getId());
        userUpdate.update(user);
        return userUpdate;
    }

    public void deleteUserById(Long id) {
        storage.getById(id);
    }
}
