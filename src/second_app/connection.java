/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package second_app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NEXT
 */
public class connection {
    	  public static Connection getConnect() throws ClassNotFoundException, SQLException {
 //   public static void main(String a[])throws ClassNotFoundException, SQLException {

	String url1="jdbc:mysql://localhost:3306/mydb";
    String user1="root";
    String pwd1="root";      
	Class.forName("com.mysql.jdbc.Driver");
	//System.out.println("driver loaded");
	Connection con=DriverManager.getConnection(url1, user1, pwd1);
	//System.out.println("connectin fired");
return con;
	
	
}

    
}
