package library;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * The Restore class is responsible for restoring the backup library database.
 */
public class Restore {

	String username = "root";
	String password = "eclipse@polis";
	String database = "library";
	
	/**
     * Constructs a Restore object with the specified file path and performs a database restore.
     *
     * @param filepath the file path of the database backup file to restore
     */
	public Restore(String filepath) {
		 try {
	            ProcessBuilder processBuilder = new ProcessBuilder(
	                    "mysql",
	                    "--user=" + username,
	                    "--password=" + password,
	                    database,
	                    "-e",
	                    "source " + filepath
	            );

	            Process process = processBuilder.start();
	            int exitCode = process.waitFor();

	            if (exitCode == 0) {
	                JOptionPane.showMessageDialog(null, "Database restore completed successfully.");
	            } else {
	                JOptionPane.showMessageDialog(null, "Error restoring database.", "Error Restoring Database!", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Restoring Database!", JOptionPane.ERROR_MESSAGE);
	        }
	}
}
