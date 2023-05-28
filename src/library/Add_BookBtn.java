package library;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.*;

import com.toedter.calendar.*;

/**
 * Represents a GUI screen for adding and managing books in a library management system.
 * Provides functionality for adding, updating, deleting, and displaying books.
 * Extends the BaseFrame class and implements the ActionListener interface.
 */
public class Add_BookBtn extends BaseFrame implements ActionListener{

	// Declare class variables for GUI components, database connections, and model
	//
	java.sql.Statement stmt;
	Connection conn;
	ResultSet rs;
	String sql;
	JLabel head_L				=new JLabel("Add book Details and Information.");
	JLabel book_NameL			=new JLabel(" Book Name");
	JLabel book_GenreL			=new JLabel(" Genre");
	JLabel book_ReleaseL		=new JLabel(" Release Date");
	JLabel book_LocationL		=new JLabel(" Book Location");
	JLabel author_IdL			=new JLabel(" Author Id");
	JLabel publisher_IdL		=new JLabel(" Publisher Id");
	
	JTextField book_NameTF		=new JTextField(30);
	JTextField book_GenreTF		=new JTextField();
	JTextField book_ReleaseTF	=new JTextField(30);
	JTextField book_LocationTF	=new JTextField(30);
	JTextField author_IdTF		=new JTextField(30);
	JTextField publisher_IdTF	=new JTextField(30);
	JDateChooser datechooser=new JDateChooser();
	
	String []item=new String[] {"Select Book Genre","Mystery","Horro","Fantasy","Drame","Children's Literature","Short Story","History","Poetry","Self-help-book","Science fantasy","Biography","Literature"};
	
	DefaultTableModel model=new DefaultTableModel();
	JTable table           =new JTable(model);
	JScrollPane jsp		   =new JScrollPane(table);
	
	JPanel head_Panel   =new JPanel();
	JPanel form_Panel	=new JPanel();
	JPanel button_Panel	=new JPanel();
	
	JButton all_Btn		=new JButton("All Book");
	JButton add_Btn		=new JButton("Add new Book");
	JButton update      =new JButton("Update Book ");
	JButton delete      =new JButton("Delete Book ");
	JButton clear       =new JButton("Clear");
	JButton refresh     =new JButton("Refresh ");
	JButton print       =new JButton("Print");
	JButton save        =new JButton("Save");
	JFrame frame        =new JFrame();
	Librarian librarian;
	
