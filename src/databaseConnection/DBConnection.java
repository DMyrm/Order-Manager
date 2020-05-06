package databaseConnection;

import java.sql.*;
import java.util.logging.*;

public class DBConnection
{
	private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName()); ;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/ordermanagement?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "";
	
	private static DBConnection singleInstance = new DBConnection();

	public DBConnection()
	{
		try
		{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	//database connection methods
	public Connection createConnection()
	{
		Connection conn = null;		
		try
		{
			conn = DriverManager.getConnection(DBURL,USER,PASS);
		} catch (SQLException e)
		{
			System.out.println("Invalid connection index");
			e.printStackTrace();
		}
		return conn;
	}
}
