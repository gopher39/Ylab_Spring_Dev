package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.StorageUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    StorageUsers storage;
    UserMapper mapper;

    UserServiceImpl(StorageUsers storage, UserMapper userMapper) {
        this.storage = storage;
        this.mapper = userMapper;
    }

        @Override
        public UserDto createUser(UserDto userDto) {
            // сгенерировать идентификатор
            // создать пользователя
            // вернуть сохраненного пользователя со всеми необходимыми полями id
            userDto.setId(storage.generateId());
            UserEntity entity = storage.save(mapper.userDtoToUserEntity(userDto));
            return mapper.userEntityToUserDto(entity);
        }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = storage.updateUser(mapper.userDtoToUserEntity(userDto));
        return mapper.userEntityToUserDto(userEntity);
    }

    @Override
    public UserDto getUserById(Long id) {
        return mapper.userEntityToUserDto(storage.getById(id));
    }

    @Override
    public void deleteUserById(Long id) {
        storage.deleteUserById(id);
    }
}
