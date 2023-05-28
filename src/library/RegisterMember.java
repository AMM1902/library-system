package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Represents the registration form for librarians.
 * Provides an interface to register a librarian by entering their details.
 */
public class RegisterMember extends BaseFrame implements ActionListener{

	ImageIcon logoImg=Resize.resize(new ImageIcon("resources/img/projectImg"), 150, 150);
	
	JLabel logoL	=new JLabel (logoImg);
	JLabel userL	=new JLabel("Name ");
	JLabel emailL	=new JLabel("Email    ");
	JLabel phonenoL =new JLabel("Phone   ");
	JLabel addressL =new JLabel("Address  ");
	JLabel passordL =new JLabel("Password   ");
	JLabel headL    =new JLabel("Registration");
	
	JTextField usertT        =new JTextField(20);
	JTextField emailT	     =new JTextField(20);
	JTextField phonenoT      =new JTextField(20);
	JTextField addressT      =new JTextField(20);
	JPasswordField passwordT =new JPasswordField(20);
	JCheckBox showPwBox = new JCheckBox("Show Password");
	
	JButton registerBtn	     =new JButton("Register");
	JButton cancelBtn	     =new JButton("Cancel");
	
	JPanel p =new JPanel();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();

	/**
     * Creates a new instance of RegisterLibrarian.
     * Initializes and configures the graphical user interface.
     */
	public RegisterMember() {
		this.setTitle("Member Register ");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
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
		p1.add(emailL,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		p1.add(emailT,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		p1.add(phonenoL,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		p1.add(phonenoT,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		p1.add(addressL,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		p1.add(addressT,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		p1.add(passordL,gbc);
		
		gbc.gridx=1;
		gbc.gridy=4;
		p1.add(passwordT,gbc);
		
		gbc.insets = new Insets(0, 5, 5, 5);
		gbc.ipady = 0;
		gbc.gridy = 5;
		p1.add(showPwBox, gbc);
		
		p2.add(registerBtn);
		registerBtn.setFocusable(false);
		p2.add(cancelBtn);
		cancelBtn.setFocusable(false);
		
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(p,BorderLayout.NORTH);
		c.add(p1,BorderLayout.CENTER);
		c.add(p2,BorderLayout.SOUTH);
		this.setVisible(true);	
		registerBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		showPwBox.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		Object source=e.getSource();
		if(source==registerBtn) {
			// Handles the registration logic
			Connection conn=DB.connect();
			PreparedStatement pstmt;
			String sql;
			if(usertT.getText().isEmpty() || emailT.getText().isEmpty() || phonenoT.getText().isEmpty()|| phonenoT.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Fill the input first!!");
			}
			else {
			String n= usertT.getText();
			String em=emailT.getText();
			String ph=phonenoT.getText();
			String ad=addressT.getText();
			String pa=new String(passwordT.getPassword());
			
			sql="insert into members (member_name, member_password, member_email, member_address, member_phone) values(?, ?, ?, ?, ?)";
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, n);
				pstmt.setString(2, pa);
				pstmt.setString(3, em);
				pstmt.setString(4, ad);
				pstmt.setString(5, ph);
				int rowsaffected = pstmt.executeUpdate();
				if(rowsaffected == 1) {
					JOptionPane.showMessageDialog(this," Registered Successfully");
					pstmt.close();
					DB.closeConnection(conn);
					
					new LoginMember();
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Registration Failed! Try Again");
					usertT.setText(null);
					emailT.setText(null);
					phonenoT.setText(null);
					addressT.setText(null);
					passwordT.setText(null);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
			}
			}
				
		}
		
		if(source==cancelBtn) {
			// Handles the cancel button function
			new Welcome();
			this.dispose();
		}
		
		if(e.getSource() == showPwBox) {
			// Handles the show password box function
			if(showPwBox.isSelected()) {
				passwordT.setEchoChar((char) 0);
			} else {
				passwordT.setEchoChar('*');
			}
		}
		
	}
}
