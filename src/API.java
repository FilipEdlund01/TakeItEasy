import java.sql.*;
//singelton pro udrzeni konzistence
public class API {
    public boolean UserFound;

    public boolean PasswordFound;

    public void setPasswordFound(boolean passwordFound) {
        PasswordFound = passwordFound;
    }

    public boolean isPasswordFound() {
        return PasswordFound;
    }

    public void setUserFound(boolean userFound) {
        UserFound = userFound;
    }

    public boolean isUserFound() {
        return UserFound;
    }

    private final static API api = new API();

    public void readFromDatabase() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                //System.out.println(resultSet.getString("uid"));
            }
            statement.close();
            connection.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void findUserInDatabase(String s){
        int index =1;
        String request = "SELECT * FROM users WHERE uid =" + "'" + s + "'" +";";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
          /*  System.out.println(resultSet.getString("uid"));*/
            while (resultSet.next()) {
                index++;
               // System.out.println(resultSet.getString("uid"));
                setUserFound(true);
            }
            if(index==1){
                System.out.println("user not found");
                setUserFound(false);
            }
            statement.close();
            connection.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }




    }
    public void CheckPassword(String username,String password){

        int index =1;


        //SELECT * FROM users WHERE uid = 'filip' AND passwd = 'k'
        String request = "SELECT * FROM users WHERE uid =" + "'" + username + "'" +" AND passwd =" + "'" + password + "'" +";";


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            /*  System.out.println(resultSet.getString("uid"));*/
            while (resultSet.next()) {
                index++;

                setPasswordFound(true);
            }
            if(index==1){

                setPasswordFound(false);
            }
            statement.close();
            connection.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }





    }

    public boolean CheckIfUSerExistInDatabase(String name){
        String request ="SELECT * FROM users where uid ="+ "'" + name + "'"+";";
        int index =1;
        boolean b =true;


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            /*  System.out.println(resultSet.getString("uid"));*/
            while (resultSet.next()) {
                System.out.println(name);
                index++;



            }
            if(index==1){
              b=false;
            }
            statement.close();
            connection.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return  b;

    }


    public void InsertNewUserIntoDataBase(String username,String password){


        //SELECT * FROM users WHERE uid = 'filip' AND passwd = 'k'
       // String request = "SELECT * FROM users WHERE uid =" + "'" + username + "'" +" AND passwd =" + "'" + password + "'" +";";

        String request = "INSERT INTO users (uid, passwd) VALUES" + "("+ "'" +username+"'"+","+"'"+password+"'"+");";
        Connection connection = null;
        PreparedStatement statement = null;




        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy", "root", "root");
            statement = connection.prepareStatement(request);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted");





        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }





    }


    public static API getInstance(){
        return api;
    }




}
