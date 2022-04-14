package com.example.musify.controller;

import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/persons")
    public List<PersonDTO> getArtists(){
        return personService.getPersons();
    }
    @GetMapping(value = "/persons/{id}", produces = "application/json")
    public Optional<PersonDTO> getArtistById(@RequestParam int id){
        return personService.getPersonById(id);
    }
    @PostMapping(value = "/persons")
    public ResponseEntity<PersonDTO> addArtist(@RequestBody PersonDTO personDTO){
        return new ResponseEntity<>(personService.addPerson(personDTO), HttpStatus.OK);
    }
    @PutMapping(value = "/persons/{id}")
    public ResponseEntity<PersonDTO> updateArtist(@PathVariable int id , @RequestBody PersonDTO personDTO){
        return new ResponseEntity<>(personService.updatePerson(id, personDTO), HttpStatus.OK);
    }
    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable int id){
        personService.deletePerson(id);
        return new ResponseEntity<>("Deleted with success!", HttpStatus.OK);
    }
}
