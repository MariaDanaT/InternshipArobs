package jdbc;

import java.sql.*;

public class Queries {
    public int insertBook(String title, String type) {
        Connection connection = null;
        int row = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "password");
            if (connection != null) {
                System.out.println("Connected successfully! (insertBook)");
                String insert = "INSERT INTO Books(title, type) VALUES(?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insert);
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, type);
                row = preparedStatement.executeUpdate();
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
        return row;
    }

    public int updateMail(int id, String newMail) {
        Connection connection = null;
        int row = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "password");
            if (connection != null) {
                System.out.println("Connected successfully! (updateMail)");
                String update = "UPDATE people SET mail = ? WHERE ID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(update);
                preparedStatement.setString(1, newMail);
                preparedStatement.setInt(2, id);
                System.out.println(preparedStatement);
                row = preparedStatement.executeUpdate();
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
        return row;
    }
}
