package com.example.musify.mapper;

import com.example.musify.dto.persondto.PersonDTO;
import com.example.musify.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO personToPersonDTO(Person person);

    Person personFromPersonDTO(PersonDTO personDTO);

    @Mapping(target = "id", ignore = true)
    void mergePersonAndPersonDTO(@MappingTarget Person person, PersonDTO personDTO);
}
