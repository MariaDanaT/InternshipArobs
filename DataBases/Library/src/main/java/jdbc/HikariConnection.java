package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnection implements ConnectionPool{

    HikariConfig hikariConfig = new HikariConfig();
    HikariDataSource hikariDataSource;

    public HikariConnection(String connectionString, String user, String password, int initialSize) {

        hikariConfig.setJdbcUrl(connectionString);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty ("maximumPoolSize", initialSize);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    @Override
    public Connection getConnection() throws SQLException {

        return hikariDataSource.getConnection();
    }

    @Override
    public void releaseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
