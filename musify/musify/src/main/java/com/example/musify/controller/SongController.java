package com.example.musify.controller;

import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.service.SongService;
import com.example.musify.service.utilcheck.Checker;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping("/{id}")
    public Optional<SongDTO> getById(@RequestParam int id) {
        return songService.getById(id);
    }

    @PostMapping
    public Optional<SongDTO> create(@RequestBody @Valid SongDTO songDTO) {
        if (!Checker.isAdmin())
            throw new UnauthorizedException("Only admins can add songs!");
        SongDTO songToCreate = songService.create(songDTO);
        return Optional.of(songToCreate);
    }

    @PutMapping
    public Optional<SongDTO> update(@RequestBody @Valid SongDTO songDTO) {
        SongDTO updatedSong = songService.update(songDTO);
        return Optional.of(updatedSong);
    }


}
