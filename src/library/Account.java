package library;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * The Account class represents a user account in the library system.
 * It serves as the parent object for both Member and Librarian classes,
 * providing common attributes and functions for non-logged in members.
 */
public class Account {
	
	/**
	 * The username of the account.
	 */
	protected String username;
	
	/**
	 * The password of the account.
	 */
	protected String password;
	
	/**
	 * The email of the account.
	 */
	protected String email;
	
	/**
	 * The phone number of the account.
	 */
	protected String phone;
	
	/**
	 * The address of the account.
	 */
	protected String address;
	
	 /**
     * Performs a search in the database based on the given criteria.
     * Returns a ResultSet containing the search results.
     *
     * @param type       The type of search criteria (1 for author, 2 for book name, 3 for publisher).
     * @param searchName The value to search for.
     * @param con        The database connection object.
     * @return A ResultSet containing the search results.
     */
	public static ResultSet search(int type, String searchName, Connection con) {
		
		try {
			Statement stmt = con.createStatement();
			String query = null;
			if(type == 1) {
				query = "SELECT books.book_name, authors.author_name, publishers.publisher_name, books.book_genre, books.book_location, books.release_date "
						+ "FROM books "
						+ "JOIN  authors ON books.author_id = authors.author_id "
						+ "JOIN publishers ON books.publisher_id = publishers.publisher_id "
						+ "WHERE LOWER(authors.author_name) LIKE \'%"+searchName.toLowerCase()+"%\';";
			}
			else if(type == 2) {
				query = "SELECT books.book_name, authors.author_name, publishers.publisher_name, books.book_genre, books.book_location, books.release_date "
						+ "FROM books "
						+ "JOIN  authors ON books.author_id = authors.author_id "
						+ "JOIN publishers ON books.publisher_id = publishers.publisher_id "
						+ "WHERE LOWER(books.book_name) LIKE \'%"+searchName.toLowerCase()+"%\';";
			}
			else if(type == 3) {
				query = "SELECT books.book_name, authors.author_name, publishers.publisher_name, books.book_genre, books.book_location, books.release_date "
						+ "FROM books "
						+ "JOIN  authors ON books.author_id = authors.author_id "
						+ "JOIN publishers ON books.publisher_id = publishers.publisher_id "
						+ "WHERE LOWER(publishers.publisher_name) LIKE \'%"+searchName.toLowerCase()+"%\';";
			}
			ResultSet rset = stmt.executeQuery(query);
			return rset;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	/**
	 * Logs out the account by setting all the instance variables null.
	 */
	public void logout() {
		this.username = null;
		this.password = null;
		this.email = null;
		this.phone = null;
		this.address = null;
	}
}
