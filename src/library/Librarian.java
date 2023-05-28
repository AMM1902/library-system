package library;

import java.io.File;
import javax.swing.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.sql.*;

/**
 * 
 * The Librarian class represents a librarian in the library system.
 * It extends the Account class and provides additional functionality specific to librarians.
 *
 */
public class Librarian extends Account {

	private int librarian_id;
	
	
	/**
	 * Retrieves the ID of the librarian
	 * 
	 * @return The librarian ID.
	 */
	public int getLibrarian_id() {
		return librarian_id;
	}
	
	/**
	 * Constructs a Librarian object with the given librarian ID.
	 * 
	 * @param librarian_id The ID of the librarian.
	 */
	public Librarian(int librarian_id) {
		this.librarian_id = librarian_id;
	}
	
	
	/**
	 * Constructs a Librarian object with the given username and password of the librarian.
	 * 
	 * @param username The username of the librarian.
	 * @param password The password of the librarian.
	 */
	public Librarian(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
     * Logs in the librarian by verifying the username and password.
     * If the login is successful, sets the librarian's attributes.
     *
     * @return true if the login is successful, false otherwise.
     */
	public boolean login() {
		try {
			Connection con = DB.connect();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM librarians WHERE librarian_name = \'"+this.username+"\';";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				if(this.password.equals(rs.getString("librarian_password"))) {
					this.librarian_id = rs.getInt("librarian_id");
					this.email = rs.getString("librarian_email");
					this.phone = rs.getString("librarian_phone");
					this.address = rs.getString("librarian_address");
					DB.closeConnection(con);
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Password!", "Incorrect Password", JOptionPane.PLAIN_MESSAGE);
					return false;
				}
			} 
			JOptionPane.showMessageDialog(null, "No librarian with the name '"+this.username+"'!");
			DB.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Connecting to Database", JOptionPane.ERROR_MESSAGE);
		}
		return false;
		
	}
	
	/**
     * Logs out the librarian by resetting the librarian's ID and invoking the superclass's logout method.
     */
	public void logout() {
		this.librarian_id = 0;
		super.logout();
	}
	
	/**
     * Handles the return of a book by a member.
     * Updates the return date and calculates any applicable fines.
     *
     * @param book_id The ID of the book being returned.
     * @param member  The member who is returning the book.
     */
	public void returnBook(int book_id, Member member) {
		LocalDate today = LocalDate.now();
		Connection con = DB.connect();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT std_return_date FROM borrowed_books"
					+ " WHERE book_id = "+book_id
					+ " AND member_id = "+member.getMember_id()
					+ " AND return_date IS NULL;";
			ResultSet rs = stmt.executeQuery(query);
			LocalDate returnDate = null;
			if(rs.next()) {
				returnDate = rs.getDate("std_return_date").toLocalDate();
			}
			int fine = 0;
			if(today.isAfter(returnDate)) {
				fine = calculateFine(today, returnDate);
				query = "UPDATE borrowed_books "
						+ "SET collector_id = "+this.getLibrarian_id()
						+ ", return_date = \'"+today.toString()
						+ "\', fine_mmk = "+fine
						+" WHERE book_id = "+book_id
						+" AND member_id = "+member.getMember_id()
						+ " AND return_date IS NULL";
			} else {
				query = "UPDATE borrowed_books "
						+ "SET collector_id = "+this.getLibrarian_id()
						+ ", return_date = \'"+today.toString()
						+"\' WHERE book_id = "+book_id
						+" AND member_id = "+member.getMember_id()
						+ " AND return_date IS NULL";
			}
			int rowsaffected = stmt.executeUpdate(query);
			if(rowsaffected == 1) {
				JOptionPane.showMessageDialog(null, ("Returned the book successfully!\n"
												  + "Return Date: "+returnDate.toString()
												  + "\nFine: "+fine));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
     * Calculates the fine amount based on the number of days between the return date and today's date.
     *
     * @param today      The current date.
     * @param returnDate The return date of the book.
     * @return The fine amount.
     */
	public int calculateFine(LocalDate today, LocalDate returnDate) {
		long diff = ChronoUnit.DAYS.between(returnDate, today);
		int fine;
		if(diff>14) {
			fine = (int) (diff * 1500);
		} else if(diff>7) {
			fine = (int) (diff * 1000);
		} else {
			fine = (int) (diff * 500);
		}
		return fine;
	}
	
	/**
     * Performs a backup of the database by allowing the librarian to choose a location to save the backup file.
     */
	public void backup() {
		JFileChooser filechooser = new JFileChooser("C:\\Users\\hp\\eclipse-workspace\\LibraryManagementSystem\\resources\\database\\backup");
		filechooser.setDialogTitle("Select Location to Backup File.");  //Title
		
		int userselection=filechooser.showSaveDialog(null);		  //Show Frame;
	    if(userselection==JFileChooser.APPROVE_OPTION) {
	    	File file = filechooser.getSelectedFile();
	    	String filepath = file.getPath();
	    	new Backup(filepath);
	    }	    
	}
	
	/**
     * Performs a database restore by allowing the librarian to choose the backup file to restore.
     */
	public void restore() {
		JFileChooser filechooser = new JFileChooser("C:\\Users\\hp\\eclipse-workspace\\LibraryManagementSystem\\resources\\database\\backup");
		filechooser.setDialogTitle("Choose File to Restore");  //Title
		
		int userselection=filechooser.showSaveDialog(null);		  //Show Frame;
	    if(userselection==JFileChooser.APPROVE_OPTION) {
	    	File file = filechooser.getSelectedFile();
	    	String filepath = file.getPath();
	    	new Restore(filepath);
	    }
	}
}
