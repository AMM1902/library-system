package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The ViewProfile class represents a graphical user interface for viewing a member's profile.
 * It extends the BaseFrame class and implements the ActionListener interface to handle user interactions.
 */
public class ViewProfile extends BaseFrame implements ActionListener {

    JPanel headingPane, imgPanel, headerPanel, profilePane, closePane;
    JLabel imgLabel, headerLabel, nameLabel, name, idLabel, id, emailLabel, email, phoneLabel, phone, addressLabel, address;
    JButton closeBtn;
    Member member;
    ImageIcon img = Resize.resize(new ImageIcon("resources/img/projectImg.png"), 150, 150);

    /**
     * Constructs a ViewProfile object.
     * Initializes the GUI components and sets up the frame.
     *
     * @param member the Member object representing the member whose profile is being viewed
     */
    public ViewProfile(Member member) {
        this.member = member;
        member.syncData();
        this.setSize(800, 500);
        this.setTitle("Profile");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        imgLabel = new JLabel(img);
        headerLabel = new JLabel("Profile");
        headerLabel.setFont(new Font("Georgia", Font.BOLD, 30));

        imgPanel = new JPanel();
        imgPanel.add(imgLabel);

        headerPanel = new JPanel();
        headerPanel.add(headerLabel);

        headingPane = new JPanel();
        headingPane.setLayout(new BoxLayout(headingPane, BoxLayout.Y_AXIS));
        headingPane.add(imgPanel);
        headingPane.add(headerPanel);

        profilePane = new JPanel();
        profilePane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        nameLabel = new JLabel("Name:");
        name = new JLabel(member.username);
        idLabel = new JLabel("Member ID:");
        id = new JLabel(Integer.toString(member.getMember_id()));
        emailLabel = new JLabel("Email:");
        email = new JLabel(member.email);
        phoneLabel = new JLabel("Phone Number:");
        phone = new JLabel(member.phone);
        addressLabel = new JLabel("Address:");
        address = new JLabel(member.address);

        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        profilePane.add(nameLabel, c);

        c.gridx = 1;
        profilePane.add(name, c);

        c.gridx = 0;
        c.gridy = 1;
        profilePane.add(idLabel, c);

        c.gridx = 1;
        profilePane.add(id, c);

        c.gridx = 0;
        c.gridy = 2;
        profilePane.add(emailLabel, c);

        c.gridx = 1;
        profilePane.add(email, c);

        c.gridx = 0;
        c.gridy = 3;
        profilePane.add(phoneLabel, c);

        c.gridx = 1;
        profilePane.add(phone, c);

        c.gridx = 0;
        c.gridy = 4;
        profilePane.add(addressLabel, c);

        c.gridx = 1;
        profilePane.add(address, c);

        closeBtn = new JButton("Close");
        closeBtn.setPreferredSize(new Dimension(80, 30));
        closeBtn.addActionListener(this);
        closePane = new JPanel();
        closePane.add(closeBtn);

        this.add(headingPane, BorderLayout.NORTH);
        this.add(profilePane, BorderLayout.CENTER);
        this.add(closePane, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    /**
     * Handles the actionPerformed event for buttons.
     *
     * @param e the ActionEvent representing the user's action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeBtn) {
            this.dispose();
        }
    }
}
