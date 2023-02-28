import java.sql.*;
//singelton pro udrzeni konzistence
public class API {

    private String Api = "jdbc";
    private String conn = "mysql";
    private String database = "dk-301_TakeItEasy";
    private String host = "localhost";
    //private String url = Api + ":" + conn +"://"+ host +"/" +database;
    private String url = "jdbc:mysql://localhost:3306/easy";





    //private String conn = "https://db.gyarab.cz/phpMyAdmin/db_structure.php?server=1&db=dk-301_TakeItEasy";
    /*private String user="dk-301";
    private String passwd = "iqFppJNo";*/

    private String user="root";
    private String passwd = "root";





    public boolean PasswordFound;

    public void setPasswordFound(boolean passwordFound) {
        PasswordFound = passwordFound;
    }


    private final static API api = new API();

    public void readFromDatabase() throws SQLException {
        try {
           // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easy", "root", "root");
            Connection connection = DriverManager.getConnection(url, user, passwd);
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


    public boolean CheckPassword(String username,String password){
        boolean b =true;

        int index =1;


        //SELECT * FROM users WHERE uid = 'filip' AND passwd = 'k'
        String request = "SELECT * FROM users WHERE uid =" + "'" + username + "'" +" AND passwd =" + "'" + password + "'" +";";


        try {
            Connection connection = DriverManager.getConnection(url, user, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            /*  System.out.println(resultSet.getString("uid"));*/
            while (resultSet.next()) {
                index++;


            }
            if(index==1){
                b=false;

                setPasswordFound(false);
            }
            statement.close();
            connection.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return  b;





    }

    public boolean CheckIfUSerExistInDatabase(String name){
        boolean b =true;


            String request ="SELECT * FROM users where uid ="+ "'" + name + "'"+";";
            int index =1;



            try {
                Connection connection = DriverManager.getConnection(url,user, passwd);
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
        if (findIfThereIsText(password)) {
            //SELECT * FROM users WHERE uid = 'filip' AND passwd = 'k'
            // String request = "SELECT * FROM users WHERE uid =" + "'" + username + "'" +" AND passwd =" + "'" + password + "'" +";";

            String request = "INSERT INTO users (uid, passwd) VALUES" + "("+ "'" +username+"'"+","+"'"+password+"'"+");";
            Connection connection = null;
            PreparedStatement statement = null;




            try {
                 connection = DriverManager.getConnection(url, user, passwd);
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




        }else{
            StartStage.getInstance().setMessage("Empty field is not valid password ");
        }





    }


    public static API getInstance(){
        return api;
    }
 private boolean findIfThereIsText(String s){
        boolean b =true;
        if((s == "") || (s == " ")){
            b =false;
        }
        return b;
 }



}
