package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
/**
 * The IssuedBooks class represents a frame for issued books.
 * Provides the list of issued books
 * Extends the BaseFrame class and implements the ActionListener interface.
 */
public class IssuedBooks extends BaseFrame implements ActionListener {
	JLabel header;
	DefaultTableModel model;
	JTable bookTable;
	JScrollPane scrollPane;
	JPanel btnPane;
	JButton closeBtn;
	String[] columnNames = {"Book Name", "Member Name", "Issued Date", "Deadline", "Return Date", "Fine(MMK)"};
	
	/**
	 * Constructs an IssuedBook object
	 * 
	 * @param librarian The Librarian class of the library system.
	 */
	public IssuedBooks(Librarian librarian) {
		this.setTitle("Issued Books");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		header = new JLabel("Issued Books");
		header.setFont(new Font("Georgia", Font.BOLD, 30));
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		this.setModel();
		bookTable = new JTable(model);
		TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
		bookTable.setRowSorter(rowSorter);
		scrollPane = new JScrollPane(bookTable);
		
		btnPane = new JPanel();
		closeBtn = new JButton("Close");
		closeBtn.addActionListener(this);
		btnPane.add(closeBtn);
		
		this.add(header, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(btnPane, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	/**
     * Sets the model for the table by retrieving the issued books data from the database.
     */
	private void setModel() {
		Connection con = DB.connect();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT books.book_name, members.member_name, borrowed_books.issued_date, borrowed_books.std_return_date, borrowed_books.return_date, borrowed_books.fine_mmk"
					 + " FROM borrowed_books"
					 + " JOIN members ON borrowed_books.member_id = members.member_id"
					 + " JOIN books ON borrowed_books.book_id = books.book_id";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("book_name"),
						 				   rs.getString("member_name"),
						 				   rs.getString("issued_date"),
						 				   rs.getString("std_return_date"),
						 				   rs.getString("return_date"),
						 				   rs.getString("fine_mmk")
				});
			}
			rs.close();
			DB.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
		} finally {
			DB.closeConnection(con);
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
