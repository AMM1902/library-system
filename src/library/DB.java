package library;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * The DB class provides methods to establish and close a connection to the database.
 */
public class DB {
	
	static Connection con = null;
	
	/**
     * Establishes a connection to the database.
     *
     * @return the Connection object representing the database connection
     */
	public static Connection connect() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String db = "jdbc:mysql://localhost:3306/library";
			String username = "root";
			String password = "eclipse@polis";
			con = DriverManager.getConnection(db, username, password);
			System.out.println("Connected to the Database!");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Loading Connection to Database!", JOptionPane.ERROR_MESSAGE);
		}
		return con;
	}
	
	/**
     * Closes the database connection.
     *
     * @param con the Connection object representing the database connection to be closed
     */
	public static void closeConnection(Connection con) {
		try {
			con.close();
			System.out.println("Connection to the Database Terminated!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Terminating Connection to Database!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
