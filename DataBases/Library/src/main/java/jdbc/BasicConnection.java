package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnection implements ConnectionPool {

    private String connectionString;
    private String user;
    private String password;
    private int maxSize;
    private List<Connection> connectionsAvailable = new ArrayList<>();
    private List<Connection> connectionsUsed = new ArrayList<>();

    public BasicConnection(String connectionString, String user, String password, int maxSize) {
        this.connectionString = connectionString;
        this.user = user;
        this.password = password;
        this.maxSize = maxSize;
        for (int i = 0; i < maxSize; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(connectionString, user, password);
                if (connection != null) {
                    connectionsAvailable.add(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        if(connectionsAvailable.size() >0)
        {
            Connection connection = connectionsAvailable.remove(connectionsAvailable.size()-1);
            connectionsUsed.add(connection);
            return connection;
        }
        return null;
    }

    @Override
    public void releaseConnection(Connection connection) {
        connectionsUsed.remove(connection);
        connectionsAvailable.add(connection);
    }
}
