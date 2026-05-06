//212930952
//319098174
package department;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import control.Hospital;
import exceptions.InvalidUserDetails;
import exceptions.ObjectDoesNotExist;
import medication.CountMedication;
import model.Department;
import model.Doctor;
import staffMember.DocsBySpec;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;

public class AppointANewManager extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox<Department> departmentsComboBox;

	/**
	 * Create the panel.
	 */

	public AppointANewManager(Departments d,Department ds) {
        this.setBackground(new Color(0xA9BED2));
        this.setPreferredSize(new Dimension(390,150));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 38, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 9;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Doctor ID:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBackground(new Color(0x698DB0));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 8;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 4;
		add(textField, gbc_textField_1);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 5;
		add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel("Department:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		
		departmentsComboBox = new JComboBox<>();
		departmentsComboBox.setBackground(new Color(0x698DB0));
		Collection<Department> departments = Hospital.getInstance().getDepartments().values();
        Department[] departmentArray = departments.toArray(new Department[0]);
		departmentsComboBox.setModel(new DefaultComboBoxModel<>(departmentArray));
		GridBagConstraints gbc_departmentsComboBox = new GridBagConstraints();
		gbc_departmentsComboBox.gridwidth = 8;
		gbc_departmentsComboBox.insets = new Insets(5, 5, 5, 5);
		gbc_departmentsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_departmentsComboBox.gridx = 3;
		gbc_departmentsComboBox.gridy = 5;
		
		 departmentsComboBox.setRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	                if (value instanceof Department) {
	                    setText(((Department) value).getName());
	                }
	                return this;
	            }
		 });
		
		        // Add departmentsComboBox to inputPanel
		        add(departmentsComboBox, gbc_departmentsComboBox);
		
		JButton btnNewButton = new JButton("Appoint");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().trim().isEmpty()) {
						throw new NullPointerException("Field Cannot Be Empty.");
					}
					if(!textField.getText().matches("\\d+")) {
						throw new InvalidUserDetails("Field Must Only Contain Numbers.");
					}
					if(!Hospital.getInstance().getStaffMembers().containsKey(Integer.parseInt(textField.getText()))) {
						throw new ObjectDoesNotExist(null, "Doctor Does Not Exist", null);
					}
					if(!(Hospital.getInstance().getStaffMember(Integer.parseInt(textField.getText())) instanceof Doctor)) {
						throw new InvalidUserDetails("Staff Member Is Not A Doctor.");
					}
					
					Hospital.getInstance().AppointANewManager((Department) departmentsComboBox.getSelectedItem());
        			JOptionPane.showMessageDialog(null, "Doctor Appointed Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        	        d.refreshList();

					
				}catch(NullPointerException ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}catch(InvalidUserDetails ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}catch(ObjectDoesNotExist ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBackground(new Color(0x698DB0));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 11;
		gbc_btnNewButton.gridy = 7;
		add(btnNewButton, gbc_btnNewButton);
        
        
	}
	

}
