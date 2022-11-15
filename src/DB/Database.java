package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "otari2007";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/mziuri";

    public static Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
