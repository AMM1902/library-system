package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Represents the menu screen for library members in a library management system.
 * Allows members to access various functionalities such as searching for books, viewing their profile, and logging out.
 */
public class Member_Menu extends BaseFrame implements ActionListener {

	JLabel heading, imgHeading;
	JButton searchAuthorBtn, searchBookBtn, searchPublisherBtn, viewAllBooksBtn, borrowedBookBtn, viewProfileBtn, updateProfileBtn, logoutBtn, rulesBtn;
	JPanel funcBox, head, imgPanel, headingPanel, logoutPanel, rulesPanel, bottomPanel;
	ImageIcon projectImg = Resize.resize(new ImageIcon("resources/img/projectImg.png"), 150, 150);
	ImageIcon searchAuthorIcon = Resize.resize(new ImageIcon("resources/icon/search_author.png"));
	ImageIcon searchBookIcon = Resize.resize(new ImageIcon("resources/icon/search_book.png"));
	ImageIcon searchPublisherIcon = Resize.resize(new ImageIcon("resources/icon/search_publisher.png"));
	ImageIcon viewAllBooksIcon = Resize.resize(new ImageIcon("resources/icon/bookshelf.png"));
	ImageIcon borrowBookIcon = Resize.resize(new ImageIcon("resources/icon/borrow_book.png"));
	ImageIcon returnBookIcon = Resize.resize(new ImageIcon("resources/icon/return.png"));
	ImageIcon viewProfileIcon = Resize.resize(new ImageIcon("resources/icon/display_profile.png"));
	ImageIcon updateProfileIcon = Resize.resize(new ImageIcon("resources/icon/update_profile.png"));
	Member member;
	
	/**
     * Creates a new instance of Member_Menu.
     * Initializes and configures the graphical user interface.
     * @param member The Member object associated with the menu.
     */
	public Member_Menu(Member member) {
		this.member = member;
		this.setTitle("Member Menu");
		this.setSize(900, 600);
		Dimension panelSize = this.getSize();
		int panelWidth = (int) panelSize.getWidth();
		int panelHeight = (int) panelSize.getHeight();
		
		head = new JPanel();
		head.setLayout(new BoxLayout(head, BoxLayout.Y_AXIS));
		imgPanel = new JPanel();
		imgHeading = new JLabel(projectImg);
		imgPanel.add(imgHeading);
		headingPanel = new JPanel();
		heading = new JLabel("Member Menu");
		int fontSize = panelWidth/20;
		heading.setFont(new Font("Georgia", Font.BOLD, fontSize));
		headingPanel.add(heading);
		head.add(imgPanel);
		head.add(headingPanel);
		this.add(head, BorderLayout.NORTH);
		
		JPanel funcBox = new JPanel();
		funcBox.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		searchAuthorBtn = new JButton("Search Author");
		searchAuthorBtn.setIcon(searchAuthorIcon);
		searchAuthorBtn.setPreferredSize(new Dimension(panelWidth/5, panelHeight/6));
		searchAuthorBtn.setToolTipText("Swarch Author");
		
		searchBookBtn = new JButton("Search Book");
		searchBookBtn.setIcon(searchBookIcon);
		searchBookBtn.setPreferredSize(new Dimension(panelWidth/5, panelHeight/6));
		searchBookBtn.setToolTipText("Search Book");
		
		searchPublisherBtn = new JButton("Search Publisher");
		searchPublisherBtn.setIcon(searchPublisherIcon);
		searchPublisherBtn.setPreferredSize(new Dimension(panelWidth/5, panelHeight/6));
		searchPublisherBtn.setToolTipText("Search Publisher");
		
		viewAllBooksBtn = new JButton("View All Books");
		viewAllBooksBtn.setIcon(viewAllBooksIcon);
		viewAllBooksBtn.setPreferredSize(new Dimension(panelWidth/5, panelHeight/6));
		viewAllBooksBtn.setToolTipText("View All Books");
		
		borrowedBookBtn = new JButton("Borrowed Books");
		borrowedBookBtn.setIcon(borrowBookIcon);
		borrowedBookBtn.setPreferredSize(new Dimension(panelWidth/5, panelHeight/6));
		borrowedBookBtn.setToolTipText("Borrowed Books");
		
		viewProfileBtn = new JButton("View Profile");
		viewProfileBtn.setIcon(viewProfileIcon);
		viewProfileBtn.setPreferredSize(new Dimension(panelWidth/5, panelHeight/6));
		viewProfileBtn.setToolTipText("View Profile");
		
		updateProfileBtn = new JButton("Update Profile");
		updateProfileBtn.setIcon(updateProfileIcon);
		updateProfileBtn.setPreferredSize(new Dimension(panelWidth/5, panelHeight/6));
		updateProfileBtn.setToolTipText("Update Profile");
		
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		funcBox.add(searchAuthorBtn, constraints);
		
		constraints.gridx = 1;
		funcBox.add(searchBookBtn, constraints);
		
		constraints.gridx = 2;
		funcBox.add(searchPublisherBtn, constraints);
		
		constraints.gridx = 3;
		funcBox.add(viewAllBooksBtn, constraints);
		
		constraints.gridy = 1;
		
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		funcBox.add(borrowedBookBtn, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		funcBox.add(viewProfileBtn, constraints);
		
		constraints.gridx = 3;
		funcBox.add(updateProfileBtn, constraints);
		
		this.add(funcBox, BorderLayout.CENTER);
		
		logoutPanel = new JPanel();
		logoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		logoutBtn = new JButton("Log Out");
		logoutBtn.setPreferredSize(new Dimension(120, 50));
		logoutBtn.setMargin(new Insets(10, 10, 10, 10));
		logoutBtn.addActionListener(this);
		logoutPanel.add(logoutBtn);
		
		rulesPanel = new JPanel();
		rulesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		rulesBtn = new JButton("Rules");
		rulesBtn.setPreferredSize(new Dimension(120, 50));
		rulesBtn.addActionListener(this);
		rulesBtn.setMargin(new Insets(10, 10, 10, 10));
		rulesPanel.add(rulesBtn);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(rulesPanel);
		bottomPanel.add(logoutPanel);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		searchAuthorBtn.addActionListener(this);
		searchBookBtn.addActionListener(this);
		searchPublisherBtn.addActionListener(this);
		viewAllBooksBtn.addActionListener(this);
		borrowedBookBtn.addActionListener(this);
		viewProfileBtn.addActionListener(this);
		updateProfileBtn.addActionListener(this);
		imgHeading.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new ProjectProfile();
			}
		});
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
     * Handles button click events.
     * Performs the corresponding action based on the source of the event.
     * @param e The ActionEvent representing the event.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchAuthorBtn) {
			new Search(1);
		} else if(e.getSource() == searchBookBtn) {
			new Search(2);
		} else if(e.getSource() == searchPublisherBtn) {
			new Search(3);
		} else if(e.getSource() == borrowedBookBtn) {
			member.borrowedBooks();
		} else if(e.getSource() == viewProfileBtn) {
			member.viewProfile();
		} else if(e.getSource() == updateProfileBtn) {
			member.updateProfile();
		}
		else if(e.getSource() == viewAllBooksBtn) {
			new ViewAllBooks();
		}
		else if(e.getSource() == logoutBtn) {
			member.logout();
			this.dispose();
		}
	}
	
}
