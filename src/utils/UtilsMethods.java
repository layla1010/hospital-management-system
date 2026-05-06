package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;

import staffMember.UpdateStaffMember;

public class UtilsMethods {
	
	public static final int BUTTON_WIDTH= 260;
	public static final String QUICK_LINKS_TITLE = "Quick Links";
	
	public static double dateDiffInDays(Date date1, Date date2) {
		return  Math.abs( ((date1.getTime() - date2.getTime())
                / (1000 * 60 * 60 * 24)));
	}
	public static Date parseDate(String string) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static JButton createPanelButton(String buttonName) {
		JButton button = createUniformButton(buttonName);
		
		Dimension buttonSize = new Dimension(BUTTON_WIDTH, 40);
		button.setPreferredSize(buttonSize);
		button.setMaximumSize(buttonSize);
		button.setBackground(Color.decode("#D4DEE8"));
		
		return button;
	}
	
	public static JButton createUniformButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.setBackground(Color.decode("#D4DEE8"));
		button.setFont(new Font("Arial", Font.PLAIN, 14)); // Set a fixed font size

		// Set a fixed width and height for the button
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, 40));
		button.setMaximumSize(new Dimension(BUTTON_WIDTH, 40));

		return button;
	}
	
	public static JLabel getRightPanelTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		return titleLabel;
	}
}
 