package dao;

import domain.User;
import jdbc.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserRepository implements Dao<User> {
    private ConnectionPool connection;

    public UserRepository(ConnectionPool connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> get(int id) throws SQLException {
        if (connection != null) {
            User user = new User();
            String query = "SELECT * FROM people WHERE ID=?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"),
                        resultSet.getDate("birthDate"), resultSet.getString("mail"), resultSet.getTimestamp("latestUpdate"));
            }
            Optional<User> userOptional = Optional.of(user);
            return userOptional;
        }

        return Optional.empty();
    }

    @Override
    public Collection<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        if (connection != null) {
            Statement statement = connection.getConnection().createStatement();
            String query = "SELECT * FROM people";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getDate("birthDate"), rs.getString("mail"), rs.getTimestamp("latestUpdate")));
            }
        }
        return users;
    }

    @Override
    public int save(User user) {
        return 0;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
