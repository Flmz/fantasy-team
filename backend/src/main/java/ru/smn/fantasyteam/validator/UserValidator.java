package ru.smn.fantasyteam.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.smn.fantasyteam.dto.user.UserRegister;
import ru.smn.fantasyteam.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegister.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegister userToValidate = (UserRegister) target;
        String userNickName = userToValidate.getNickName();
        String userEmail = userToValidate.getEmail();

        if (userRepository.existsByEmail(userEmail)) {
            errors.rejectValue("email", "", "email should be unique");
        }
        if (userRepository.existsByNickName(userNickName)) {
            errors.rejectValue("nickName", "", "nick name should be unique");
        }
    }
}
