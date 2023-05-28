package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Represents the login screen for librarians in a library management system.
 * Allows librarians to enter their username and password to log in.
 */
public class LoginLibrarian extends BaseFrame implements ActionListener {
	
	// Class Implementation
	ImageIcon logoImg=Resize.resize(new ImageIcon("resources/img/projectImg.png"), 150, 150);
	
	JLabel logoL	=new JLabel (logoImg);
	JLabel userL	=new JLabel("User Name :");
	JLabel passordL =new JLabel("Password   :");
	JLabel headL    =new JLabel("Log in as Librarian");
	JCheckBox showPwBox = new JCheckBox("Show Password");
	
	JTextField usertT    =new JTextField(20);
	JPasswordField passwordT =new JPasswordField(20);
	
	JButton loginBtn	     =new JButton("Login");
	JButton cancelBtn	 =new JButton("Cancel");
	
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	
	/**
     * Creates a new instance of LoginLibrarian.
     * Initializes and configures the graphical user interface.
     */
	public LoginLibrarian() {
		this.setTitle("Library Login ");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		p.setLayout(new BorderLayout());
		headL.setFont(new Font("Georgia", Font.BOLD, 25));
		headL.setHorizontalAlignment(SwingConstants.CENTER);
		p.add(logoL,BorderLayout.CENTER);
		p.add(headL,BorderLayout.SOUTH);
		
		p1.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		gbc.fill= GridBagConstraints.HORIZONTAL;
		
		gbc.ipady=10;
		gbc.gridx=0;
		gbc.gridy=0;
		p1.add(userL,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		p1.add(usertT,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		p1.add(passordL,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		p1.add(passwordT,gbc);
		
		gbc.insets =  new Insets(0, 5, 5, 5);
		gbc.ipady = 0;
		gbc.gridy = 2;
		p1.add(showPwBox, gbc);
		
		p2.add(loginBtn);
		loginBtn.setFocusable(false);
		p2.add(cancelBtn);
		cancelBtn.setFocusable(false);
		
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(p,BorderLayout.NORTH);
		c.add(p1,BorderLayout.CENTER);
		c.add(p2,BorderLayout.SOUTH);
		this.setVisible(true);
		
		loginBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		showPwBox.addActionListener(this);
	}
	
	/**
     * Handles button click events and checkbox state change events.
     * @param e The ActionEvent representing the event.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Implements functionality for logging in
		if(e.getSource() == loginBtn) {
			
			String username = usertT.getText();
			String password =new String(passwordT.getPassword());
			if(username.isEmpty() && password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Fill in all the fields!");
			}
			else {
				Librarian librarian = new Librarian(username, password);
				if(librarian.login()) {
					new LibrarianMenu(librarian);
					this.dispose();
				}
			}
		} else if(e.getSource() == cancelBtn) {
			new Welcome();
			this.dispose();
		} else if(e.getSource() == showPwBox) {
			// Implements functionality for showing password
			if(showPwBox.isSelected()) {
				passwordT.setEchoChar((char) 0);
			} else {
				passwordT.setEchoChar('*');
			}
		}
	}
}
