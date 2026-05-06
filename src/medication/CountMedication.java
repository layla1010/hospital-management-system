//212930952
//319098174
package medication;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.Hospital;
import exceptions.InvalidUserDetails;
import exceptions.NegativeDosageException;
import visit.HowManyVisitBefore;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;

public class CountMedication extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public CountMedication(Medications m) {
		
        this.setBackground(new Color(0xA9BED2));
        this.setPreferredSize(new Dimension(390,150));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 62, 62, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 10;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		
		JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 5;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		add(panel_3, gbc_panel_3);
		
		JLabel lblNewLabel = new JLabel("Minimum Dosage:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(new Color(0x698DB0));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 6;
		gbc_textField.gridy = 3;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblMax = new JLabel("Maximum Dosage:");
		lblMax.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.gridwidth = 4;
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 2;
		gbc_lblMax.gridy = 5;
		add(lblMax, gbc_lblMax);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(0x698DB0));
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 7;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 5;
		add(textField_1, gbc_textField_1);
		
		JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 7;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 13;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().trim().isEmpty() || textField_1.getText().trim().isEmpty()) {
						throw new NullPointerException("All Fields Must Be Filled.");
					}
					if(!textField.getText().matches("\\d*\\.?\\d+")||!textField_1.getText().matches("\\d*\\.?\\d+")){
						throw new InvalidUserDetails ("Fields Must Only Contain Numbers");
					}
					if(Double.parseDouble(textField.getText())<0||Double.parseDouble(textField_1.getText())<0) {
						throw new NegativeDosageException(Double.parseDouble(textField.getText()));
					}
					
        			JOptionPane.showMessageDialog(null, "Number Of Medication Between " + Double.parseDouble(textField.getText()) + " and " 
        					+ Double.parseDouble(textField_1.getText()) + " is: " + 
        					Hospital.getInstance().countMedications(Double.parseDouble(textField.getText()), Double.parseDouble(textField_1.getText())));

					
				}catch(NullPointerException ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}catch(InvalidUserDetails ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}catch(NegativeDosageException ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBackground(new Color(0x698DB0));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 12;
		gbc_btnNewButton.gridy = 8;
		add(btnNewButton, gbc_btnNewButton);
		
		
		JPanel panel = new JPanel();
        panel.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 14;
		gbc_panel.gridy = 9;
		add(panel, gbc_panel);

	}
	

}
