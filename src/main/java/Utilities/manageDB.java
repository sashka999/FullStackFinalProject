package Utilities;

import java.sql.DriverManager;


public class manageDB extends commonOps{
// ---- This method initiates a connection with DB. Receives connection details from commonOps class(@BeforeClass). Also it defines SQL statement parameter --------------------
    public static void initConnection(String dbURL, String user, String pass){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, user, pass);
            stmt = con.createStatement();
        }
        catch (Exception e){
            System.out.println("Error Occurred while connecting to DB, see details: " + e);
        }
    }
// ---- This method closes a connection with DB ------------------------------------------------------------------------------------------------------------------
    public static void closeConnection(){

        try {
            con.close();
        }
        catch (Exception e){
            System.out.println("Error Occurred while closing connection to DB, see details: " + e);
        }
    }
}
