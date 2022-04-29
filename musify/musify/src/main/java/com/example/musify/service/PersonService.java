package com.example.musify.service;

import com.example.musify.dto.albumdto.AlbumDTO;
import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.entity.Person;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.PersonMapper;
import com.example.musify.repo.springdata.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
                .map(person -> personMapper.personToPersonDTO(person))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<PersonDTO> getPersonById(int id) {
        Optional<PersonDTO> artistDTO = Optional.empty();
        Optional<Person> artist = personRepository.findById(id);
        if (artist.isPresent()) {
            artistDTO = Optional.of(personMapper.personToPersonDTO(artist.get()));
        }
        return artistDTO;
    }

    @Transactional
    public PersonDTO addPerson(PersonDTO personDTO) {
        Person person = personMapper.personFromPersonDTO(personDTO);
        return personMapper.personToPersonDTO(personRepository.save(person));
    }

    @Transactional
    public Optional<PersonDTO> updatePerson(Integer id, PersonDTO personDTO) {
        Optional<Person> optional = personRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no person with id=" + id);
        }
        Person person = optional.get();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setBirthday(personDTO.getBirthday());
        person.setActivityStartDate(personDTO.getActivityStartDate());
        person.setActivityEndDate(personDTO.getActivityEndDate());
        return Optional.of(personDTO);
    }

    @Transactional
    public void deletePerson(Integer id) {
        Optional<Person> optional = personRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("There is no person with id=" + id);
        }
        Person person = optional.get();
        personRepository.delete(person);
    }

    @Transactional
    public List<AlbumDTO> loadAllAlbums(Integer idPerson) {
        Optional<Person> optional = personRepository.findById(idPerson);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("There is no person with id=" + idPerson);
        }
        Person person = optional.get();

        return person.getAlbums()
                .stream()
                .map(albumMapper::albumToAlbumDTO)
                .collect(Collectors.toList());
    }
}
