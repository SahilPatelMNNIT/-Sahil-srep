package Employee_Management_System;
import java.sql.*;

public class conn {
	public Connection con;
	public Statement s;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			conn obj=new conn();
		}
		catch(Exception e)
		{
			System.out.println("Execption thrown");
		}
	}
	public conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3308/employee_management", "root", "");
			s=con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Execption thrown");
		}
	}
}
