package com.example.musify.repo.springdata;

import com.example.musify.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String name, String firstName);

    List<Person> findByStageNameContainingIgnoreCase(String stageName);
}
