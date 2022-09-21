package com.edu.ulab.app.mapper.dto;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDto userToUserDto(UserEntity userEntity);

    UserEntity userDtoToUser(UserDto userDto);
}
