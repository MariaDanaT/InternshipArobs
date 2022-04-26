package com.example.musify.controller;

import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.SongMapper;
import com.example.musify.security.JwtUtils;
import com.example.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final SongMapper songMapper;

    @Autowired
    public SongController(SongService songService, SongMapper songMapper) {
        this.songService = songService;
        this.songMapper = songMapper;
    }

    @GetMapping("/{id}")
    public Optional<SongDTO> getById(@RequestParam int id){
        Optional<SongDTO> songDTOOptional = songService.getById(id);
        return songDTOOptional;
    }

    @PostMapping
    public ResponseEntity<SongDTO> create(@RequestBody @Valid SongDTO songDTO){
        if(!JwtUtils.getUserRoleFromSession().equals("admin"))
            throw new UnauthorizedException("Only admins can add songs!");
        SongDTO songToCreate = songService.create(songDTO);
        return new ResponseEntity<>(songToCreate, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<SongDTO> update(@RequestBody @Valid SongDTO songDTO){
        SongDTO updatedSong = songService.update(songDTO);
        return new ResponseEntity<>(updatedSong, HttpStatus.OK);
    }


}
