package ru.smn.fantasyteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.smn.fantasyteam.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
