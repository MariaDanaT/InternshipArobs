package com.example.musify.service;

import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.entity.Person;
import com.example.musify.mapper.PersonMapper;
import com.example.musify.repo.springdata.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> getPersons() {
        return personRepository.findAll().stream()
                .map(person -> personMapper.personToPersonDTO(person))
                .collect(Collectors.toList());
    }

    public Optional<PersonDTO> getPersonById(int id) {
        Optional<PersonDTO> artistDTO = Optional.empty();
        Optional<Person> artist = personRepository.findById(id);
        if (artist.isPresent()) {
            artistDTO = Optional.of(personMapper.personToPersonDTO(artist.get()));
        }
        return artistDTO;
    }

    public PersonDTO addPerson(PersonDTO personDTO) {
        Person person = personMapper.personFromPersonDTO(personDTO);
        return personMapper.personToPersonDTO(personRepository.save(person));
    }

    public PersonDTO updatePerson(Integer id, PersonDTO personDTO) {
        Optional<Person> optional = personRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("There is no artist with id=" + id);
        }
        Person person = optional.get();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setBirthday(personDTO.getBirthday());
        person.setActivityStartDate(personDTO.getActivityStartDate());
        person.setActivityEndDate(personDTO.getActivityEndDate());
        return personMapper.personToPersonDTO(personRepository.save(person));
    }

    public void deletePerson(Integer id) {
        Optional<Person> optional = personRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("There is no artist with id=" + id);
        }
        Person person = optional.get();
        personRepository.delete(person);
    }
}
