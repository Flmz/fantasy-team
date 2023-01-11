package ru.smn.fantasyteam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.smn.fantasyteam.dto.user.UserRegister;
import ru.smn.fantasyteam.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntityFromRegister(UserRegister userRegister);
}
