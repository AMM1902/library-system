package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/**
 * The Delete_BookBtn class represents a frame for deleting books from the library.
 * Provides functionality for deleting books from the library database.
 * Extends the BaseFrame class and implements the ActionListener interface.
 */
public class Delete_BookBtn extends BaseFrame implements ActionListener {
	
	JFrame Frame=new JFrame();
	DefaultTableModel model=new DefaultTableModel();
	JButton allBtn=new JButton("Show all Book");
	JButton deleteBtn=new JButton(" Delete Book");
	JButton refreshBtn=new JButton("    Refresh    ");
	JTable table=new JTable();
	JScrollPane scrollPane=new JScrollPane();
	JPanel p1=new JPanel();
	
	/**
     * Constructs a Delete_BookBtn object and initializes the frame and its components.
     */
	public Delete_BookBtn() {
		Frame.setTitle("Delete Book");
		Frame.setSize(750, 500);
		Frame.setLocationRelativeTo(null);
		Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Object [] colum= {"Book Id","Book Name","Book Genre","Release Date","Book Location","Author ID","Publisher ID","Librarian ID"};
		model.setColumnIdentifiers(colum);
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		scrollPane.setViewportView(table);
		p1.setLayout(new FlowLayout());
		p1.add(allBtn);
		p1.add(deleteBtn);
		p1.add(refreshBtn);
		Frame.setLayout(new BorderLayout());
		Frame.add(p1, BorderLayout.SOUTH);
		Frame.add(scrollPane,BorderLayout.CENTER);
		Frame.setVisible(true);
		
		allBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		String sql;
		ResultSet rs;
		Statement stmt;
		Connection conn;
		
		// Implements functionality for showing all the book data in the table.
		if(source==allBtn) {
			try {
				conn = DB.connect();
				stmt=conn.createStatement();
				sql="SELECT * FROM books";
				rs=stmt.executeQuery(sql);
				while(rs.next()) {
					model.addRow(new Object[] {rs.getInt("book_id"),rs.getString("book_name"),rs.getString("book_genre"),rs.getDate("release_date"),
												rs.getInt("book_location"),rs.getInt("author_id"),rs.getInt("publisher_id"),rs.getInt("librarian_id")});
				}
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// Implements functionality for deleting a book from the library database.
		if(source==deleteBtn) {
			conn= DB.connect();
			String deleteid=JOptionPane.showInputDialog(this,"Enter Book ID you want to Delete.");
				try {
					stmt=conn.createStatement();
					sql="DELETE FROM books WHERE book_id="+Integer.parseInt(deleteid);
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Deleted Book id :"+deleteid.toString());
					stmt.close();
					DB.closeConnection(conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		// Implements functionality for refreshing the book list.
		if(source==refreshBtn) {
			conn = DB.connect();
			model.setRowCount(0);
			try {
				 stmt=conn.createStatement();
				sql="SELECT * FROM books";
				rs=stmt.executeQuery(sql);
				while(rs.next()) {
					model.addRow(new Object[] {rs.getInt("book_id"),
											   rs.getString("book_name"),
											   rs.getString("book_genre"),
											   rs.getDate("release_date"),
											   rs.getInt("book_location"),
											   rs.getInt("author_id"),
											   rs.getInt("publisher_id"),
											   rs.getInt("librarian_id")});
				}
				stmt.close();
				DB.closeConnection(conn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}	
}
