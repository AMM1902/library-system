package library;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * The Backup class is responsible for backing up the library database.
 */
public class Backup {

	String username = "root";
	String password = "eclipse@polis";
	String database = "library";
	
	/**
	 * Constructs an instance of Backup class and creates a backup of the library database.
	 * 
	 * @param filepath The file path that the backup file will be created
	 */
	public Backup(String filepath) {
		try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "mysqldump",
                    "--user=" + username,
                    "--password=" + password,
                    "--databases",
                    database,
                    "--result-file=" + filepath
            );

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                JOptionPane.showMessageDialog(null, "Database backup created successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Error creating database backup.", "Error Creating Backup", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Creating Backup Database!", JOptionPane.ERROR_MESSAGE);
        }
	}
}
