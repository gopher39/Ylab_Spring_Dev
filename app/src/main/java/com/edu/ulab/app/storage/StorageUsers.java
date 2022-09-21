package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.EntityUser;
import org.springframework.stereotype.Repository;

@Repository
public class StorageUsers {
    private final Storage<EntityUser> storage;

    public StorageUsers(Storage<EntityUser> storage) {
        this.storage = storage;
    }

    public Long generateId() {
        return storage.generateId();
    }

    public EntityUser save(EntityUser entity) {
        return storage.saveEntity(entity);
    }

    public EntityUser getById(Long id) {
        return storage.getById(id);
    }

    public EntityUser updateUser(EntityUser user) {
        EntityUser userUpdate = getById(user.getId());
        userUpdate.update(user);
        return userUpdate;
    }

    public void deleteUserById(Long id) {
        storage.getById(id);
    }
}
