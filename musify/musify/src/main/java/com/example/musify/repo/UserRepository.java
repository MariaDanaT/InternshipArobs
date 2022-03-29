package com.example.musify.repo;

import com.example.musify.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int addUserUsingFirstNameAndLastNameParameters(String firstName, String lastName) {
        return jdbcTemplate.update(
                "INSERT INTO Users(firstName, lastName) VALUES (?, ?)", firstName, lastName);
    }

    public List<User> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM Users", new BeanPropertyRowMapper<>(User.class));
    }
}
