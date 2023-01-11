package ru.smn.fantasyteam.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.smn.fantasyteam.container.PostgresContainerTest;
import ru.smn.fantasyteam.dto.user.LoginRequest;
import ru.smn.fantasyteam.dto.user.UserRegister;
import ru.smn.fantasyteam.model.Role;
import ru.smn.fantasyteam.model.User;
import ru.smn.fantasyteam.repository.RoleRepository;
import ru.smn.fantasyteam.repository.UserRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.smn.fantasyteam.DataTest.DataUtil.*;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class AuthControllerTestIT extends PostgresContainerTest {

    private final MockMvc mvc;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public AuthControllerTestIT(MockMvc mvc, UserRepository userRepository, RoleRepository roleRepository) {
        this.mvc = mvc;
        this.userRepository = userRepository;
        roleRepository.saveAll(List.of(ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR));
    }


    @Test
    public void registerNewUser_ShouldReturnStatusCreated_AndUserShouldHaveRoleUser() throws Exception {
        String userJson = objectMapper.writeValueAsString(USER_FOR_REGISTER_TEST);

        mvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpectAll(status().isCreated());

        User user = userRepository.findByLogin(USER_FOR_REGISTER_TEST.getNickName().toLowerCase()).orElseThrow();

        Set<Role> userRoles = user.getRoles();

        assertEquals(1, userRoles.size());

        for (Role role : userRoles) {
            assertEquals("ROLE_USER", role.getName());
        }
    }


    @Test
    public void registerNewUser_LoginThisUser_ShouldReturnStatus200AndJwtTokens() throws Exception {

        UserRegister userRegister = new UserRegister("testLogin", "email@mail.com", "password");

        mvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegister)));

        String userJson = objectMapper.writeValueAsString(
                new LoginRequest(userRegister.getNickName(), userRegister.getPassword()));

        MvcResult result = mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andReturn();
        String bodyResponse = result.getResponse().getContentAsString();
        Assertions.assertTrue(bodyResponse.contains("access_jwt_token") && bodyResponse.contains("refresh_jwt_token"));
    }

    @Test
    public void loginUnknownUser_shouldReturnException() throws Exception {
        mvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginRequest("badlogin", "badPassword"))))
                .andExpectAll(
                        status().isUnauthorized()
                        , jsonPath("$.message").value("Login or password incorrect")
                );
    }

    @Test
    public void registerNewUserWithExistsLogin_shouldReturnException() throws Exception {
        userRepository.save(USER_TEST_USER);
        mvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_TEST_USER)))
                .andExpect(
                        status().isBadRequest());
    }

    @Test
    public void registerUserWithoutNickName_shouldReturnErrorMessage_StatusBadRequest() throws Exception {
        UserRegister userRegister = new UserRegister("", "emailfailed@mail.com", "password");

        MvcResult result = mvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegister))).andDo(print())
                .andExpect(
                        status().isBadRequest())
                .andReturn();

        String bodyResult = result.getResponse().getContentAsString();

        assertTrue(bodyResult.contains("nickName  - size must be between"));
    }

    @Test
    public void registerUserWithoutEmail_shouldReturnErrorMessage_StatusBadRequest() throws Exception {
        UserRegister userRegister = new UserRegister("nickNameFail", "", "password");

        MvcResult result = mvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegister))).andDo(print())
                .andExpect(
                        status().isBadRequest())
                .andReturn();

        String bodyResult = result.getResponse().getContentAsString();

        assertTrue(bodyResult.contains("email  - must not be empty"));
    }
}
