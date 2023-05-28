package library;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * The Member class represents a member in the library system. 
 * It extends the Account class and provides additional functionality specific to members.
 */
public class Member extends Account {

	private int member_id;
	
	/**
	 * Retrieves the ID of the member.
	 * 
	 * @return member_id The ID of the member.
	 */
	public int getMember_id() {
		return member_id;
	}
	
	/**
	 * Creates an instance of a Member class with the specified member ID.
	 * 
	 * @param member_id The ID of the member.
	 */
	public Member(int member_id) {
		this.member_id = member_id;
	}
	
	
	/**
	 * Creates an instance of a Member class with the specified username and password.
	 *  
	 * @param username The username of the member.
	 * @param password The password of the member.
	 */
	public Member(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
     * Logs in the member by verifying the username and password.
     * If the login is successful, sets the librarian's attributes.
     *
     * @return true if the login is successful, false otherwise.
     */
	public boolean login() {
		try {
			Connection con = DB.connect();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM members WHERE member_name = \'"+this.username+"\';";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				if(this.password.equals(rs.getString("member_password"))) {
					this.member_id = rs.getInt("member_id");
					this.email = rs.getString("member_email");
					this.phone = rs.getString("member_phone");
					this.address = rs.getString("member_address");
					DB.closeConnection(con);
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Password!", "Incorrect Password", JOptionPane.PLAIN_MESSAGE);
					return false;
				}
			} 
			JOptionPane.showMessageDialog(null, "No member with the name '"+this.username+"'!");
			DB.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Connecting to Database", JOptionPane.ERROR_MESSAGE);
		}
		return false;
		
	}
	
	/**
	 * Syncs the member's data from the database.
	 */
	public void syncData() {
		Connection con = DB.connect();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT member_name, member_password, member_email, member_phone, member_address FROM members WHERE member_id = "+this.getMember_id();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				this.username = rs.getString("member_name");
				this.password = rs.getString("member_password");
				this.email = rs.getString("member_email");
				this.phone = rs.getString("member_phone");
				this.address = rs.getString("member_address");
			}
			stmt.close();
			DB.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Logs out the member by resetting the member's ID and invokes the superclass's logout method.
	 */
	public void logout() {
		this.member_id = 0;
		super.logout();
	}
	
	/**
	 * Opens a new screen to view all books.
	 */
	public void viewAllBooks() {
		new ViewAllBooks();
	}
	
	/**
	 * Opens a new screen to view borrowed books.
	 */
	public void borrowedBooks() {
		new BorrowedBooks(this);
	}
	
	/**
	 * Opens a new screen to view the profile of the member.
	 */
	public void viewProfile() {
		new ViewProfile(this);
	}

	/**
	 * Opens a new screen to update the member's profile.
	 */
	public void updateProfile() {
		new UpdateProfile(this);
	}
	
}
