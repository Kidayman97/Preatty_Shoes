import entities.User;
import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://192.168.1.2:1433;databaseName=ApplicationDB;integratedSecurity=true;encrypt=false;";

    public static Connection connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User checkLogin(String username, String password) {
        System.out.println("Attempting login with username: " + username);
        String query = "SELECT login, type FROM users WHERE login = ? AND password = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Fetch user details from the ResultSet
                String fetchedUsername = rs.getString("login");
                String fetchedType = rs.getString("type");
                return new User(fetchedUsername, fetchedType);
            } else {
                System.out.println("No matching user found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if login fails
    }

}
