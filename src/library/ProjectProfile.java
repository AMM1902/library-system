package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Represents the project profile screen that displays information about the project and its members.
 * Provides an interface to view project details and close the profile.
 */
public class ProjectProfile extends BaseFrame {

    JPanel imgPanel, memberPanel, closePanel;
    JLabel header, imgheader, member1, member2, member3, member4, member5;
    JButton closeBtn;
    ImageIcon projectImg = Resize.resize(new ImageIcon("resources/img/projectImg.png"), 150, 150);
    
    /**
     * Creates a new instance of ProjectProfile.
     * Initializes and configures the graphical user interface.
     */
    public ProjectProfile() {
        this.setTitle("Project Title");
        this.setSize(400, 500);
        
        // Create image panel and add project image
        imgPanel = new JPanel();
        imgheader = new JLabel(projectImg);
        imgPanel.add(imgheader);
        
        // Create member panel and configure layout
        memberPanel = new JPanel();
        memberPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Create header label for project members
        header = new JLabel("Project Members");
        header.setFont(new Font("Georgia", Font.BOLD, 25));
        
        // Create labels for each member
        member1 = new JLabel("Aung Myat Min");
        member1.setFont(member1.getFont().deriveFont(15f));
        member2 = new JLabel("Saw Mya San Aung");
        member2.setFont(member1.getFont());
        member3 = new JLabel("Sai Aung Paing Khant");
        member3.setFont(member1.getFont());
        member4 = new JLabel("Hein Min Thu");
        member4.setFont(member1.getFont());
        member5 = new JLabel("Swan Wanna Aung");
        member5.setFont(member1.getFont());
        
        // Set constraints and add components to member panel
        c.ipady = 10;
        c.gridy = 0;
        memberPanel.add(header, c);
        
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 1;
        memberPanel.add(member1, c);
        
        c.gridy = 2;
        memberPanel.add(member2, c);
        
        c.gridy = 3;
        memberPanel.add(member3, c);
        
        c.gridy = 4;
        memberPanel.add(member4, c);
        
        c.gridy = 5;
        memberPanel.add(member5, c);
        
        // Create close button and panel
        closeBtn = new JButton("Close");
        closePanel = new JPanel();
        closePanel.add(closeBtn);
        
        // Add components to the frame
        this.add(imgPanel, BorderLayout.NORTH);
        this.add(memberPanel, BorderLayout.CENTER);
        this.add(closePanel, BorderLayout.SOUTH);
        
        // Set frame properties
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        // Register action listener for close button
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == closeBtn) {
                    close();
                }
            }
        });
    }

    /**
     * Closes the project profile by disposing of the frame.
     */
    protected void close() {
        this.dispose();
    }
}
