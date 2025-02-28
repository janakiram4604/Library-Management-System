package librarymanagement;

import java.sql.*;

// Class for handling user credentials
public class Credentials {

    // Function to create an account
    public void setcredentials(int uid, String uname, String upwd, String user_role) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO credentials (User_Id, User_Name, Password, User_Role) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, uid);
            pstmt.setString(2, uname);
            pstmt.setString(3, upwd);
            pstmt.setString(4, user_role);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            System.out.println("Account Created Successfully✅");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Unable to register user. User ID or password already exists.");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Not Found");
        }
    }

    // Function to verify credentials
    public boolean getcredentials(int uid, String uname, String upwd) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM credentials");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("User_Id") == uid && rs.getString("User_Name").equals(uname) &&
                        rs.getString("Password").equals(upwd)) {
                    return true;
                }
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Not Found: " + e.getMessage());
        }

        return false;
    }
}
