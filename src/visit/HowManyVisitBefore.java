package visit;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import control.Hospital;
import exceptions.FutureDateException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class HowManyVisitBefore extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final Date MAX_DATE;

	static {
		try {
			MAX_DATE = DATE_FORMAT.parse("30/04/2024");
		} catch (ParseException e) {
			throw new RuntimeException("Date parsing error", e);
		}
	}


    public HowManyVisitBefore(Visits visits) {
		 this.setPreferredSize(new Dimension(300, 150)); 
		 this.setBackground(new Color(0xA9BED2));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("Date:");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 4;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 6;
        add(lblNewLabel, gbc_lblNewLabel);
        
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBackground(new Color(0x698DB0));
        dateChooser.setPreferredSize(new Dimension(200, 30)); // Set desired size

        GridBagConstraints gbc_DateChooser = new GridBagConstraints();
        gbc_DateChooser.gridwidth = 12;
        gbc_DateChooser.gridheight = 1;
        gbc_DateChooser.insets = new Insets(0, 0, 5, 5);
        gbc_DateChooser.fill = GridBagConstraints.BOTH;
        gbc_DateChooser.gridx = 6;
        gbc_DateChooser.gridy = 6;
        add(dateChooser, gbc_DateChooser);
        
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setBackground(new Color(0x698DB0));
        dateTextField.setForeground(Color.WHITE);

        JButton btnNewButton = new JButton("Calculate");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(dateChooser.getDate().after(MAX_DATE)) {
        				throw new FutureDateException(MAX_DATE);
        			}
        			if(dateChooser.getDate() == null) {
        				throw new NullPointerException("Field Cannot Be Empty.");
        			}
        			JOptionPane.showMessageDialog(null , "Number Of Visits Before" + dateChooser.getDate() + "is: " + Hospital.getInstance().howManyVisitBefore(dateChooser.getDate()));
        		}catch(FutureDateException ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        		}catch(NullPointerException ec) {
        			JOptionPane.showMessageDialog(null, ec.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.gridwidth = 2;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 16;
        gbc_btnNewButton.gridy = 9;
        add(btnNewButton, gbc_btnNewButton);
    }

   
}
