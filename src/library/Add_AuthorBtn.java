package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/**
 * Represents a GUI screen for adding and managing authors data in a library management system.
 * Provides functionality for adding, updating, deleting, and displaying authors.
 * Extends the BaseFrame class and implements the ActionListener interface.
 */
public class Add_AuthorBtn extends BaseFrame implements ActionListener {
	
	// Declare class variables for GUI components, database connections, and model 
	JFrame frame		   =new JFrame();
	DefaultTableModel model=new DefaultTableModel();
	JTable table		   = new JTable();
	JScrollPane     jsp    = new JScrollPane();
	
	JLabel head			=new JLabel("Add Author Infrmation.");
	JLabel subhead = new JLabel("Author Information");
	JLabel authorname_L =new JLabel("Author Name");
	JLabel authoremail_L=new JLabel("Author E-mail");
	JLabel authorphone_L=new JLabel("Author Phone");
	
	JTextField authorname_T   =new JTextField();
	JTextField authoremail_T  =new JTextField();
	JTextField authorphone_T  =new JTextField();
	
	JButton add_Btn   =new JButton("Add");
	JButton delete_Btn=new JButton("Delete");
	JButton update_Btn  =new JButton("Update");
	JButton clear_Btn =new JButton("Clear");
	JButton viewall_Btn =new JButton("View All");
	
	JPanel p1 =new JPanel();
	JPanel p2 =new JPanel();
	
	Statement stmt;
	String sql;

