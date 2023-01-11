package ru.smn.fantasyteam.mapper;

import org.mapstruct.Mapper;
import ru.smn.fantasyteam.dto.player.PlayerDTO;
import ru.smn.fantasyteam.model.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    Player toEntity(PlayerDTO playerDTO);

    PlayerDTO toDTO(Player player);
}
