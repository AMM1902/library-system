package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/**
 * The LibrarianMenu class represents the graphical user interface for the librarian's menu.
 * It allows the librarian to perform various actions such as displaying books, adding books,
 * adding authors, adding publishers, deleting books, updating book locations, issuing books,
 * viewing issued books, returning books, performing backup and restore operations, and logging out.
 */
public class LibrarianMenu extends BaseFrame implements ActionListener {

	Statement stmt;
	ResultSet rs;
	String sql;
	Librarian librarian;
	JButton display_BookBtn    = new JButton("Display Books");
	JButton display_UserBtn    = new JButton("Display Users");
	JButton add_BookBtn        = new JButton("Add Book");
	JButton add_AuthorBtn      = new JButton("Add Author");
	JButton add_PublisherBtn   = new JButton("Add Publisher");
	JButton delete_BookBtn     = new JButton("Delete book ");
	JButton update_LocationBtn = new JButton("Update Location ");
	JButton issueBookBtn     = new JButton("Issue Book");
	JButton issued_BookBtn     = new JButton("Issued Book ");
	JButton return_BookBtn     = new JButton("Return Book");
	JButton backupBtn = new JButton("Back Up");
	JButton restoreBtn = new JButton("Restore");
	JButton log_OutBtn         = new JButton("Log Out ");

	ImageIcon logo_Img       = Resize.resize(new ImageIcon("resources/img/projectImg.png"), 150, 150);
	JLabel logo_Label 		 = new JLabel(logo_Img);
	JLabel header = new JLabel("Librarian Menu");
	JPanel logo_Panel 		 = new JPanel();
	JPanel button_Panel		 = new JPanel();
	JPanel button2_Panel	 = new JPanel();
	
	ImageIcon displayBookIcon = Resize.resize(new ImageIcon("resources/icon/bookshelf.png"));
	ImageIcon displayUserIcon = Resize.resize(new ImageIcon("resources/icon/user.png"));
	ImageIcon addBookIcon = Resize.resize(new ImageIcon("resources/icon/add_book.png"));
	ImageIcon addAuthorIcon = Resize.resize(new ImageIcon("resources/icon/add-user(author).png"));
	ImageIcon addPublisherIcon = Resize.resize(new ImageIcon("resources/icon/add_publisher.png"));
	ImageIcon deleteBookIcon = Resize.resize(new ImageIcon("resources/icon/delete_book.png"));
	ImageIcon updateLocationIcon = Resize.resize(new ImageIcon("resources/icon/update_location.png"));
	ImageIcon issueBookIcon = Resize.resize(new ImageIcon("resources/icon/borrow.png"));
	ImageIcon returnBookIcon = Resize.resize(new ImageIcon("resources/icon/return.png"));
	ImageIcon issuedBooksIcon = Resize.resize(new ImageIcon("resources/icon/issued_book.png"));
	ImageIcon backupIcon = Resize.resize(new ImageIcon("resources/icon/backup.png"));
	ImageIcon restoreIcon = Resize.resize(new ImageIcon("resources/icon/restore.png"));

	JTextArea display_Text_Area = new JTextArea();

