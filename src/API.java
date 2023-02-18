import java.sql.*;
//singelton pro udrzeni konzistence
public class API {
    private final static API api = new API();

    public void readFromDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy","root","root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()){
            System.out.println(resultSet.getString("uid"));
        }

    }

    public static API getInstance(){
        return api;
    }




}