	/**
	 * Constructs a new instance of the Add_BookBtn class.
	 *
	 * @param librarian The librarian associated with the screen.
	 */
	public Add_BookBtn(Librarian librarian) {
		// Initialize the GUI components and set up the screen
		this.librarian = librarian;
		frame.setTitle("Add book");
		frame.setSize(900,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		head_Panel.add(head_L);
		head_L.setFont(new Font("Georgia", Font.BOLD, 25));
		
		form_Panel.setLayout(new GridLayout(12,1));
		
		form_Panel.add(book_NameL);
		form_Panel.add(book_NameTF);
		
		form_Panel.add(book_GenreL);
		form_Panel.add(book_GenreTF);
		
		form_Panel.add(book_ReleaseL);
		datechooser.setDateFormatString("yyyy-MM-dd");
		form_Panel.add(datechooser);
		
		form_Panel.add(book_LocationL);
		form_Panel.add(book_LocationTF);
		
		form_Panel.add(author_IdL);
		form_Panel.add(author_IdTF);
		
		form_Panel.add(publisher_IdL);
		form_Panel.add(publisher_IdTF);
		
		button_Panel.add(all_Btn);
		button_Panel.add(add_Btn);
		button_Panel.add(update);
		button_Panel.add(delete);
		button_Panel.add(clear);
		button_Panel.add(refresh);
		button_Panel.add(save);
		button_Panel.add(print);
		
		frame.setLayout(new BorderLayout());
		frame.add(head_Panel,BorderLayout.NORTH);
		frame.add(button_Panel,BorderLayout.SOUTH);
		frame.add(form_Panel,BorderLayout.WEST);
		frame.add(jsp);
		
		// Add a MouseEvent that copy the data to the form when selecting a row in the table.
		model.setColumnIdentifiers(new Object[] {"Book Name","Book Genre","Release Date","Book Location","Author ID","Publisher ID","Librarian ID"});
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				int i=table.getSelectedRow();
				book_NameTF.setText(model.getValueAt(i, 1).toString());
				book_GenreTF.setText(model.getValueAt(i, 2).toString());
				Date dateString = new java.util.Date(((java.sql.Date) model.getValueAt(i, 3)).getTime());
				datechooser.setDate(dateString);
				book_LocationTF.setText(model.getValueAt(i, 4).toString());
				author_IdTF.setText(model.getValueAt(i, 5).toString());
				publisher_IdTF.setText(model.getValueAt(i, 6).toString());
			}
		});
		
		// Add event listeners to buttons
		all_Btn.addActionListener(this);
		add_Btn.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		clear.addActionListener(this);
		refresh.addActionListener(this);
		save.addActionListener(this);
		print.addActionListener(this);
	}

	/**
	 * Action performed when an event is triggered by a GUI component.
	 *
	 * @param e The event that was triggered.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object oo=e.getSource();

		// Implements functionality for showing all the books from the library database in the table
		if(oo==all_Btn) {
			conn = DB.connect();
			model.setRowCount(0);
			try {
				stmt=conn.createStatement();
				sql="select * from books";
				rs=stmt.executeQuery(sql);
				while (rs.next()) {
					model.addRow(new Object[] {rs.getInt("book_id"),rs.getString("book_name"),rs.getString("book_genre"),rs.getDate("release_date"),rs.getInt("book_location"),rs.getInt("author_id"),rs.getInt("publisher_id"), rs.getInt("librarian_id")});
				}
			    stmt.close();
			    DB.closeConnection(conn);
			}catch(SQLException eee) {
					eee.printStackTrace();
					JOptionPane.showMessageDialog(this, eee.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
			}
		}  

		// Implements functionality for adding a new book to the library database.
		if(oo==add_Btn) {
			conn = DB.connect();
			if(book_NameTF.getText().isBlank() || book_GenreTF.getText().isBlank() ||
					datechooser.getDate().equals(null) || book_LocationTF.getText().isBlank() || author_IdTF.getText().isBlank() || publisher_IdTF.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Fill the information first!");
			}
			else {						
				String bname=book_NameTF.getText();
			    String bgen=book_GenreTF.getText();
			    Date date= datechooser.getDate();
			    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				int loc=Integer.parseInt(book_LocationTF.getText());
				int auid=Integer.parseInt(author_IdTF.getText());
				int puid=Integer.parseInt(publisher_IdTF.getText());
				int libid=librarian.getLibrarian_id();
				try {
					sql = "insert into books (book_name, book_genre, release_date, book_location, author_id, publisher_id, librarian_id) values (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement  pstmt = conn.prepareStatement(sql);
				    pstmt.setString(1, bname);
				    pstmt.setString(2, bgen);
				    pstmt.setDate(3, sqlDate) ;
				    pstmt.setInt(4,    loc);
				    pstmt.setInt(5,    auid);
				    pstmt.setInt(6,    puid);
				    pstmt.setInt(7,    libid);
				    int rowsAffected = pstmt.executeUpdate();
				    if(rowsAffected == 1) {
					   JOptionPane.showMessageDialog(this, "Added new BOOK successfully!");					 
				   }
				   pstmt.close();
				   DB.closeConnection(conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}			

		// Implements functionality for updating a book's information.
		if(oo==update) {		    
				conn = DB.connect();
				int selectedRow = table.getSelectedRow();
			    if(selectedRow == -1) {
			    	JOptionPane.showMessageDialog(this, "Select the row you want to update!");
			    }
				else if(book_NameTF.getText().isBlank() || book_GenreTF.getText().isBlank() ||
						datechooser.getDate().equals(null) || book_LocationTF.getText().isBlank() || author_IdTF.getText().isBlank() || publisher_IdTF.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Fill the information first!");
				} else {
					int bid=(int) model.getValueAt(selectedRow, 0);
					String bname=book_NameTF.getText();
					String bgen=book_GenreTF.getText();
					Date releaseDate = datechooser.getDate();
					java.sql.Date sqlDate = new java.sql.Date(releaseDate.getTime());					
					int loc=Integer.parseInt(book_LocationTF.getText());
					int auid=Integer.parseInt(author_IdTF.getText());
					int puid=Integer.parseInt(publisher_IdTF.getText());
					int libid=librarian.getLibrarian_id();
					sql = "update books set book_name=?, book_genre=?, release_date=?, book_location=?, author_id=?, publisher_id=?, librarian_id=? where booK_id = ?";
					try { 
						PreparedStatement pstmt=conn.prepareStatement(sql);
					    pstmt.setString(1, bname);
					    pstmt.setString(2, bgen);
					    pstmt.setDate(3, sqlDate);
					    pstmt.setInt(4, loc);
					    pstmt.setInt(5, auid);
					    pstmt.setInt(6, puid);
					    pstmt.setInt(7, libid);
					    pstmt.setInt(8, bid);
					    int rowsAffected = pstmt.executeUpdate();
					    if(rowsAffected == 1) {
					    	JOptionPane.showMessageDialog(this, "Updated the book\'s data successfully!");
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

		// Implements functionality for deleting a book from the library database.
		if(oo==delete) {
			conn = DB.connect();
			int selectedRow = table.getSelectedRow();
			if(selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Select the row you want to delete!");
			}
			else {
				int deleteid=(int) model.getValueAt(selectedRow, 0);
				try {
					stmt=conn.createStatement();
					sql="delete from books where book_id = "+deleteid;
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Delete Book ID: "+deleteid+"Successfully");
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		// Implements functionality for clearing the form and table.
		if(oo==clear) {
			book_NameTF.setText(null);
			book_GenreTF.setText(null);
			datechooser.setDate(null);
			book_LocationTF.setText(null);
			author_IdTF.setText(null);
			publisher_IdTF.setText(null);
			model.setRowCount(0);
		}

		// Implements functionality for refreshing the book list.
		if(oo==refresh) {
			conn = DB.connect();
			model.setRowCount(0);
			try {
				stmt=conn.createStatement();
				sql="select * from books";
				rs=stmt.executeQuery(sql);
				while (rs.next()) {
					model.addRow(new Object[] {rs.getInt("book_id"),rs.getString("book_name"),rs.getString("book_genre"),rs.getDate("release_date"),rs.getInt("book_location"),rs.getInt("author_id"),rs.getInt("publisher_id"), rs.getInt("librarian_id")});
					}
				    stmt.close();
				}catch(SQLException eee) {
					eee.printStackTrace();
					JOptionPane.showMessageDialog(this, eee.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
				}
		}			

		// Implements functionality for saving book data to a file.
		if(oo==save) {
			JFileChooser file=new JFileChooser("C:\\Users\\hp\\Desktop");
			file.setDialogTitle("Select Location to Save File");  
		    int userselection=file.showSaveDialog(this);		  
		    if(userselection==JFileChooser.APPROVE_OPTION) {
		    	File savefile= file.getSelectedFile();			  
		    	
		    	try {
		    		FileWriter fw=new FileWriter(savefile);		  
		    		BufferedWriter bw=new BufferedWriter(fw);
		    		for(int row=0;row<table.getRowCount();row++) {
		    			for(int column=0;column<table.getColumnCount();column++) {
		    				bw.write(table.getValueAt(row, column).toString()+"\t");
		    			}
		    			bw.newLine();
		    		}		
		    		bw.close();
		    		fw.close();
		    		
		    	}catch(IOException e2) {
		    		e2.printStackTrace();
		    		JOptionPane.showMessageDialog(this, e2.getMessage(), "Error Writing File!", JOptionPane.ERROR_MESSAGE);
		    	}	
		    }
		}

		// Implements functionality for printing the book list
		if(oo==print) {
			try {
				table.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Printing!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
