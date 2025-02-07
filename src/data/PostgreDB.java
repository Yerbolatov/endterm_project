package data;

import data.interfaces.IDB;

import java.sql.*;

public class PostgreDB implements IDB {
    private static PostgreDB instance;
    private String host;
    private String user;
    private String password;
    private String database;

    private Connection connection;

    public PostgreDB(String host, String user, String password, String database) {
        setHost(host);
        setUser(user);
        setPassword(password);
        setDatabase(database);
    }

    public static synchronized PostgreDB getInstance(String host, String user, String password, String database) {
        if (instance == null) {
            instance = new PostgreDB(host, user, password, database);
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        String connectionUrl = host + "/" + database;
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(connectionUrl, user, password);

            return connection;
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ee) {
                System.out.println("Connection close error: " + ee.getMessage());
            }
        }
    }


}
