package com.example.musify.mapper;

import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.entity.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO personToPersonDTO(Person person);

    Person personFromPersonDTO(PersonDTO personDTO);
}
