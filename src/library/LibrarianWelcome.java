package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Represents the welcome screen for the librarian section in a library management system.
 * Allows librarians to register or login to the system.
 */
public class LibrarianWelcome extends BaseFrame implements ActionListener {

	// Declares class variables for components.
	ImageIcon img = Resize.resize(new ImageIcon("resources/img/projectImg.png"), 150, 150);
	JLabel header, imgheader;
	JButton registerBtn, loginBtn, cancelBtn;
	JPanel headingPane, btnPane, cancelPane, imgPanel, headerPanel;
	
	/**
     * Creates a new instance of LibrarianWelcome.
     * Initializes and configures the graphical user interface.
     */
	public LibrarianWelcome() {
		this.setTitle("Librarian");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		imgPanel = new JPanel();
		imgheader = new JLabel(img);
		imgPanel.add(imgheader);
		headerPanel = new JPanel();
		header = new JLabel("Librarian");
		header.setFont(new Font("Georgia", Font.BOLD, 40));
		headerPanel.add(header);
		
		headingPane = new JPanel();
		headingPane.setLayout(new BoxLayout(headingPane, BoxLayout.Y_AXIS));
		headingPane.add(imgPanel);
		headingPane.add(headerPanel);
		
		btnPane = new JPanel();
		btnPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		registerBtn = new JButton("Register");
		registerBtn.setPreferredSize(new Dimension(200, 80));
		loginBtn = new JButton("Login");
		loginBtn.setPreferredSize(new Dimension(200, 80));
		
		c.insets = new Insets(10, 50, 10, 50);
		c.gridx = 0;
		c.gridy = 0;
		btnPane.add(registerBtn, c);
		
		c.gridx = 1;
		btnPane.add(loginBtn, c);
		
		cancelBtn = new JButton("Cancel");
		cancelPane = new JPanel();
		cancelPane.add(cancelBtn);
		
		this.add(headingPane, BorderLayout.NORTH);
		this.add(btnPane, BorderLayout.CENTER);
		this.add(cancelPane, BorderLayout.SOUTH);
		this.setVisible(true);
		
		registerBtn.addActionListener(this);
		loginBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}

	/**
     * Handles button click events.
     * @param e The ActionEvent representing the button click event.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == registerBtn) {
			new RegisterLibrarian();
			this.dispose();
		} else if(e.getSource() == loginBtn) {
			new LoginLibrarian();
			this.dispose();
		} else if(e.getSource() == cancelBtn) {
			new Welcome();
			this.dispose();
		}
	}
}
