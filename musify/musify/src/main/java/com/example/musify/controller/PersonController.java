package com.example.musify.controller;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.service.PersonService;
import com.example.musify.service.utilcheck.Checker;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/persons")
    public List<PersonDTO> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(value = "/persons/{id}", produces = "application/json")
    public Optional<PersonDTO> getPersonById(@RequestParam int id) {
        return personService.getPersonById(id);
    }

    @PostMapping(value = "/persons")
    public Optional<PersonDTO> addPerson(@RequestBody PersonDTO personDTO) {
        if (!Checker.isAdmin())
            throw new UnauthorizedException("Only admins can add persons (artists)!");
        return Optional.ofNullable(personService.addPerson(personDTO));
    }

    @PutMapping(value = "/persons/{id}")
    public Optional<PersonDTO> updatePerson(@PathVariable int id, @RequestBody PersonDTO personDTO) {
        return personService.updatePerson(id, personDTO);
    }

    @GetMapping("/persons/albums/{idPerson}")
    public List<AlbumDTO> loadAllAlbums(@PathVariable("idPerson") Integer idPerson) {
        return personService.loadAllAlbums(idPerson);
    }
}
