package com.example.musify.controller;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.dto.songdto.SongDTO;
import com.example.musify.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;


    @GetMapping("/person/name")
    public List<PersonDTO> searchPersonByName(@RequestParam String name) {
        return searchService.searchPersonByName(name);
    }

    @GetMapping("/person/stageName")
    public List<PersonDTO> searchPersonByStageName(@RequestParam String stageName) {
        return searchService.searchPersonStageName(stageName);
    }

    @GetMapping("/album/title")
    public List<AlbumDTO> searchAlbumByTitle(@RequestParam String title) {
        return searchService.searchAlbumByTitle(title);
    }

    @GetMapping("/album/genre")
    public List<AlbumDTO> searchAlbumByGenre(@RequestParam String genre) {
        return searchService.searchAlbumByGenre(genre);
    }

    @GetMapping("/song/title")
    public List<SongDTO> searchSongsByTitle(@RequestParam String title) {
        return searchService.searchSongsByTitle(title);
    }

}
