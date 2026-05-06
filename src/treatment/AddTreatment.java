package treatment;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
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
import department.AddDepartment;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;
import model.Treatment;
import visit.AddVisit;
import visit.UpdateVisit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTreatment extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField serialNumber;
	private JTextField description;

	/**
	 * Create the panel.
	 */
	public AddTreatment(Treatments t) {

		this.setBackground(new Color(0xA9BED2));
		 this.setPreferredSize(new Dimension(420, 220)); 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Add a Treatment");
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.gridwidth = 12;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 11;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);

		JLabel lblNewLabel_1 = new JLabel("Serial Number:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 6;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		serialNumber = new JTextField();
		serialNumber.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_serialNumber = new GridBagConstraints();
		gbc_serialNumber.gridwidth = 6;
		gbc_serialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_serialNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_serialNumber.gridx = 6;
		gbc_serialNumber.gridy = 6;
		add(serialNumber, gbc_serialNumber);
		serialNumber.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Description:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 8;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		description = new JTextField();
		description.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_description = new GridBagConstraints();
		gbc_description.gridwidth = 6;
		gbc_description.insets = new Insets(0, 0, 5, 5);
		gbc_description.fill = GridBagConstraints.HORIZONTAL;
		gbc_description.gridx = 6;
		gbc_description.gridy = 8;
		add(description, gbc_description);
		description.setColumns(10);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if(serialNumber.getText().trim().isEmpty() || description.getText().trim().isEmpty()) {
						throw new NullPointerException("All Fields Must Be Filled.");
					}
					if(!serialNumber.getText().matches("\\d+")) {
						throw new InvalidUserDetails("Serial Number Field Must Only Contain Numbers.");
					}
					Treatment treatment = new Treatment(Integer.parseInt(serialNumber.getText()),description.getText());

					Hospital.getInstance().addTreatment(treatment);
					t.refreshList();
					JOptionPane.showMessageDialog(null, "Treatment Added Successfully!");


				}catch(InvalidUserDetails ec) {
					JOptionPane.showMessageDialog(null, ec.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}catch(ObjectAlreadyExistsException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}catch(NullPointerException es) {
					JOptionPane.showMessageDialog(null, es.getMessage());
				}
			}
		});
		btnNewButton.setBackground(new Color(0x698DB0));
		btnNewButton.setFont(new Font("Traditional Arabic", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 13;
		gbc_btnNewButton.gridy = 11;
		add(btnNewButton, gbc_btnNewButton);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 14;
		gbc_panel.gridy = 12;
		add(panel, gbc_panel);

	}
	
	

}