	/**
	 * Constructs a new instance of the Add_AuthorBtn class.
	 */
	public Add_AuthorBtn() {
		// Initialize the GUI components and set up the screen.
		p1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(0, 10, 30, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridwidth = 2;
		c.gridy = 0;
		p1.add(subhead, c);
		subhead.setFont(new Font("Script", Font.BOLD, 20));
		
		c.insets = new Insets(0, 5, 0, 5);
		
		c.gridy = 1;
		p1.add(authorname_L, c);
		
		c.gridy = 2;
		p1.add(authorname_T, c);
		
		c.gridy = 3;
		p1.add(authoremail_L, c);
		
		c.gridy = 4;
		p1.add(authoremail_T, c);
		
		c.gridy = 5;
		p1.add(authorphone_L, c);
		
		c.gridy = 6;
		p1.add(authorphone_T, c);
		
		p2.add(add_Btn);
		p2.add(update_Btn);
		p2.add(delete_Btn);
		p2.add(clear_Btn);
		p2.add(viewall_Btn);

		Object []colum= {"Author Id", "Author Name","Author Email","Author Phone"};
		
		model.setColumnIdentifiers(colum);
		table.setModel(model);
		jsp.setViewportView(table);		
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(head, BorderLayout.NORTH);
		head.setFont(new Font("Script", Font.BOLD, 25));
		head.setHorizontalAlignment(SwingConstants.CENTER);
		p.add(jsp,BorderLayout.CENTER);
		p.add(p1,BorderLayout.WEST);
		p.add(p2,BorderLayout.SOUTH);
				
	    frame.add(p);		
		
		frame.setSize(900, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		// Add action listeners to buttons.
		add_Btn.addActionListener(this);
		update_Btn.addActionListener(this);
		delete_Btn.addActionListener(this);
		clear_Btn.addActionListener(this);
		viewall_Btn.addActionListener(this);
		
		// Add a MouseEvent that copy the data to the form when selecting a row in the table.
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				int i=table.getSelectedRow();
				authorname_T.setText(model.getValueAt(i, 1).toString());
				authoremail_T.setText(model.getValueAt(i, 2).toString());
				authorphone_T.setText(model.getValueAt(i, 3).toString());
			}		
		});
		
	}
	
	/**
	 * Action performed when an event is triggered by a GUI component.
	 *
	 * @param e The event that was triggered.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object oo=e.getSource();		
		
		// Implements functionality for adding a new author's data to the library database.
		if(oo==add_Btn) {
			Connection conn=DB.connect();

			String n=authorname_T.getText();
			String em=authoremail_T.getText();
			String p=authorphone_T.getText();
			
			
			try {				
				sql="INSERT INTO authors (author_name, author_email, author_phone) VALUES (?, ?, ?)";
				PreparedStatement pstmt=conn.prepareStatement(sql);				
				pstmt.setString(1, n);
				pstmt.setString(2, em);
				pstmt.setString(3, p);
				int rowsaffected = pstmt.executeUpdate();
				if(rowsaffected == 1) {
					JOptionPane.showMessageDialog(this, "Added new AUTHOR successfully!");
					pstmt.close();				
				} else {
					JOptionPane.showMessageDialog(this, "Error Adding new Author!");
				}
				DB.closeConnection(conn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		// Implements functionality for updating an author's information.
		if(oo==update_Btn) {
			Connection conn=DB.connect();
			int selectedRow=table.getSelectedRow();
			if(selectedRow==-1) {
				JOptionPane.showMessageDialog(null, "Select the row you want update");
			}
			
			else {			
				Object[] rowData=new Object[model.getColumnCount()];
				
				for(int i=0;i<model.getColumnCount();i++) {
					rowData[i]=model.getValueAt(selectedRow, i);
				}
				
				String name = authorname_T.getText();
				String email = authoremail_T.getText();
				String phone = authorphone_T.getText();
				String sql="UPDATE authors SET author_name=?, author_email=?, author_phone=? WHERE author_id=?";
				try {
					PreparedStatement pstmt= conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setString(2, email);
					pstmt.setString(3, phone);
					pstmt.setInt(4, (int) rowData[0]);
					int rowsAffected = pstmt.executeUpdate();
					if(rowsAffected == 1) {
						JOptionPane.showMessageDialog(this, "Updated author\'s data successfully!");
						pstmt.close();
						DB.closeConnection(conn);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		// Implements functionality for deleting an author's data from  the library database.
		if(oo==delete_Btn) {
			Connection conn=DB.connect();
			int selectedRow = table.getSelectedRow();

		    // Check if a row is selected
		    if (selectedRow == -1) {
		        JOptionPane.showMessageDialog(this, "Select the row you want to update");
		    } else {		    		 
			    Object[] rowData = new Object[model.getColumnCount()];
			    for (int i = 0; i < model.getColumnCount(); i++)
			    {
			    	rowData[i] = model.getValueAt(selectedRow, i);
			    }
	            String sql = "DELETE FROM authors WHERE author_id = ?";
	            PreparedStatement preparedStatement;
	            try {
	            	preparedStatement = conn.prepareStatement(sql);
	            	preparedStatement.setInt(1, (int) rowData[0]);
	            	int rowsAffected = preparedStatement.executeUpdate();
	            	if(rowsAffected == 1) {
	            		JOptionPane.showMessageDialog(this, "Author Data deleted successfully!");
	            		//Remove From data show table;
	    				if(selectedRow == 1) {
	    					model.removeRow(selectedRow);
	    					JOptionPane.showMessageDialog(null, "Deleted.");
	    				}
	    				else {
	    					JOptionPane.showMessageDialog(null, "Select row to delete.");
	    				}
	            	}
	            	
		            preparedStatement.close();
		            DB.closeConnection(conn);
	            } catch (SQLException e1) {
	            	// TODO Auto-generated catch block
	            	e1.printStackTrace();
	            	JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
	            }	        
		    }
		}
		
		// Implements functionality for clearing the form and the table.
		if(oo==clear_Btn) {
			authorname_T.setText(" ");
			authoremail_T.setText(" ");
			authorphone_T.setText(" ");
			model.setRowCount(0);
		}
		
		// Implements functionality for viewing all the authors' data in the library database.
		if(oo==viewall_Btn) {
			Connection conn=DB.connect();
			model.setRowCount(0);
			ResultSet rs;
			try {
				stmt=conn.createStatement();
				sql="SELECT * FROM authors";
				rs=stmt.executeQuery(sql);
				while(rs.next()) {
				  model.addRow(new Object []{rs.getInt("author_id"),rs.getString("author_name"),rs.getString("author_email"),rs.getString("author_phone")});
				}
				stmt.close();
				DB.closeConnection(conn);
			}catch(SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}	
}
