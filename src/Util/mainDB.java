package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Shakla
 */
public class mainDB {
    
    //Database connection info
    private static final String dbName = "U04Rc2";
    private static final String dbURL = "jdbc:mysql://52.206.157.109:3306/" + dbName;
    private static final String dbUser = "U04Rc2";
    private static final String dbPass = "53688320962";
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    private static Connection dbConn;
    
    //Connects to the database
    public static void dbConnect(){
        try{
            Class.forName(dbDriver);
            dbConn = DriverManager.getConnection(dbURL, dbUser, dbPass);
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Class exception");
        } catch (SQLException se) {
            System.out.println("SQL Exception");
        }
    }
    
    //Disconnect from the batabase
    public static void dbDisconnect(){
        try{
            dbConn.close();
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
    }
    
    //Connection return
    public static Connection getConn(){
        return dbConn;
    }
    
    
}
