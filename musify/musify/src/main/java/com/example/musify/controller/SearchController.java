package com.example.musify.controller;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/person/name")
    public List<PersonDTO> searchPersonByName(@RequestParam String name) {
        List<PersonDTO> personDTOList = searchService.searchPersonByName(name);
        return personDTOList;
    }

    @GetMapping("/person/stageName")
    public List<PersonDTO> searchPersonByStageName(@RequestParam String stageName) {
        List<PersonDTO> personDTOList = searchService.searchPersonStageName(stageName);
        return personDTOList;
    }

    @GetMapping("/album/title")
    public List<AlbumDTO> searchAlbumByTitle(@RequestParam String title) {
        List<AlbumDTO> albumDTOList = searchService.searchAlbumByTitle(title);
        return albumDTOList;
    }

    @GetMapping("/album/genre")
    public List<AlbumDTO> searchAlbumByGenre(@RequestParam String genre) {
        List<AlbumDTO> albumDTOList = searchService.searchAlbumByGenre(genre);
        return albumDTOList;
    }

    @GetMapping("/song/title")
    public ResponseEntity<List<SongDTO>> searchSongsByTitle(@RequestParam String title) {
        List<SongDTO> songDTOList = searchService.searchSongsByTitle(title);
        return new ResponseEntity<>(songDTOList, HttpStatus.OK);
    }

}
