package ru.smn.fantasyteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.smn.fantasyteam.model.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByNickname(@Param("nick") String nick);

    @Query(nativeQuery = true, value = """
            SELECT * FROM PLAYER p
                WHERE lower(p.nickname) like lower(concat('%', :nick, '%'))
            """)
    Player findWithNotFullNickName(@Param("nick") String nick);
}
