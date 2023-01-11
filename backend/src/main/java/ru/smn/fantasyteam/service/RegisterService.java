package ru.smn.fantasyteam.service;

import org.springframework.validation.BindingResult;
import ru.smn.fantasyteam.dto.user.UserRegister;

public interface RegisterService {
    void register(UserRegister userDto, BindingResult bindingResult);
}