	/**
     * Creates a new instance of the LibrarianMenu class.
     * 
     * @param librarian The librarian object associated with the menu.
     */
	public LibrarianMenu(Librarian librarian) {
		this.librarian = librarian;
		this.setTitle("Librarian Menu");
		this.setSize(900,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		logo_Panel.setLayout(new BorderLayout());
		logo_Panel.add(logo_Label, BorderLayout.NORTH);
		
		header.setFont(new Font("Georgia", Font.BOLD, 30));
		header.setHorizontalAlignment(SwingConstants.CENTER);
		logo_Panel.add(header, BorderLayout.CENTER);
		
		Dimension frameSize = this.getSize();
		int framewidth = (int) frameSize.getWidth();
		int frameheight = (int) frameSize.getHeight();
		
		button_Panel.setLayout(new GridBagLayout());
		GridBagConstraints gb=new GridBagConstraints();
		gb.insets = new Insets(4, 4, 4, 4);
		gb.fill=  GridBagConstraints.HORIZONTAL;
		gb.weightx = 1;
		gb.ipady=15;	
		gb.gridx=0;
		gb.gridy=0;
		button_Panel	.add(display_BookBtn,gb);
		display_BookBtn	.setFocusable(false);
		display_BookBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		display_BookBtn.setIcon(displayBookIcon);
		
		gb.gridx=1;
		gb.gridy=0;
		button_Panel	.add(display_UserBtn,gb);
		display_UserBtn .setFocusable(false);
		display_UserBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		display_UserBtn.setIcon(displayUserIcon);
		
		gb.gridx=2;
		gb.gridy=0;
		button_Panel	.add(add_BookBtn,gb);
		add_BookBtn		.setFocusable(false);
		add_BookBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		add_BookBtn.setIcon(addBookIcon);
		
		gb.gridx=3;
		gb.gridy=0;
		button_Panel	.add(add_AuthorBtn,gb);
		add_AuthorBtn	.setFocusable(false);
		add_AuthorBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		add_AuthorBtn.setIcon(addAuthorIcon);
		
		gb.gridx=0;
		gb.gridy=1;
		button_Panel	 .add(add_PublisherBtn,gb);
		add_PublisherBtn .setFocusable(false);
		add_PublisherBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		add_PublisherBtn.setIcon(addPublisherIcon);
		
		gb.gridx=1;
		gb.gridy=1;
		button_Panel	 .add(delete_BookBtn,gb);
		delete_BookBtn   .setFocusable(false);
		delete_BookBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		delete_BookBtn.setIcon(deleteBookIcon);
		
		gb.gridx=2;
		gb.gridy=1;
		button_Panel	 .add(update_LocationBtn,gb); 
		update_LocationBtn.setFocusable(false);
		update_LocationBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		update_LocationBtn.setIcon(updateLocationIcon);
		
		gb.gridx=3;
		gb.gridy=1;
		button_Panel	 .add(issueBookBtn,gb);
		issueBookBtn	 .setFocusable(false);
		issueBookBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		issueBookBtn.setIcon(issueBookIcon);
		
		gb.gridx=0;
		gb.gridy=2;
		
		button_Panel	.add(return_BookBtn,gb);
		return_BookBtn  .setFocusable(false);
		return_BookBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		return_BookBtn.setIcon(returnBookIcon);
		
		gb.gridx=1;
		gb.gridy=2;
		button_Panel 	.add(issued_BookBtn,gb);
		issued_BookBtn  .setFocusable(false);
		issued_BookBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		issued_BookBtn.setIcon(issuedBooksIcon);
		
		gb.gridx = 2;
		button_Panel.add(backupBtn, gb);
		backupBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		backupBtn.setIcon(backupIcon);
		
		gb.gridx = 3;
		button_Panel.add(restoreBtn, gb);
		restoreBtn.setPreferredSize(new Dimension(framewidth/5, frameheight/5));
		restoreBtn.setIcon(restoreIcon);
		
		button2_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		log_OutBtn   .setMargin(new Insets(10, 10, 10, 10));
		log_OutBtn   .setFocusable(false);
		button2_Panel.add(log_OutBtn);
		
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		c.add(logo_Panel,	BorderLayout.NORTH);				
		c.add(button_Panel,	BorderLayout.CENTER);				
		c.add(button2_Panel,BorderLayout.SOUTH);				
		this.setVisible(true);
		
		// Provides a frame for displaying the book data from the database.
		display_BookBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
        		Connection con = DB.connect();
                JFrame f = new JFrame("Display All Book");
                f.setSize(800, 500);
                f.setLocationRelativeTo(null);
                DefaultTableModel model=new DefaultTableModel();
                try {
    				stmt=con.createStatement();
    			    sql="SELECT * FROM books ";
    				rs=stmt.executeQuery(sql);
    				
    				model.setColumnIdentifiers(new Object[] {"Book Id","Book Name","Book Genre","Release Date","Book Location","Author ID","Publisher ID","Librarian ID"});
    				while (rs.next()) {
    					model.addRow(new Object[] {rs.getInt("book_id"),rs.getString("book_name"),rs.getString("book_genre"),rs.getString("release_date"),rs.getInt("book_location"),rs.getInt("author_id"),rs.getInt("publisher_id"), rs.getInt("librarian_id")} );
    					
    				}
    			    stmt.close();
                   // f.setSize(800, 400);                
                    
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(f, e1.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
                } 
                    JTable table=new JTable(model);
    			    JScrollPane scrollPane=new JScrollPane(table);
    			    f.add(scrollPane, BorderLayout.CENTER);
    			    
    			    JButton closeBtn = new JButton("Close");
    			    JPanel btnPane = new JPanel();
    			    btnPane.add(closeBtn);
    			    f.add(btnPane, BorderLayout.SOUTH);
                    f.setVisible(true);
                    
                    closeBtn.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent a) {
                    		if(a.getSource() == closeBtn) {
                    			DB.closeConnection(con);
                    			f.dispose();
                    		}
                    	}
                    });
        		}
		});

		// Provides a frame for displaying all the members registered in the database.
		display_UserBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = DB.connect();
				JFrame f = new JFrame("Display All User");
				f.setSize(800, 500);
                f.setLocationRelativeTo(null);
                try {
					stmt=con.createStatement();
				    sql="select * from members ";
					rs=stmt.executeQuery(sql);
					DefaultTableModel model=new DefaultTableModel();
					model.setColumnIdentifiers(new Object [] {"User ID","User Name","Password","Email","Phone"+"Address"});
					
					while(rs.next()) {
						model.addRow(new Object[] {rs.getInt("member_id"),rs.getString("member_name"),rs.getString("member_password"),rs.getString("member_email"),rs.getString("member_phone"),rs.getString("member_address")});
					}
					
				    stmt.close();
				    JTable table=new JTable(model);
				    JScrollPane scrollPane=new JScrollPane(table);
	  			    f.add(scrollPane);
	  			    JButton closeBtn = new JButton("Close");
  			        JPanel btnPane = new JPanel();
  			        btnPane.add(closeBtn);
  			        f.add(btnPane, BorderLayout.SOUTH);
                    f.setVisible(true);
                  
                    closeBtn.addActionListener(new ActionListener() {
                  	public void actionPerformed(ActionEvent a) {
                  	 	if(a.getSource() == closeBtn) {
                  			DB.closeConnection(con);
                  			f.dispose();
                  	 		}
                  		}
                    });            
                } catch (SQLException e1) {
	                e1.printStackTrace();
	                JOptionPane.showMessageDialog(f, e1.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
                }                   	
			}
		});
	
		// Add action listeners to other buttons.
		add_BookBtn.addActionListener(this);
		add_AuthorBtn.addActionListener(this);
		add_PublisherBtn.addActionListener(this);
		delete_BookBtn.addActionListener(this);
		update_LocationBtn.addActionListener(this);
		issueBookBtn.addActionListener(this);
		issued_BookBtn.addActionListener(this);
		return_BookBtn.addActionListener(this);
		backupBtn.addActionListener(this);
		restoreBtn.addActionListener(this);
		log_OutBtn.addActionListener(this);	
		logo_Label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new ProjectProfile();
			}
		});
	}

	/**
     * Handles the action events generated by the menu buttons.
     * @param e The ActionEvent object that represents the user's action.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source==add_BookBtn) {
			new Add_BookBtn(librarian);
		}
		if (source == add_AuthorBtn) {
			new Add_AuthorBtn();
		}
		if(source==add_PublisherBtn) {
			new Add_PublisherBtn();
		}
		if(source==delete_BookBtn) {
			new Delete_BookBtn();
		}
		if(source==update_LocationBtn) {
			new Update_LocationBtn();
		}
		
		if(source==issueBookBtn) {
			new IssueBook(librarian);
		}
		
		if(source == issued_BookBtn) {
			new IssuedBooks(librarian);
		}
		
		if(source == return_BookBtn) {
			new ReturnBook(librarian);
		}
		
		if (source == log_OutBtn) {
		   if(JOptionPane.showConfirmDialog(this, "Are you sure want to log out?","Print System",JOptionPane.YES_NO_OPTION)==
			  JOptionPane.YES_NO_OPTION) {
			  new Welcome();			  
			  this.dispose();
		   }
		}
		
		if(source == backupBtn) {
			librarian.backup();
		}
		
		if(source == restoreBtn) {
			librarian.restore();
		}
	}
}