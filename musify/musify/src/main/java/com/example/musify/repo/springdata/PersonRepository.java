package com.example.musify.repo.springdata;

import com.example.musify.entity.Person;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT p FROM Person p WHERE p.firstName iLIKE %:name%")
    List<Person> findByFirstNameContainingIgnoreCase(@Param("name") String firstName);

    @Query("SELECT p FROM Person p WHERE p.firstName iLIKE %:name% OR p.lastName iLIKE %:name%")
    List<Person> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@Param("name") String name, @Param("name") String firstName);

    @Query("SELECT p FROM Person p WHERE p.stageName iLIKE %:stageName%")
    List<Person> findByStageNameContainingIgnoreCase(@Param("stageName")String stageName);
}
