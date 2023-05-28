package library;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import java.sql.*;

/**
 * The BorrowedBooks class represents a frame that displays a list of books borrowed by a member.
 * It extends the BaseFrame class and implements the ActionListener interface.
 */
public class BorrowedBooks extends BaseFrame implements ActionListener {

	JLabel header;
	DefaultTableModel model;
	JTable table;
	JButton closeBtn;
	JPanel closePane;
	JScrollPane scrollPane;
	Member member;
	String[] columnNames = {"Book Name", "Borrowed Date", "Deadline", "Return Date", "Fine(MMK)"};
	
	/**
     * Constructs a BorrowedBooks object for the specified member.
     *
     * @param member the member for whom the borrowed books are displayed
     */
	public BorrowedBooks(Member member) {
		this.member = member;
		this.setTitle("Borrowed Books");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		header = new JLabel("Borrowed Books");
		header.setFont(new Font("Georgia", Font.BOLD, 30));
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		this.setModel();
		table = new JTable(model);
		//table.setEnabled(false);
		scrollPane = new JScrollPane(table);
		
		closeBtn = new JButton("Close");
		closePane = new JPanel();
		closePane.add(closeBtn);
		closeBtn.addActionListener(this);
		
		this.add(header, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(closePane, BorderLayout.SOUTH);
		this.setVisible(true);
		if(model.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "You have no borrowed books right now!");
		}
	}

	/**
     * Sets the model for the table by retrieving the borrowed books data from the database.
     */
	private void setModel() {
		// TODO Auto-generated method stub
		Connection con = DB.connect();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT books.book_name, borrowed_books.issued_date, borrowed_books.std_return_date, borrowed_books.return_date, borrowed_books.fine_mmk "
						 + "FROM borrowed_books "
						 + "JOIN books ON borrowed_books.book_id = books.book_id "
						 + "WHERE borrowed_books.member_id = "+member.getMember_id();
						 //+ " AND borrowed_books.return_date is NULL;";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("book_name"),
						rs.getDate("issued_date"),
						rs.getDate("std_return_date"),
						((rs.getDate("return_date") != null) ? rs.getDate("return_date"): "Not Returned Yet!"),
						rs.getInt("fine_mmk")
				});
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == closeBtn) {
			this.dispose();
		}
	}
	
}
