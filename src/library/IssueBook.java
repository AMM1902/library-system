package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.*;

import com.toedter.calendar.JDateChooser;

/**
 * The IssueBook class represents a frame for issuing books to library members.
 * Provides functionality for issuing books
 * Extends the BaseFrame class and implements ActionListener interface.
 */
public class IssueBook extends BaseFrame implements ActionListener {
	JFrame frame    =new JFrame();
	JLabel head_L   =new JLabel("Issue Book");
	JLabel bookid_L =new JLabel("Fill book ID : ");
	JLabel member_Id=new JLabel("Fill Member ID :");
	JLabel issue_L  =new JLabel("Issue Date: ");
	JLabel retut_Id =new JLabel("Return Date :");
	JLabel note_L   =new JLabel("Note. :");
	
	JTextField bookid_F  =new JTextField(50);
	JTextField memberid_F=new JTextField(50);
	
	JDateChooser issue =new JDateChooser();
	JDateChooser retur =new JDateChooser();

	
	JButton issueBtn  =new JButton("Issue");
	JButton cancleBtn =new JButton("Cancle");
	JTextArea comA=new JTextArea(5,10);
	JScrollPane jsp=new JScrollPane(comA);

	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JPanel p3=new JPanel();
	Librarian librarian;
	
	/**
	 * Constructs an instance of the IssueBook class.
	 * 
	 * @param librarian The librarian associated with the issue
	 */
	public IssueBook(Librarian librarian) {
		this.librarian = librarian;
		frame.setTitle("Issue Book");
		frame.setSize(600,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		head_L.setFont(new Font("Georgia", Font.BOLD, 25));
		head_L.setHorizontalAlignment(SwingConstants.CENTER);
		p.setLayout(new GridBagLayout());
		GridBagConstraints gb=new GridBagConstraints();
		gb.fill=GridBagConstraints.HORIZONTAL;
		
		gb.ipadx=89;	
		gb.ipady=8;	
		gb.gridx=0;
		gb.gridy=0;
		p.add(bookid_L,gb);
		
		gb.gridx=1;
		gb.gridy=0;
		p.add(bookid_F,gb);
		
		gb.gridx=0;
		gb.gridy=1;
		p.add(member_Id,gb);
		
		gb.gridx=1;
		gb.gridy=1;
		p.add(memberid_F,gb);
		
		gb.gridx=0;
		gb.gridy=2;
		p.add(issue_L,gb);
		
		gb.gridx=1;
		gb.gridy=2;
		p.add(issue,gb);
		issue.setDateFormatString("yyyy-MM-dd");
		
		gb.gridx=0;
		gb.gridy=4;
		p.add(note_L,gb);
		
		gb.fill=GridBagConstraints.HORIZONTAL;
		gb.weightx=0.0;
		gb.gridwidth = 3;
		gb.ipady=80;
		gb.gridx=0;
		gb.gridy=5;
		p.add(jsp,gb);
		note_L.setFont(new Font("Script", Font.BOLD, 20));
		p1.add(issueBtn);
		p1.add(cancleBtn);
		p3.setLayout(new BorderLayout());
		p3.add(p1,BorderLayout.SOUTH);
		frame.setLayout(new BorderLayout());
		frame.add(head_L, BorderLayout.NORTH);
		frame.add(p,BorderLayout.CENTER);
		frame.add(p1,BorderLayout.SOUTH);	
		
		issueBtn.addActionListener(this);
		cancleBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Connection conn=DB.connect();
		String sql;
		Object source=e.getSource();
		
		// Implements functionality for issuing a book.
		if(source==issueBtn) {
			sql="insert into borrowed_books (book_id,member_id, issuer_id,issued_date) values(?,?, ?, ?)";
			
			int bid=Integer.parseInt(bookid_F.getText());
			int mid=Integer.parseInt(memberid_F.getText());
			int iid = librarian.getLibrarian_id();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date1=sdf.format(issue.getDate());
						
			try {
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, bid);
				pstmt.setInt(2, mid);
				pstmt.setInt(3, iid);
				pstmt.setString(4, date1);
				int rowsaffected = pstmt.executeUpdate();
				if(rowsaffected == 1) {
					JOptionPane.showMessageDialog(this, "Issue Successfully.");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		// Implements functionality for exiting
		if(source==cancleBtn) {
			if(JOptionPane.showConfirmDialog(frame, "Are you sure want to exit","Print System",JOptionPane.YES_NO_OPTION)==
					   JOptionPane.YES_NO_OPTION) {
				   this.dispose();
			}
		}
		
	}
}