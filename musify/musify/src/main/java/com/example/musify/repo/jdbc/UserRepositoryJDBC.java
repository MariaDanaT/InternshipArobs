package com.example.musify.repo.jdbc;

import com.example.musify.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserRepositoryJDBC {
    private JdbcTemplate jdbcTemplate;

    public UserRepositoryJDBC(DataSource dataSource) {
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
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        });
    }
    public User addUser(User user) {
        String query = "INSERT INTO Users(id, first_name, last_name, email, password, country, role) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(query, user.getId() ,user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getCountry(), user.getRole());
        return jdbcTemplate.queryForObject("SELECT * FROM Users WHERE id = LAST_INSERT_ID()", new BeanPropertyRowMapper<>(User.class));

    }

    public int addUserUsingFirstNameAndLastNameParameters(String firstName, String lastName) {
        return jdbcTemplate.update(
                "INSERT INTO Users(first_name, last_name) VALUES (?, ?)", firstName, lastName);
    }

    public List<User> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM Users", new BeanPropertyRowMapper<>(User.class));
    }
}
