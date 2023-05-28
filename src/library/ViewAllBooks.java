package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/**
 * The ViewAllBooks class represents a graphical user interface for displaying all books in a library database.
 * It extends the BaseFrame class and implements the ActionListener interface to handle user interactions.
 */
public class ViewAllBooks extends BaseFrame implements ActionListener {
    
	JLabel header;
    DefaultTableModel model;
    JTable bookTable;
    JScrollPane scrollPane;
    JPanel btnPane;
    JButton closeBtn;
    String[] columnNames = {"Book Name", "Author", "Publisher", "Genre", "Book Shelf", "Release Date"};

    /**
     * Constructs a ViewAllBooks object.
     * Initializes the GUI components and sets up the frame.
     */
    public ViewAllBooks() {
        this.setTitle("All Books");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        header = new JLabel("All Books");
        header.setFont(new Font("Georgia", Font.BOLD, 30));

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        this.setModel();
        bookTable = new JTable(model);
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        bookTable.setRowSorter(rowSorter);
        scrollPane = new JScrollPane(bookTable);

        btnPane = new JPanel();
        closeBtn = new JButton("Close");
        closeBtn.addActionListener(this);
        btnPane.add(closeBtn);

        this.add(header, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(btnPane, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    /**
     * Sets up the table model by retrieving data from the database and populating the table.
     */
    private void setModel() {
        Connection con = DB.connect();
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT books.book_name, authors.author_name, publishers.publisher_name, " +
                    "books.book_genre, books.book_location, books.release_date " +
                    "FROM books " +
                    "JOIN authors ON books.author_id = authors.author_id " +
                    "JOIN publishers ON books.publisher_id = publishers.publisher_id";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("book_name"),
                        rs.getString("author_name"),
                        rs.getString("publisher_name"),
                        rs.getString("book_genre"),
                        rs.getString("book_location"),
                        rs.getString("release_date")
                });
            }
            rs.close();
            DB.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Connecting to Database!", JOptionPane.ERROR_MESSAGE);
        }
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
