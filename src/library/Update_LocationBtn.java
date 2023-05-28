package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/**
 * The Update_LocationBtn class represents a graphical user interface for updating the location of books in a library database.
 * It extends the BaseFrame class and implements the ActionListener interface to handle user interactions.
 */
public class Update_LocationBtn extends BaseFrame implements ActionListener  {
	
	JFrame frame		   =new JFrame();
	DefaultTableModel model=new DefaultTableModel();
	JButton allBtn		   =new JButton("Show all Book");
	JButton updateBtn  	   =new JButton(" Update Book");
	JButton refreshBtn	   =new JButton("    Refresh   ");
	JButton exit           =new JButton("        Exit       ");
	JTable table		   =new JTable();
	JScrollPane scrollPane =new JScrollPane();
	JPanel p1			   =new JPanel();
	
	/**
     * Constructs an Update_LocationBtn object.
     * Initializes the GUI components and sets up the frame.
     */
	public Update_LocationBtn() {
		frame.setTitle("Update Book");
		frame.setSize(750, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Object [] colum= {"Book Id","Book Name","Book Location"};
		model.setColumnIdentifiers(colum);
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		scrollPane.setViewportView(table);
		p1.setLayout(new FlowLayout());
		p1.add(allBtn);
		p1.add(updateBtn);
		p1.add(refreshBtn);
		p1.add(exit);
		frame.setLayout(new BorderLayout());
		frame.add(p1, BorderLayout.SOUTH);
		frame.add(scrollPane,BorderLayout.CENTER);
		frame.setVisible(true);
		
		allBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
		exit.addActionListener(this);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();		
		String sql;
		ResultSet rs;
		Statement stmt;
		Connection conn;
		
		if(source==allBtn) {
			// Implements functionality for showing all books and its location in the library database.
			try {
				conn = DB.connect();
				stmt=conn.createStatement();
				sql="SELECT * FROM books";
				rs=stmt.executeQuery(sql);
				while(rs.next()) {
					model.addRow(new Object[] {rs.getInt("book_id"),rs.getString("book_name"),rs.getInt("book_location")});
				}
				stmt.close();
				DB.closeConnection(conn);
			} catch (SQLException e1) {				
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to the Database!", JOptionPane.ERROR_MESSAGE);
			}
		}

		if(source==updateBtn) {
			// Implements functionality for updating the location of the book that is provided by the librarian
			conn = DB.connect();
			int updateid=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Book ID "));
			int location=Integer.parseInt(JOptionPane.showInputDialog(this,"Enter Location For \nBook ID : "+updateid));
			  sql="UPDATE books SET book_location=? WHERE book_id=? ";
				try {
					PreparedStatement pstmt=conn.prepareStatement(sql);
				   
					pstmt.setInt(2, updateid);
					pstmt.setInt(1,location);  
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successfully");
					pstmt.close();
					DB.closeConnection(conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to the Database!", JOptionPane.ERROR_MESSAGE);
				}
			}			
		
		if(source==refreshBtn) {
			// Implements functionality for refreshing the book list.
			 conn = DB.connect();
			 model.setRowCount(0);
			 try {
				 stmt=conn.createStatement();
				 sql="SELECT * FROM books";
				 rs=stmt.executeQuery(sql);
				 while(rs.next()) {
					model.addRow(new Object[] {rs.getInt("book_id"),rs.getString("book_name"),/*rs.getString("book_genre"),/*rs.getDate("release_date"),*/
												rs.getInt("book_location"),rs.getInt("author_id"),rs.getInt("publisher_id"),rs.getInt("librarian_id")});
				 }
				 stmt.close();
				 DB.closeConnection(conn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to the Database!", JOptionPane.ERROR_MESSAGE);
			}
		}
	 
		if(source==exit) {
			frame.dispose();
		}
	}

}
