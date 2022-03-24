package jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

//        ConnectionPool basicConnection = new BasicConnection("jdbc:mysql://localhost:3306/Library", "root", "password",3);
        ConnectionPool basicConnection = new HikariConnection("jdbc:mysql://localhost:3306/Library", "root", "password",3);

        Queries queries = new Queries();
//        System.out.println("Insert book: "+queries.insertBook("Maitreyi", "roman"));
        System.out.println("Update mail: " + queries.updateMail(1, "aana@gmail.com"));

        Connection connection = null;
        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "password");
            connection = basicConnection.getConnection();
            if (connection != null) {
                System.out.println("Connected successfully!");
                Statement statement = connection.createStatement();
                String id1 = "1' or 1=1"; //in this way, all rows from people table are printed
                String query = "SELECT * FROM people WHERE id = '" + id1;

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
//            try {
//                connection.close();
            try {
                basicConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
    }
}
