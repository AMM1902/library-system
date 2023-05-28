package library;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A utility class for resizing images.
 */
public class Resize {

	/**
     * Resizes the given ImageIcon to a default width and height of 50.
     *
     * @param imgIcon the ImageIcon to resize
     * @return the resized ImageIcon
     */
	public static ImageIcon resize(ImageIcon imgIcon) {
		Image img = imgIcon.getImage();
		int width = 50;
		int height = 50;
		ImageIcon resizedIcon = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		return resizedIcon;
	}
	
	/**
     * Resizes the given ImageIcon to the specified width and height.
     *
     * @param imgIcon the ImageIcon to resize
     * @param width   the desired width of the resized image
     * @param height  the desired height of the resized image
     * @return the resized ImageIcon
     */
	public static ImageIcon resize(ImageIcon imgIcon, int w, int h) {
		Image img = imgIcon.getImage();
		int width = w;
		int height = h;
		ImageIcon resizedIcon = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		return resizedIcon;
	}
}
