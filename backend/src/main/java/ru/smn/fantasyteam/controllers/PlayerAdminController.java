package ru.smn.fantasyteam.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.smn.fantasyteam.dto.ErrorResponse;
import ru.smn.fantasyteam.dto.player.PlayerDTO;
import ru.smn.fantasyteam.dto.player.PlayerListResponse;
import ru.smn.fantasyteam.exceptions.player.PlayerAlreadyExistException;
import ru.smn.fantasyteam.exceptions.player.PlayerNotFoundException;
import ru.smn.fantasyteam.mapper.PlayerMapper;
import ru.smn.fantasyteam.service.PlayerFinderService;
import ru.smn.fantasyteam.service.PlayerService;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/player")
public class PlayerAdminController {

    private final PlayerFinderService playerFinderService;
    private final PlayerService playerService;
    private final PlayerMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PlayerListResponse getAllPlayers(@RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "players_per_page", required = false)
                                            Integer playersPerPage,
                                            @RequestParam(value = "sort", defaultValue = "team") String sortBy) {
        return playerFinderService.findAllPlayers(page, playersPerPage, sortBy);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO create(@RequestBody @Valid PlayerDTO player) {
        return playerService.save(player);
    }

    @GetMapping("/{nickname}")
    public PlayerDTO get(@PathVariable("nickname") String name) {
        return mapper.toDTO(playerFinderService.findByNickName(name));
    }

    @ExceptionHandler(PlayerAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handlePlayerAlreadyExistException(PlayerAlreadyExistException exception) {
        return ErrorResponse(exception.getMessage(), 409);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerAlreadyExistException(PlayerNotFoundException exception) {
        return ErrorResponse(exception.getMessage(), 404);
    }

    private ResponseEntity<ErrorResponse> ErrorResponse(String message, int status) {
        ErrorResponse errorResponse = new ErrorResponse(message, status);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
    }
}
