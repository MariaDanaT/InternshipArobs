package jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "password");
            if (connection != null) {
                System.out.println("Connected successfully!");
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM people";
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String email = rs.getString("mail");
                    Date birthDate = rs.getDate("birthDate");
                    Timestamp latestUpdate = rs.getTimestamp("latestUpdate");

                    System.out.println(String.join(", ", String.valueOf(id), firstName, lastName, email, birthDate.toString(), latestUpdate.toString()));
                }
            } else {
                System.out.println("NOT connected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
