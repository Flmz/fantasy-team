package ru.smn.fantasyteam.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRegister {

    @NotNull
    @Size(min = 4, max = 44)
    private String nickName;

    private String firstName;

    private String secondName;

    @NotNull
    @NotEmpty
    @Email(message = "that should be email")
    private String email;

    @NotNull
    @Size(min = 8, max = 25, message = "password should be between 8 and 25 characters")
    private String password;

    public UserRegister(String nickName, String email, String password) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

}
