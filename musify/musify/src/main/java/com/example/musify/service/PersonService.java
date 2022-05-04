package com.example.musify.service;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.entity.Person;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.PersonMapper;
import com.example.musify.repo.springdata.PersonRepository;
import com.example.musify.service.utilcheck.Checker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final AlbumMapper albumMapper;

    @Transactional
    public List<PersonDTO> getPersons() {
        return personRepository.findAll().stream()
                .map(personMapper::personToPersonDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<PersonDTO> getPersonById(int id) {
        Person person = Checker.getPersonIfExists(personRepository.findById(id), id);
        return Optional.of(personMapper.personToPersonDTO(person));
    }

    @Transactional
    public PersonDTO addPerson(PersonDTO personDTO) {
        Person person = personMapper.personFromPersonDTO(personDTO);
        return personMapper.personToPersonDTO(personRepository.save(person));
    }

    @Transactional
    public Optional<PersonDTO> updatePerson(Integer id, PersonDTO personDTO) {
        Person person = Checker.getPersonIfExists(personRepository.findById(id), id);
        personMapper.mergePersonAndPersonDTO(person, personDTO);
        return Optional.of(personDTO);
    }


    @Transactional
    public List<AlbumDTO> loadAllAlbums(Integer idPerson) {
        Person person = Checker.getPersonIfExists(personRepository.findById(idPerson), idPerson);

        return person.getAlbums()
                .stream()
                .map(albumMapper::albumToAlbumDTO)
                .collect(Collectors.toList());
    }
}
