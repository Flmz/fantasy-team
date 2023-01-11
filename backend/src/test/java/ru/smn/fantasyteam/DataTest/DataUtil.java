package ru.smn.fantasyteam.DataTest;

import ru.smn.fantasyteam.dto.user.LoginRequest;
import ru.smn.fantasyteam.dto.user.UserRegister;
import ru.smn.fantasyteam.model.Role;
import ru.smn.fantasyteam.model.User;

public class DataUtil {
    public static final UserRegister USER_FOR_REGISTER_TEST;

    public static final User USER_TEST_USER;

    public static final User USER_TEST_ADMIN;

    public static final Role ROLE_USER = new Role();

    public static final Role ROLE_ADMIN = new Role();

    public static final Role ROLE_MODERATOR = new Role();

    public static final LoginRequest USER_FOR_LOGIN_REQUEST;

    static {
        USER_FOR_REGISTER_TEST = new UserRegister("test_user",
                "TestName",
                "TestSecondName",
                "testemail@email.com",
                "password");

        USER_FOR_LOGIN_REQUEST = new LoginRequest(USER_FOR_REGISTER_TEST.getNickName()
                , USER_FOR_REGISTER_TEST.getPassword());
        ROLE_USER.setName("ROLE_USER");

        ROLE_ADMIN.setName("ROLE_ADMIN");

        ROLE_MODERATOR.setName("ROLE_MODERATOR");

        USER_TEST_ADMIN = User.builder()
                .firstName("TestAdmin")
                .email("adminuser@email.com")
                .nickName("adminUserNickName")
                .password("password")
                .build();

        USER_TEST_USER = User.builder()
                .firstName("TestUser")
                .email("user@email.com")
                .nickName("usernickname")
                .password("password")
                .build();
    }
}
