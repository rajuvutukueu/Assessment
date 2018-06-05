package CSVProcessManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
	
	public static DatabaseConnector util =  new DatabaseConnector();
	
	private DatabaseConnector ()
	{
	}
	
	private Connection ConnectToDB()
	{
		Connection connection = null;
	try
	{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	 connection = DriverManager.getConnection(url,"system","tiger");
	if(connection.equals(null))
	{
	System.out.println("connection was failed");	
	}
	else
	{
	System.out.println("connected successfully");	
	}
	}
	catch(Exception exception)
	{
	exception.printStackTrace();	
	}
	return connection;
	}
	
	public Connection getDatabaseConnection()
	{
		return ConnectToDB();
	}
	public static DatabaseConnector gerInstance()
	{
		return util;
	}

}
