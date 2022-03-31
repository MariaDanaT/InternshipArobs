package com.example.musify.repo;

import com.example.musify.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User findUserUsingEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM Users WHERE email = ? and password = ?";
        return jdbcTemplate.execute(query, (PreparedStatementCallback<User>) ps -> {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setOriginCountry(resultSet.getString("originCountry"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        });
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
