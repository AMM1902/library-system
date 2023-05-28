package library;

import javax.swing.*;
import java.sql.*;

/**
 * The BaseFrame class represents the base frame of the library application
 * It extends the java.swing.JFrame class.
 * It sets the UI theme to NimbusLookAndFeel.
 */
public class BaseFrame extends JFrame {

	public Connection con;
	
	/**
	 * Creates a BaseFrame object
	 */
	public BaseFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
