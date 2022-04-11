package com.example.musify.controller;

import com.example.musify.dto.artistdto.ArtistDTO;
import com.example.musify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }
    @GetMapping("/artists")
    public List<ArtistDTO> getArtists(){
        return artistService.getArtists();
    }
    @GetMapping(value = "/artists/{id}", produces = "application/json")
    public Optional<ArtistDTO> getArtistById(@RequestParam int id){
        return artistService.getArtistById(id);
    }
    @PostMapping(value = "/artists")
    public ResponseEntity<ArtistDTO> addArtist(@RequestBody ArtistDTO artistDTO){
        return new ResponseEntity<>(artistService.addArtist(artistDTO), HttpStatus.OK);
    }
    @PutMapping(value = "/artists/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable int id ,@RequestBody ArtistDTO artistDTO){
        return new ResponseEntity<>(artistService.updateArtist(id,artistDTO), HttpStatus.OK);
    }
    @DeleteMapping(value = "/artists/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable int id){
        artistService.deleteArtist(id);
        return new ResponseEntity<>("Deleted with success!", HttpStatus.OK);
    }
}
