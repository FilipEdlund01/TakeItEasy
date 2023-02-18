import java.sql.*;
//singelton pro udrzeni konzistence
public class API {
    private final static API api = new API();

    public void readFromDatabase() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("uid"));
            }
            statement.close();
            connection.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void InsertNewUserToDatabase(){
        try {
            // Load the JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy","root","root");

            // Create a statement
            Statement stmt = conn.createStatement();

            // Write the SQL query
            String sql = "INSERT INTO users (uid, passwd) VALUES ('John Doe', 30)";

            // Execute the query
            int rows = stmt.executeUpdate(sql);

            System.out.println(rows + " row(s) affected");

            // Close the statement and connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static API getInstance(){
        return api;
    }




}
