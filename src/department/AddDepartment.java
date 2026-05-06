//212930952
//319098174
package department;

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
import enums.Specialization;
import exceptions.InvalidUserDetails;
import exceptions.NegativeNumberOfDosesException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectDoesNotExist;
import medicalProblem.UpdateMedicalProblem;
import model.Department;
import model.Doctor;
import visit.AddVisit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddDepartment extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField numberTextField;
	private JTextField locationTextField;
	private JComboBox<Specialization>  specializationComboBox;
	private JTextField managerIDTextField;
	private JTextField nameTextField;

	/**
	 * Create the panel.
	 */
	public AddDepartment(Departments d) {

		this.setBackground(new Color(0xA9BED2));
		 this.setPreferredSize(new Dimension(400, 280)); 


		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 10;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);

		JLabel lblNewLabel = new JLabel("Add a Department\r\n");
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.gridwidth = 12;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 10;
		gbc_panel_2.gridy = 3;
		add(panel_2, gbc_panel_2);

		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		numberTextField = new JTextField();
		numberTextField.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_numberTextField = new GridBagConstraints();
		gbc_numberTextField.gridwidth = 9;
		gbc_numberTextField.insets = new Insets(0, 0, 5, 5);
		gbc_numberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberTextField.gridx = 4;
		gbc_numberTextField.gridy = 4;
		add(numberTextField, gbc_numberTextField);
		numberTextField.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("Location:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 2;
		gbc_lblNewLabel_1_2.gridy = 5;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);

		locationTextField = new JTextField();
		locationTextField.setBackground(new Color(0x698DB0)); 
		locationTextField.setColumns(10);
		GridBagConstraints gbc_locationTextField = new GridBagConstraints();
		gbc_locationTextField.gridwidth = 9;
		gbc_locationTextField.insets = new Insets(0, 0, 5, 5);
		gbc_locationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationTextField.gridx = 4;
		gbc_locationTextField.gridy = 5;
		add(locationTextField, gbc_locationTextField);

		JLabel lblNewLabel_1_3 = new JLabel("Specialization:");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_3 = new GridBagConstraints();
		gbc_lblNewLabel_1_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_3.gridx = 2;
		gbc_lblNewLabel_1_3.gridy = 6;
		add(lblNewLabel_1_3, gbc_lblNewLabel_1_3);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Specialization specialization=(Specialization) specializationComboBox.getSelectedItem();

					if(numberTextField.getText().trim().isEmpty() ||  locationTextField.getText().trim().isEmpty()
							|| managerIDTextField.getText().trim().isEmpty()|| nameTextField.getText().trim().isEmpty()) {
						throw new NullPointerException("All Fields Must Be Filled.");
					}
					if(!numberTextField.getText().matches("\\d+")) {
						throw new InvalidUserDetails("Number Field Must Only Contain Numbers.");

					}
					if(!managerIDTextField.getText().matches("\\d+")) {
						throw new InvalidUserDetails("ID Field Must Only Contain Numbers.");
					}
					
					


					Doctor doctor = (Doctor) Hospital.getInstance().getStaffMember(Integer.parseInt(managerIDTextField.getText()));

					Department department = new Department(Integer.parseInt(numberTextField.getText())
					,nameTextField.getText(),doctor,locationTextField.getText(),specialization);

					Hospital.getInstance().addDepartment(department);
					
					if(!Hospital.getInstance().getStaffMembers().containsKey(Integer.parseInt(managerIDTextField.getText()))) {
							throw new ObjectDoesNotExist(doctor.getId(), doctor.getClass().getSimpleName(), this.getClass().getSimpleName());
					}
					
					JOptionPane.showMessageDialog(null, "Department Added Successfully!");
			        d.refreshList();

					
				}catch(InvalidUserDetails ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}catch(ObjectAlreadyExistsException ec) {
					JOptionPane.showMessageDialog(null, ec.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}catch(NullPointerException ec) {
					JOptionPane.showMessageDialog(null, ec.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}catch(ObjectDoesNotExist ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}

			}

		});

					specializationComboBox = new JComboBox<>(); 
					specializationComboBox.setBackground(new Color(0x698DB0)); 
					specializationComboBox.setModel(new DefaultComboBoxModel<>(Specialization.values()));		
					GridBagConstraints gbc_comboBox = new GridBagConstraints();
					gbc_comboBox.gridwidth = 9;
					gbc_comboBox.insets = new Insets(0, 0, 5, 5);
					gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBox.gridx = 4;
					gbc_comboBox.gridy = 6;
					add(specializationComboBox, gbc_comboBox);

					JLabel lblNewLabel_1_2_1 = new JLabel("Manager ID:");
					lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
					GridBagConstraints gbc_lblNewLabel_1_2_1 = new GridBagConstraints();
					gbc_lblNewLabel_1_2_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_1_2_1.gridx = 2;
					gbc_lblNewLabel_1_2_1.gridy = 7;
					add(lblNewLabel_1_2_1, gbc_lblNewLabel_1_2_1);

					managerIDTextField = new JTextField();
					managerIDTextField.setColumns(10);
					managerIDTextField.setBackground(new Color(105, 141, 176));
					GridBagConstraints gbc_managerIDTextField = new GridBagConstraints();
					gbc_managerIDTextField.gridwidth = 9;
					gbc_managerIDTextField.insets = new Insets(0, 0, 5, 5);
					gbc_managerIDTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_managerIDTextField.gridx = 4;
					gbc_managerIDTextField.gridy = 7;
					add(managerIDTextField, gbc_managerIDTextField);

					JLabel nameLabel = new JLabel("Department Name:");
					nameLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
					GridBagConstraints gbc_nameLabel = new GridBagConstraints();
					gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
					gbc_nameLabel.gridx = 2;
					gbc_nameLabel.gridy = 8;
					add(nameLabel, gbc_nameLabel);

					nameTextField = new JTextField();
					nameTextField.setColumns(10);
					nameTextField.setBackground(new Color(105, 141, 176));
					GridBagConstraints gbc_nameTextField = new GridBagConstraints();
					gbc_nameTextField.gridwidth = 9;
					gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
					gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_nameTextField.gridx = 4;
					gbc_nameTextField.gridy = 8;
					add(nameTextField, gbc_nameTextField);
					btnNewButton.setBackground(new Color(0x698DB0));
					btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
					gbc_btnNewButton.gridx = 13;
					gbc_btnNewButton.gridy = 9;
					add(btnNewButton, gbc_btnNewButton);



	}
	
}
