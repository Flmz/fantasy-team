package ru.smn.fantasyteam.dto.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smn.fantasyteam.model.Role;
import ru.smn.fantasyteam.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
