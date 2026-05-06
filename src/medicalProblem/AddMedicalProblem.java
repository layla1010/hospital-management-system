package medicalProblem;
//212930952
//319098174

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import control.Hospital;
import department.AddDepartment;
import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;
import medication.UpdateMedication;
import model.Department;
import model.Disease;
import model.Fracture;
import model.Injury;
import model.Medication;

public class AddMedicalProblem extends JPanel {



	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField locationField;
	private JTextField descriptionField;
	private JTextField commonRecoveryTimeField;
	private JComboBox<Department> departmentsComboBox;
	private JComboBox<String> optionsComboBox;
	private JPanel fieldsPanel;
	private JButton saveButton;
	private ButtonGroup group;

	public AddMedicalProblem(MedicalProblems m) {
 
		this.setBackground(new Color(0xA9BED2));
		 this.setPreferredSize(new Dimension(500, 350)); 


		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel titleLabel = new JLabel("Add A Medical Problem");
		titleLabel.setFont(new Font("Traditional Arabic", Font.PLAIN, 22));
		gbc.gridwidth = 3;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(titleLabel, gbc);

		JLabel selectLabel = new JLabel("Select:");
		selectLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(selectLabel, gbc);

		optionsComboBox = new JComboBox<>(new String[] {"", "Fracture", "Injury", "Disease"});
		gbc.gridx = 1;
		gbc.gridwidth = 10;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(optionsComboBox, gbc);
		optionsComboBox.setBackground(new Color(0x698DB0));

		fieldsPanel = new JPanel();
		fieldsPanel.setBackground(new Color(0xA9BED2));
		fieldsPanel.setLayout(new GridBagLayout());
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		add(fieldsPanel, gbc);

		saveButton = new JButton("Save");
		gbc.gridy = 3;
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(saveButton, gbc);

		optionsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateFields();
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String selectedItem = (String) optionsComboBox.getSelectedItem();

					if (selectedItem == null || selectedItem.isEmpty()) {
						throw new NullPointerException("Please select a medical problem to add.");
					}

					switch (selectedItem) {
					case "Fracture":
						if (nameField.getText().isEmpty() || locationField.getText().trim().isEmpty() || group.getSelection() == null) {
							throw new NullPointerException("Field cannot be empty.");
						}
						Department department = (Department)departmentsComboBox.getSelectedItem();
		    			Fracture fracture = new Fracture(nameField.getText(),department,locationField.getText(),Boolean.parseBoolean(group.getSelection().getActionCommand()));
		    		    Hospital.getInstance().addFracture(fracture);
						break;

					case "Disease":
						if (nameField.getText().isEmpty() || descriptionField.getText().trim().isEmpty()) {
							throw new NullPointerException("Field cannot be empty.");
						}
						 department = (Department)departmentsComboBox.getSelectedItem();
		    			Disease disease = new Disease(nameField.getText(),department,descriptionField.getText());
		    			
		                Hospital.getInstance().addDisease(disease);
		    			 
						break;

					case "Injury":
						if (nameField.getText().isEmpty() || locationField.getText().trim().isEmpty() || commonRecoveryTimeField.getText().trim().isEmpty()) {
							throw new NullPointerException("Field cannot be empty.");
						}
						if(!commonRecoveryTimeField.getText().matches("\\d*\\.?\\d+")) {	
							throw new InvalidUserDetails("Common Recovery Time Can Only Contain Numbers.");
						}
						 department = (Department)departmentsComboBox.getSelectedItem();
						Injury injury = new Injury(nameField.getText(),department,Double.parseDouble(commonRecoveryTimeField.getText()),locationField.getText());
			    			
			                Hospital.getInstance().addInjury(injury);
			                
						break;
					}
					m.refreshList();
					JOptionPane.showMessageDialog(null, "Medical Problem updated successfully.");

				} catch (InvalidUserDetails ex) {
					showErrorMessage(ex.getMessage());
				} catch (FutureDateException ec) {
					JOptionPane.showMessageDialog(null, "Invalid Date Input.");
				}catch(ObjectAlreadyExistsException ex) {
        			JOptionPane.showMessageDialog(AddMedicalProblem.this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}catch(NullPointerException ec) {
					JOptionPane.showMessageDialog(null, ec.getMessage());
				}
			}
		});



		updateFields(); // Initialize fields based on default selection
	}

	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}


	private void updateFields() {
		// Clear the fields panel
		fieldsPanel.removeAll();

		String selectedOption = (String) optionsComboBox.getSelectedItem();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;

		if (!selectedOption.isEmpty()) {
			JLabel departmentLabel = new JLabel("Department:");
			departmentLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			gbc.anchor = GridBagConstraints.EAST;  // Align the label to the right
			fieldsPanel.add(departmentLabel, gbc);

			 Collection<Department> departments = Hospital.getInstance().getDepartments().values();
		        Department[] departmentArray = departments.toArray(new Department[0]);
		        departmentsComboBox = new JComboBox<>(departmentArray);
		        departmentsComboBox.setBackground(new Color(0x698DB0));

		        // Set custom renderer to display the department name
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
			gbc.gridx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.WEST;  // Align the combo box to the left
			fieldsPanel.add(departmentsComboBox, gbc);
			departmentsComboBox.setBackground(new Color(0x698DB0));

			
			gbc.gridx = 0;
			gbc.gridy++;
		}

		if ("Fracture".equals(selectedOption)) {
			fieldsPanel.add(createLabel("Name:"), gbc);
			nameField = createTextField();
			gbc.gridx = 1;
			fieldsPanel.add(nameField, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			fieldsPanel.add(createLabel("Location:"), gbc);
			locationField = createTextField();
			gbc.gridx = 1;
			fieldsPanel.add(locationField, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			fieldsPanel.add(createLabel("Requires Cast:"), gbc);
			JPanel castPanel = new JPanel();
			JRadioButton trueButton = new JRadioButton("True");
			JRadioButton falseButton = new JRadioButton("False");
			group = new ButtonGroup();  // Initialize the class-level ButtonGroup
			group.add(trueButton);
			group.add(falseButton);
			castPanel.add(trueButton);
			castPanel.add(falseButton);
			gbc.gridx = 1;
			fieldsPanel.add(castPanel, gbc);

		} else if ("Disease".equals(selectedOption)) {
			fieldsPanel.add(createLabel("Name:"), gbc);
			nameField = createTextField();
			gbc.gridx = 1;
			fieldsPanel.add(nameField, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			fieldsPanel.add(createLabel("Description:"), gbc);
			descriptionField = createTextField();
			gbc.gridx = 1;
			fieldsPanel.add(descriptionField, gbc);

		} else if ("Injury".equals(selectedOption)) {
			fieldsPanel.add(createLabel("Name:"), gbc);
			nameField = createTextField();
			gbc.gridx = 1;
			fieldsPanel.add(nameField, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			fieldsPanel.add(createLabel("Location:"), gbc);
			locationField = createTextField();
			gbc.gridx = 1;
			fieldsPanel.add(locationField, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			fieldsPanel.add(createLabel("Common Recovery Time:"), gbc);
			commonRecoveryTimeField = createTextField();
			gbc.gridx = 1;
			fieldsPanel.add(commonRecoveryTimeField, gbc);
		}

		fieldsPanel.revalidate();
		fieldsPanel.repaint();
	}

	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		label.setHorizontalAlignment(SwingConstants.RIGHT); // Align label text to the right
		return label;
	}

	private JTextField createTextField() {
		JTextField textField = new JTextField(20);
		textField.setBackground(new Color(0x698DB0)); 

		return textField;
	}
	
	
}
