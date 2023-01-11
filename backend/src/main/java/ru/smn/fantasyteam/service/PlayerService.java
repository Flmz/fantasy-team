package ru.smn.fantasyteam.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smn.fantasyteam.dto.player.PlayerDTO;
import ru.smn.fantasyteam.exceptions.player.PlayerAlreadyExistException;
import ru.smn.fantasyteam.mapper.PlayerMapper;
import ru.smn.fantasyteam.model.Player;
import ru.smn.fantasyteam.repository.PlayerRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerDTO save(PlayerDTO playerToSave) {
        Player playerEntityToSave = playerMapper.toEntity(playerToSave);
        String playerToSaveNickname = playerEntityToSave.getNickname();
        Optional<Player> playerOptional = playerRepository.findByNickname(playerToSaveNickname);

        if (playerOptional.isEmpty()) {
            return playerMapper.toDTO(playerRepository.save(playerEntityToSave));
        }
        throw new PlayerAlreadyExistException(playerToSaveNickname);
    }

}
