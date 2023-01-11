package ru.smn.fantasyteam.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.smn.fantasyteam.dto.user.RoleService;
import ru.smn.fantasyteam.dto.user.UserRegister;
import ru.smn.fantasyteam.exceptions.role.RoleNotFoundException;
import ru.smn.fantasyteam.mapper.UserMapper;
import ru.smn.fantasyteam.model.Role;
import ru.smn.fantasyteam.model.User;
import ru.smn.fantasyteam.repository.UserRepository;
import ru.smn.fantasyteam.service.RegisterService;

@RequiredArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {

    private final RoleService roleService;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;


    public void register(UserRegister userDto, BindingResult bindingResult) {
        User userToSave = UserMapper.INSTANCE.toEntityFromRegister(userDto);
        enrichUser(userToSave);
        userRepo.save(userToSave);
    }

    private void enrichUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        user.setNickName(user.getNickName().toLowerCase());
        user.setActive(true);
        user.addRole(getUserRole());
    }

    private Role getUserRole() {
        return roleService.findByName("ROLE_USER")
                .orElseThrow(RoleNotFoundException::new);
    }
}
