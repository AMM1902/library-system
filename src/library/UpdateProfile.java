package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * The UpdateProfile class represents a graphical user interface for updating a member's profile in a library database.
 * It extends the BaseFrame class and implements the ActionListener interface to handle user interactions.
 */
public class UpdateProfile extends BaseFrame implements ActionListener {

    JPanel headingPane, imgPanel, headerPanel, profilePane, btnPane;
    JLabel imgLabel, headerLabel, nameLabel, idLabel, id, emailLabel, phoneLabel, addressLabel;
    JTextField nameField, emailField, phoneField, addressField;
    JButton closeBtn, updateBtn;
    Member member;
    ImageIcon img = Resize.resize(new ImageIcon("resources/img/projectImg.png"), 150, 150);

    /**
     * Constructs an UpdateProfile object.
     * Initializes the GUI components and sets up the frame.
     *
     * @param member the Member object representing the member whose profile is being updated
     */
    public UpdateProfile(Member member) {
        this.member = member;
        this.member.syncData();
        this.setSize(800, 600);
        this.setTitle("Update Profile");
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
        nameField = new JTextField(20);
        nameField.setText(member.username);
        idLabel = new JLabel("Member ID:");
        id = new JLabel(Integer.toString(member.getMember_id()));
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setText(member.email);
        phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        phoneField.setText(member.phone);
        addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressField.setText(member.address);

        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 0;
        profilePane.add(nameLabel, c);

        // ...

        updateBtn = new JButton("Update");
        updateBtn.setPreferredSize(new Dimension(80, 30));
        updateBtn.addActionListener(this);
        closeBtn = new JButton("Close");
        closeBtn.setPreferredSize(new Dimension(80, 30));
        closeBtn.addActionListener(this);
        btnPane = new JPanel();
        btnPane.add(updateBtn);
        btnPane.add(closeBtn);

        this.add(headingPane, BorderLayout.NORTH);
        this.add(profilePane, BorderLayout.CENTER);
        this.add(btnPane, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    /**
     * Handles the actionPerformed event for buttons.
     *
     * @param e the ActionEvent representing the user's action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateBtn) {
            Connection con = DB.connect();
            try {
                // Retrieve values from the text fields or use the existing values if the fields are empty
                String name = nameField.getText().isEmpty() ? member.username : nameField.getText();
                String email = emailField.getText().isEmpty() ? member.email : emailField.getText();
                String phone = phoneField.getText().isEmpty() ? member.phone : phoneField.getText();
                String address = addressField.getText().isEmpty() ? member.address : addressField.getText();

                String query = "UPDATE members"
                        + " SET "
                        + "member_name = ?, "
                        + "member_email = ?, "
                        + "member_phone = ?, "
                        + "member_address = ?"
                        + " WHERE member_id = ?;";

                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, phone);
                pstmt.setString(4, address);
                pstmt.setInt(5, member.getMember_id());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected != 1) {
                    // Check if the profile values were not changed
                    if (name.equals(member.username) && email.equals(member.email) &&
                            phone.equals(member.phone) && address.equals(member.address)) {
                        JOptionPane.showMessageDialog(this, "You need to CHANGE some VALUES to Update Profile!");
                    }
                } else if (rowsAffected == 1) {
                    JOptionPane.showMessageDialog(this, "Updated Successfully!");
                }
                member.syncData();
                pstmt.close();
                DB.closeConnection(con);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == closeBtn) {
            this.dispose();
        }
    }
}
