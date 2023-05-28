package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/**
 * Represents a GUI screen for adding and managing publishers data in a library management system.
 * Provides functionality for adding, updating, deleting, and displaying publishers.
 * Extends the BaseFrame class and implements the ActionListener interface.
 */
public class Add_PublisherBtn extends BaseFrame implements ActionListener {
	
	// Declare class variables for the GUI components, database connection and model.
	JFrame frame		   =new JFrame();
	DefaultTableModel model=new DefaultTableModel();
	JTable table		   = new JTable();
	JScrollPane     jsp    = new JScrollPane();
	
	JLabel head			   =new JLabel("Add Publisher Information.");
	JLabel publisherid_L   =new JLabel("Publisher ID");
	JLabel publishername_L =new JLabel("Publisher Name");
	JLabel publisheremail_L=new JLabel("Publisher E-mail");
	JLabel publisherphone_L=new JLabel("Publisher Phone");
	JLabel subhead = new JLabel("Publisher Information");
	
	JTextField publisherid_T     =new JTextField();
	JTextField publishername_T   =new JTextField();
	JTextField publisheremail_T  =new JTextField();
	JTextField publisherphone_T  =new JTextField();
	
	JButton add_Btn    =new JButton("		Add		");
	JButton delete_Btn =new JButton("		Delete	");
	JButton update_Btn =new JButton("		Update	");
	JButton clear_Btn  =new JButton("		Clear	");
	JButton viewall_Btn=new JButton("		View All Publishers ");
	
	JPanel p1 =new JPanel();
	JPanel p2 =new JPanel();
	
	Statement stmt;
	String sql;

	/**
	 * Constructs an instance of the Add_PublisherBtn class.
	 */
	public Add_PublisherBtn() {
		// Initialize the GUI components and set up the screen.
		p1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(0, 10, 0, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		c.gridy = 0;
		p1.add(subhead, c);
		subhead.setFont(new Font("Script", Font.BOLD, 20));
		
		c.insets = new Insets(0, 5, 0, 5);
		
		c.gridy = 1;
		p1.add(publishername_L, c);
		
		c.gridy = 2;
		p1.add(publishername_T, c);
		
		c.gridy = 3;
		p1.add(publisheremail_L, c);
		
		c.gridy = 4;
		p1.add(publisheremail_T, c);
		
		c.gridy = 5;
		p1.add(publisherphone_L, c);
		
		c.gridy = 6;
		p1.add(publisherphone_T, c);
		
		p2.add(add_Btn);
		p2.add(update_Btn);
		p2.add(delete_Btn);
		p2.add(clear_Btn);
		p2.add(viewall_Btn);
		
		Object []colum= {"Publisher ID","Publisher Name","Publisher Email","Publisher Phone"};
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
		
		frame.setSize(800, 500); 
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		// Add action listeners for buttons
		add_Btn.addActionListener(this);
		update_Btn.addActionListener(this);
		delete_Btn.addActionListener(this);
		clear_Btn.addActionListener(this);
		viewall_Btn.addActionListener(this);
		
		// Add a MouseEvent that copy the data to the form when selecting a row in the table.
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
			int i=table.getSelectedRow();
			//publisherid_T.setText(model.getValueAt(i, 0).toString());
			publishername_T.setText(model.getValueAt(i, 1).toString());
			publisheremail_T.setText(model.getValueAt(i, 2).toString());
			publisherphone_T.setText(model.getValueAt(i, 3).toString());
			}
		});
	}

	/**
	 * Action performed when an event is triggered by a GUI component.
	 *
	 * @param e The event that was triggered.
	 */
	@Override
	public void actionPerformed(ActionEvent eee) {
		Connection conn;
		Object oo=eee.getSource();
		ResultSet rs;
		
		// Implements functionality for adding a new publisher's data to the library database
		if(oo==add_Btn) {
			conn = DB.connect();
			if(publishername_T.getText().isBlank() || publisheremail_T.getText().isBlank() || publisherphone_T.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Fill the information first!");
			}
			else {
			
			String n =publishername_T.getText();
			String em=publisheremail_T.getText();
			String p =publisherphone_T.getText();
	
			try {
				sql="INSERT INTO publishers (publisher_name, publisher_email, publisher_phone) VALUES (?, ?, ?)";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, n);
				pstmt.setString(2, em);
				pstmt.setString(3, p);
				int rowsAffected = pstmt.executeUpdate();
				if(rowsAffected == 1) {
					JOptionPane.showMessageDialog(this, "Added new Publisher successfully!");
					pstmt.close();
					DB.closeConnection(conn);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			}
		}

		// Implements functionality for updating the publisher's information.
		if(oo==update_Btn) {
			conn = DB.connect();
			int selectedRow = table.getSelectedRow();
			if(selectedRow==-1) {
				JOptionPane.showMessageDialog(null, "Select the row you want Update!");
			}
			else {
				int   i  =(int) model.getValueAt(selectedRow, 0);
				String n =publishername_T.getText();
				String em=publisheremail_T.getText();
				String p =publisherphone_T.getText();
				sql="UPDATE publishers SETet publisher_name=?, publisher_email=?, publisher_phone=? WHERE publisher_id = ?";
				
				try {
					PreparedStatement pstmt=conn.prepareStatement(sql);
					
					pstmt.setString(1, n);
					pstmt.setString(2, em);
					pstmt.setString(3, p);
					pstmt.setInt(4, i);
					int rowsAffected = pstmt.executeUpdate();
					if(rowsAffected == 1) {
						JOptionPane.showMessageDialog(this, "Updated Publisher Id: "+i+" successfully!");pstmt.close();
						pstmt.close();
						DB.closeConnection(conn);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			}
		}

		// Implements functionality for deleting a publisher's data from the library database
		if(oo==delete_Btn) {
			conn = DB.connect();
			int selectedRow = table.getSelectedRow();
			if(selectedRow ==-1) {
				JOptionPane.showMessageDialog(null, "Select the row you want Delete First!");
			}
			else {
				int deleteid=(int) model.getValueAt(selectedRow, 0);
				try {
					stmt=conn.createStatement();
					sql="DELETE FROM publishers WHERE publisher_id= "+deleteid;
					int rowsAffected = stmt.executeUpdate(sql);
					if(rowsAffected == 1) {
						JOptionPane.showMessageDialog(null, "Delete Publisher ID: "+deleteid+" successfully!");
						stmt.close();
						DB.closeConnection(conn);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			}
		}

		// Implements functionality for clearing the form and the table.
		if(oo==clear_Btn) {
			publisherid_T.setText(null);
			publishername_T.setText(null);
			publisheremail_T.setText(null);
			publisherphone_T.setText(null);
			model.setRowCount(0);
		}
		
		// Implements functionality for viewing all the publisher data in the table.
		if(oo==viewall_Btn) {
			conn = DB.connect();
			model.setRowCount(0);
			try {
				stmt=conn.createStatement();
				sql=" SELECT * FROM publishers ";
				rs=stmt.executeQuery(sql);
				while(rs.next()) {
				  model.addRow(new Object []{rs.getInt("publisher_id"),rs.getString("publisher_name"),rs.getString("publisher_email"),rs.getString("publisher_phone")});
				}
				stmt.close();
				DB.closeConnection(conn);
			}catch(SQLException eee2e) {
				eee2e.printStackTrace();
				JOptionPane.showMessageDialog(this, eee2e.getMessage());
			}
		}
	}
}