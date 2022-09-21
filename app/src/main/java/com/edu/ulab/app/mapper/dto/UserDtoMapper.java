package com.edu.ulab.app.mapper.dto;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.EntityUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDto userToUserDto(EntityUser entityUser);

    EntityUser userDtoToUser(UserDto userDto);
}
