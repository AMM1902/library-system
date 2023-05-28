package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * The IssueBook class represents a frame for collecting the returned books from the library members.
 * Provides functionality for collecting issued books
 * Extends the BaseFrame class and implements ActionListener interface.
 */
public class ReturnBook extends BaseFrame implements ActionListener {
	
	int book_id;
	Member member;
	Librarian librarian;
	JPanel container, btnPane;
	JLabel header, bookIdLabel, memberIdLabel;
	JTextField bookIdField, memberIdField;
	JButton submitBtn, cancelBtn;
	
	/**
     * Constructs a ReturnBook object with the specified librarian and initializes the GUI.
     *
     * @param librarian the librarian associated with the return book operation
     */
	public ReturnBook(Librarian librarian) {
		this.librarian = librarian;
		this.setTitle("Return Book");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		header = new JLabel("Return Book");
		header.setFont(new Font("Georgia", Font.BOLD, 30));
		bookIdLabel = new JLabel("Book ID:");
		memberIdLabel = new JLabel("Member ID:");
		
		bookIdField = new JTextField(25);
		memberIdField = new JTextField(25);
		
		submitBtn = new JButton("Submit");
		cancelBtn = new JButton("Cancel");
		
		container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		container.add(header, c);
		
		c.gridy = 1;
		c.gridwidth = 1;
		container.add(bookIdLabel, c);
		
		c.gridx = 1;
		container.add(bookIdField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		container.add(memberIdLabel, c);
		
		c.gridx = 1;
		container.add(memberIdField, c);
		
		btnPane = new JPanel();
		btnPane.add(submitBtn);
		btnPane.add(cancelBtn);
		
		this.add(container, BorderLayout.CENTER);
		this.add(btnPane, BorderLayout.SOUTH);
		this.setVisible(true);
		
		submitBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitBtn) {
			// Handels function for submitting the returned books.
			String bookIdstr = bookIdField.getText();
			String memberIdstr = memberIdField.getText();
			if(bookIdstr.isEmpty() || memberIdstr.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Fill all the Fields!");
			} else {
				book_id = Integer.parseInt(bookIdstr);
				member = new Member(Integer.parseInt(memberIdstr));
				librarian.returnBook(book_id, member);
			}
			
		} else if(e.getSource() == cancelBtn) {
			// Handles the cancel button function
			this.dispose();
		}
	}
}
