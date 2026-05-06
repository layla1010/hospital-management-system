//212930952
//319098174
package panels;
import javax.swing.*;
import java.awt.*;

public class BackgroundImagePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

    public BackgroundImagePanel(ImageIcon imageIcon) {
        this.backgroundImage = imageIcon.getImage();
        setPreferredSize(new Dimension(800, 200)); // Set initial preferred size
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Scale the image to fit the panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}