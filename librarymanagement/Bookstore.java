package librarymanagement;

import java.util.*;
import java.sql.*;

// Class for managing books
public class Bookstore {

    // Function to add book
    public void SetBookdetails() throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Book ID:  ");
        int book_id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Book name:  ");
        String book_name = sc.nextLine();
        System.out.print("Enter the Book Author:  ");
        String book_author = sc.nextLine();
        System.out.print("Enter the published year:  ");
        int published_year = sc.nextInt();
        sc.nextLine();
        if (published_year > currentYear) {
            System.out.println("Published year should be less than or equal to current year");
            return;
        }
        System.out.print("Enter the Genre:  ");
        String genre = sc.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO book_details(Book_Id, Book_Name, Book_Author, Published_year, Genre) VALUES(?,?,?,?,?)");
            pstmt.setInt(1, book_id);
            pstmt.setString(2, book_name);
            pstmt.setString(3, book_author);
            pstmt.setInt(4, published_year);
            pstmt.setString(5, genre);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            System.out.println("Book Inserted Successfully");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Not found: " + e.getMessage());
        }
    }

    // Function to read book details
    public void getBookdetails() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM book_details");
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\nHere is the Books List:");
            while (rs.next()) {
                System.out.println("Book ID: " + rs.getInt("Book_Id") +
                        " | Book Name: " + rs.getString("Book_Name") +
                        " | Author: " + rs.getString("Book_Author") +
                        " | Published Year: " + rs.getInt("Published_Year") +
                        " | Genre: " + rs.getString("Genre"));
            }
            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Class Error: " + e.getMessage());
        }
    }

    // Function to remove book
    public void removeBookdetails() throws Exception {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the Book ID to remove: ");
            int book_id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter the Book Name: ");
            String book_name = sc.nextLine();

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM book_details WHERE Book_Id=? AND Book_Name=?");
            pstmt.setInt(1, book_id);
            pstmt.setString(2, book_name);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            System.out.println("Book Deleted Successfully");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Error: " + e.getMessage());
        }
    }
}
