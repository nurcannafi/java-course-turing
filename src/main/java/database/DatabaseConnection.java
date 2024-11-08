package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "4277";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("New user id: " + insertUser(connection, "tester", "12345678",
                    "testuser@gmail.com"));
            System.out.println("Updated rows count: " + updateUserStatus(connection, "tester",
                    "inactive"));
            System.out.println("Deleted rows count: " + deleteUser(connection, 4));

            String tableDefinition = "(id SERIAL PRIMARY KEY, username VARCHAR(30) UNIQUE NOT NULL," +
                    " email VARCHAR(50) UNIQUE NOT NULL)";
            System.out.println("table created or already exists: " + createTable(connection, "users",
                    tableDefinition));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int insertUser(Connection connection, String username, String password, String email) {
        String query = "INSERT INTO users (USERNAME, PASSWORD, EMAIL) VALUES(?, ?, ?) RETURNING id";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static int updateUserStatus(Connection connection, String username, String newStatus) {
        String query = "UPDATE users  SET status= ?  WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newStatus);
            statement.setString(2, username);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int deleteUser(Connection connection, int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean createTable(Connection connection, String tableName, String tableDefinition) {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + tableDefinition;

        try (Statement statement = connection.createStatement()) {
            int res = statement.executeUpdate(query);
            return res == 0 || res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
