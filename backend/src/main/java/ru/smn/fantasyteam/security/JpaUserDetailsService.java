package ru.smn.fantasyteam.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.smn.fantasyteam.exceptions.user.UserNotFoundException;
import ru.smn.fantasyteam.model.User;
import ru.smn.fantasyteam.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login.toLowerCase())
                .orElseThrow(UserNotFoundException::new);

        return new SecurityUser(user);
    }
}
