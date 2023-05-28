package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/**
 * The Search class represents a graphical user interface for searching books, authors, or publishers in a library database.
 * It extends the BaseFrame class and implements the ActionListener interface to handle user interactions.
 */
public class Search extends BaseFrame implements ActionListener {

	JPanel searchPane;
	JLabel searchLabel;
	JTextField searchField;
	JButton searchBtn, closeBtn;
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	String typeName;
	int type;
	
	/**
     * Constructs a Search object with the specified type.
     *
     * @param type the type of search (1: Author, 2: Book, 3: Publisher)
     */
	public Search(int type) {
		this.type = type;
		switch(type) {
			case(1):
				typeName = "Author";
				break;
			case(2):
				typeName = "Book";
				break;
			case(3):
				typeName = "Publisher";
				break;
		}
		this.setTitle("Search "+typeName);
		this.setSize(900, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		searchPane = new JPanel();
		searchPane.setLayout(new FlowLayout());
		searchLabel = new JLabel("Enter The "+typeName+" Name:");
		searchLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
		searchField = new JTextField(50);
		searchBtn = new JButton("Search");
		searchBtn.setPreferredSize(new Dimension(80, 30));
		searchBtn.addActionListener(this);
		closeBtn = new JButton("Close");
		closeBtn.setPreferredSize(new Dimension(80, 30));
		closeBtn.addActionListener(this);
		searchPane.add(searchLabel);
		searchPane.add(searchField);
		searchPane.add(searchBtn);
		searchPane.add(closeBtn);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[] {"Book Name", "Author", "Publisher", "Genre", "Book Shelf", "Release Date"});
		table = new JTable(model);
		//table.setEnabled(false);
		scrollPane = new JScrollPane(table);
		
		this.add(searchPane, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.setVisible(true);
		
		this.con = DB.connect();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchBtn) {
			// Performs the search in the database.
			model.setRowCount(0);
			String searchName = searchField.getText();
			if(!searchName.isEmpty()) {
				ResultSet rset = Account.search(type, searchName, con);
				try {
					while(rset.next()) {
						model.addRow(new Object[] {rset.getString("book_name"),
								 				   rset.getString("author_name"),
								 				   rset.getString("publisher_name"),
								 				   rset.getString("book_genre"),
								 				   rset.getString("book_location"),
								 				   rset.getString("release_date")
						});
					}
					rset.close();
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error in Connection to Database!", JOptionPane.ERROR_MESSAGE);
				}
				
				if(model.getRowCount() == 0) {
					switch(type) {
					case(1): 
						JOptionPane.showMessageDialog(this, "No author with the name '"+searchName+"'!");
						break;
					case(2):
						JOptionPane.showMessageDialog(this, "No book with the name '"+searchName+"'!");
						break;
					case(3):
						JOptionPane.showMessageDialog(this, "No publisher with the name '"+searchName+"'!");
						break;
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Enter a name first.");
			}			
		}
		else if(e.getSource() == closeBtn) {
			DB.closeConnection(con);
			this.dispose();
		}
	}
}
