package ru.smn.fantasyteam.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.smn.fantasyteam.dto.player.PlayerListResponse;
import ru.smn.fantasyteam.exceptions.player.PlayerNotFoundException;
import ru.smn.fantasyteam.mapper.PlayerMapper;
import ru.smn.fantasyteam.model.Player;
import ru.smn.fantasyteam.repository.PlayerRepository;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PlayerFinderService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerListResponse findAllPlayers(Integer page, Integer playersPerPage, String sortBy) {
        if (page == null || playersPerPage == null) {
            return new PlayerListResponse(playerRepository.findAll(Sort.by(sortBy))
                    .stream().map(playerMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        return new PlayerListResponse(playerRepository.findAll(
                        PageRequest.of(page, playersPerPage, Sort.by(sortBy)))
                .stream().map(playerMapper::toDTO)
                .collect(Collectors.toList()));
    }

    public Player findByNickName(String nickName) {
        return playerRepository.findByNickname(nickName)
                .orElseThrow(() -> new PlayerNotFoundException(nickName));
    }

}
