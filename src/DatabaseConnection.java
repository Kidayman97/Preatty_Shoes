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

    public static ResultSet getAdminData() throws SQLException {
        String query = "SELECT * FROM dbo.Fiche_Technique"; // Ensure this query is correct
        Connection conn = connect();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(query);
                if (!rs.isBeforeFirst()) { // Check if the ResultSet is empty
                    System.out.println("No admin data found.");
                } else {
                    System.out.println("Admin data retrieved successfully.");
                }
                return rs;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection conn = connect();
        if (conn != null) {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        }
        return null;
    }

}
