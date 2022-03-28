package jdbc;

import dao.UserRepository;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

//        ConnectionPool basicConnection = new BasicConnection("jdbc:mysql://localhost:3306/Library", "root", "password",3);
        ConnectionPool basicConnection = new HikariConnection("jdbc:mysql://localhost:3306/Library", "root", "password", 3);

        Queries queries = new Queries();
//        System.out.println("Insert book: "+queries.insertBook("Maitreyi", "roman"));
        System.out.println("Update mail: " + queries.updateMail(1, "aana@gmail.com"));

        UserRepository userRepository = new UserRepository(basicConnection);
        try {
            userRepository.getAll().forEach(user -> System.out.println(user.toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Person with the id = 2: \n" + userRepository.get(2).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
