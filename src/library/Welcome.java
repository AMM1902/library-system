package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The Welcome class represents a graphical user interface for the welcome screen of the library application.
 * It extends the BaseFrame class and implements the ActionListener interface to handle user interactions.
 */
public class Welcome extends BaseFrame implements ActionListener {

    JPanel loginPane, labelPane, headPane, btnPane, closePane;
    JLabel notice, heading, imgLabel, orLabel;
    JButton librarianBtn, memberBtn, searchAuthorBtn, searchBookBtn, searchPublisherBtn, closeBtn;
    ImageIcon projectImg = Resize.resize(new ImageIcon("resources/img/projectImg.png"), 200, 200);
    ImageIcon searchAuthorIcon = Resize.resize(new ImageIcon("resources/icon/search_author.png"));
    ImageIcon searchBookIcon = Resize.resize(new ImageIcon("resources/icon/search_book.png"));
    ImageIcon searchPublisherIcon = Resize.resize(new ImageIcon("resources/icon/search_publisher.png"));

    /**
     * Constructs a Welcome object.
     * Initializes the GUI components and sets up the frame.
     */
    public Welcome() {
        this.setTitle("Welcome");
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginPane = new JPanel();
        loginPane.setLayout(new FlowLayout());

        notice = new JLabel("Log In/Register to Borrow Books >>>>>");
        notice.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 14));
        loginPane.add(notice);
        loginPane.setBorder(BorderFactory.createEtchedBorder());

        orLabel = new JLabel("\tor\t");

        librarianBtn = new JButton("Librarian");
        memberBtn = new JButton("Member");
        loginPane.add(librarianBtn);
        loginPane.add(orLabel);
        loginPane.add(memberBtn);

        labelPane = new JPanel();
        labelPane.setLayout(new BoxLayout(labelPane, BoxLayout.Y_AXIS));

        JPanel imgPanel = new JPanel();
        imgLabel = new JLabel(projectImg);
        JPanel labelPanel = new JPanel();
        heading = new JLabel("Welcome!");
        heading.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        imgPanel.add(imgLabel);
        labelPanel.add(heading);

        labelPane.add(imgPanel);
        labelPane.add(labelPanel);

        headPane = new JPanel();
        headPane.setLayout(new BoxLayout(headPane, BoxLayout.Y_AXIS));
        headPane.add(loginPane);
        headPane.add(labelPane);

        Dimension paneSize = this.getSize();
        int paneWidth = (int) paneSize.getWidth();
        int paneHeight = (int) paneSize.getHeight();
        searchAuthorBtn = new JButton("Search Author");
        searchAuthorBtn.setPreferredSize(new Dimension(200, 100));
        searchAuthorBtn.setIcon(searchAuthorIcon);
        searchBookBtn = new JButton("Search Book");
        searchBookBtn.setPreferredSize(new Dimension(200, 100));
        searchBookBtn.setIcon(searchBookIcon);
        searchPublisherBtn = new JButton("Search Publisher");
        searchPublisherBtn.setPreferredSize(new Dimension(200, 100));
        searchPublisherBtn.setIcon(searchPublisherIcon);
        btnPane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        btnPane.add(searchAuthorBtn, c);

        c.gridx = 1;
        btnPane.add(searchBookBtn, c);

        c.gridx = 2;
        btnPane.add(searchPublisherBtn, c);

        closePane = new JPanel();
        closeBtn = new JButton("Close");
        closeBtn.setPreferredSize(new Dimension(100, 40));
        closePane.add(closeBtn);

        librarianBtn.addActionListener(this);
        memberBtn.addActionListener(this);
        searchAuthorBtn.addActionListener(this);
        searchBookBtn.addActionListener(this);
        searchPublisherBtn.addActionListener(this);
        closeBtn.addActionListener(this);

        this.add(btnPane, BorderLayout.CENTER);
        this.add(headPane, BorderLayout.NORTH);
        this.add(closePane, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    /**
     * Handles the user's actions performed on the GUI components.
     * @param e the ActionEvent representing the user's action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchAuthorBtn) {
            new Search(1);
        } else if (e.getSource() == searchBookBtn) {
            new Search(2);
        } else if (e.getSource() == searchPublisherBtn) {
            new Search(3);
        } else if (e.getSource() == closeBtn) {
            this.dispose();
        } else if (e.getSource() == librarianBtn) {
            new LibrarianWelcome();
            this.dispose();
        } else if (e.getSource() == memberBtn) {
            new MemberWelcome();
            this.dispose();
        }
    }
}
