package ru.smn.fantasyteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.smn.fantasyteam.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = """
            SELECT u FROM User u left join fetch u.roles where u.email = :login OR u.nickName = :login
            """)
    Optional<User> findByLogin(@Param("login") String login);

    boolean existsByEmail(String email);

    boolean existsByNickName(String nickname);
}
