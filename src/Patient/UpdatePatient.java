package Patient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import control.Hospital;
import enums.BiologicalSex;
import enums.HealthFund;
import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectDoesNotExist;
import model.Patient;
import staffMember.UpdateStaffMember;

public class UpdatePatient extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel inputPanel;
	private JComboBox<String> attributeComboBox;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField adressField;
	private JTextField numberField;
	private JTextField emailField;
	private JRadioButton maleRadioButton;
	private JRadioButton femaleRadioButton;
	private ButtonGroup genderGroup;
	private JDateChooser birthDateChooser;
	private JRadioButton otherRadioButton;
	private JComboBox<HealthFund> healthFundComboBox;
	private BiologicalSex[] selectedSex;
	private Patients patients;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Date maxAllowedDate;
	private Patient patient;


	static {
		try {
			maxAllowedDate = sdf.parse("30/04/2024");
		} catch (ParseException e) {
			e.printStackTrace();
			maxAllowedDate = new Date(); // Fallback to current date if parsing fails
		}
	}

	public UpdatePatient(Patients patients,Patient patient) {
		this.patients=patients;
		this.patient=patient;

		this.setBackground(new Color(0x698DB0));
		this.setPreferredSize(new Dimension(420, 220)); 
		setLayout(new BorderLayout());

		// Initialize the radio buttons first
		maleRadioButton = new JRadioButton("Male");
		femaleRadioButton = new JRadioButton("Female");
		otherRadioButton = new JRadioButton("Other");

		// Group the radio buttons
		genderGroup = new ButtonGroup();
		genderGroup.add(maleRadioButton);
		genderGroup.add(femaleRadioButton);
		genderGroup.add(otherRadioButton);

		// Now you can set up the action listeners
		selectedSex = new BiologicalSex[1];
		maleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.M);
		femaleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.F);
		maleRadioButton.setSelected(true);
		selectedSex[0] = BiologicalSex.M;

		JLabel titleLabel = new JLabel("What Do You Want To Update?");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(0xA9BED2));
		northPanel.setLayout(new BorderLayout());
		northPanel.add(titleLabel, BorderLayout.NORTH);

		String[] options = { "First Name", "Last Name", "Address", "Phone Number", "Gender", "Biological Sex", "Email", "HealthFund", "Birthdate"};
		attributeComboBox = new JComboBox<>(options);
		attributeComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "First Name", "Last Name", "Address", "Phone Number", "Gender", "Biological Sex", "Email", "HealthFund", "Birthdate"}));

		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setBackground(new Color(0xA9BED2));
		comboBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel selectLabel = new JLabel("Select:");
		comboBoxPanel.add(selectLabel);
		comboBoxPanel.add(attributeComboBox);

		northPanel.add(comboBoxPanel, BorderLayout.CENTER);
		inputPanel = new JPanel();
		inputPanel.setBackground(new Color(0xA9BED2));
		inputPanel.setLayout(new GridBagLayout());

		birthDateChooser = new JDateChooser();
		birthDateChooser.getCalendarButton().setPreferredSize(new Dimension(12, 12));
		birthDateChooser.setPreferredSize(new Dimension(82, birthDateChooser.getPreferredSize().height));

		add(northPanel, BorderLayout.NORTH);
		add(inputPanel, BorderLayout.CENTER);

		attributeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateInputPanel();
			}
		});
		
		firstNameField = new JTextField();
		lastNameField = new JTextField();
		adressField = new JTextField();
		numberField = new JTextField();
		emailField = new JTextField();

		
		JComponent[] components = {
				birthDateChooser, firstNameField, lastNameField,
				adressField, numberField, emailField
		};

		for (JComponent component : components) {
			component.setBackground(new Color(0x698DB0));
			component.setForeground(Color.WHITE);
		}

		healthFundComboBox = new JComboBox<>();
		healthFundComboBox.setModel(new DefaultComboBoxModel<>(HealthFund.values()));

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String selectedItem = (String) attributeComboBox.getSelectedItem();

					if(selectedItem == null || selectedItem.isEmpty()) {
						throw new NullPointerException("choose an attribute to update.");
					}
					if (selectedItem == "Phone Number") {
						if(numberField.getText().trim().isEmpty()) {

						}

						if(!numberField.getText().matches("\\d+")) {
							throw new InvalidUserDetails("Feild Must Only Contain Numbers");
						}					
						Hospital.getInstance().getRealPatient(patient.getId()).setPhoneNumber(numberField.getText());

					}
					if(selectedItem == "First Name") {
						if(firstNameField.getText().trim().isEmpty()) {
							throw new InvalidUserDetails ("Feild Cannot Be Empty.");
						}	
						Hospital.getInstance().getRealPatient(patient.getId()).setFirstName(firstNameField.getText());

					}
					if(selectedItem == "Last Name") {
						if(lastNameField.getText().trim().isEmpty() ) {
							throw new NullPointerException ("Feild Cannot Be Empty.");


						}Hospital.getInstance().getRealPatient(patient.getId()).setFirstName(lastNameField.getText());
					}
					if(selectedItem == "Address") {
						if(adressField.getText().trim().isEmpty()) {
							throw new NullPointerException ("Feild Cannot Be Empty.");

						}Hospital.getInstance().getRealPatient(patient.getId()).setFirstName(adressField.getText());

					}
					if(selectedItem == "Email") {
						if(emailField.getText().trim().isEmpty()) {
							throw new NullPointerException ("Feild Cannot Be Empty.");


						}
						Hospital.getInstance().getRealPatient(patient.getId()).setFirstName(emailField.getText());
					}
					if(selectedItem == "Gender") {
						if(genderGroup.getSelection() == null ) {
							throw new NullPointerException("Field Cannot Be Empty");

						}					
						Hospital.getInstance().getRealPatient(patient.getId()).setGender(genderGroup.getSelection().getActionCommand());

					}
					if(selectedItem == "Biological Sex") {
						if( selectedSex == null) {
							throw new NullPointerException("Field Cannot Be Empty");

						}
						Hospital.getInstance().getRealPatient(patient.getId()).setBiologicalSex(selectedSex[0]);

					}
					if(selectedItem == "Health Fund") {
						if(healthFundComboBox.getSelectedItem() == null){
							throw new NullPointerException("Field Cannot Be Empty");

						}	
						Hospital.getInstance().getRealPatient(patient.getId()).setHealthFund((HealthFund)healthFundComboBox.getSelectedItem());

					}
					if(selectedItem == "Birthdate") {
						if(birthDateChooser.getDate() == null) {
							throw new NullPointerException("Field Cannot Be Empty");
						}
						if(birthDateChooser.getDate().after(maxAllowedDate)) {
							throw new FutureDateException(maxAllowedDate);
						}
						Hospital.getInstance().getRealPatient(patient.getId()).setBirthDate(birthDateChooser.getDate());

					}
					patients.refreshList();
					JOptionPane.showMessageDialog(null, "Patient updated successfully.");

				}

				catch(InvalidUserDetails ex){
					showErrorMessage(ex.getMessage());
				}catch(FutureDateException ex) {
					showErrorMessage(ex.getMessage());

				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}catch(ObjectDoesNotExist ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.setBackground(new Color(0xA9BED2));
		southPanel.add(updateButton);

		add(southPanel, BorderLayout.SOUTH);
	}

	private void updateInputPanel() {
		inputPanel.removeAll();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		String selectedOption = (String) attributeComboBox.getSelectedItem();
		switch (selectedOption) {
		case "First Name":
			addLabelAndTextField("First Name:", firstNameField, gbc, 1);
			break;
		case "Last Name":
			addLabelAndTextField("Last Name:", lastNameField, gbc, 2);
			break;
		case "Address":
			addLabelAndTextField("Address:", adressField, gbc, 3);
			break;
		case "Phone Number":
			addLabelAndTextField("Phone Number:", numberField, gbc, 4);
			break;
		case "Email":
			addLabelAndTextField("Email:", emailField, gbc, 5);
			break;
		case "Gender":
			addGenderOptions(gbc, 6);
			break;
		case "Biological Sex":

			addBiologicalSexOptions(gbc, 7);
			break;
		case "HealthFund":
			addHealthFundOption(gbc, 8);
			break;
		case "Birthdate":
			addLabelAndDateChooser("Birthdate:", birthDateChooser, gbc, 9);
			break;
		}

		inputPanel.revalidate();
		inputPanel.repaint();
	}


	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}


	
	
	private void addLabelAndTextField(String labelText, JComponent textField, GridBagConstraints gbc, int row) {
	    // Adding the label
	    gbc.gridx = 0;
	    gbc.gridy = row;
	    gbc.gridwidth = 1; // Label takes 1 column
	    gbc.fill = GridBagConstraints.NONE; // Label does not stretch
	    gbc.weightx = 0; // Label does not take extra space
	    gbc.anchor = GridBagConstraints.WEST; // Align label to the left
	    JLabel label = new JLabel(labelText);
	    inputPanel.add(label, gbc);

	    // Set a preferred size for the text field
	    textField.setPreferredSize(new Dimension(200, 30)); // Adjust the width and height as needed

	    // Adding the text field
	    gbc.gridx = 1;
	    gbc.gridy = row;
	    gbc.gridwidth = 1; // Text field takes 1 column
	    gbc.fill = GridBagConstraints.NONE; // Text field does not stretch
	    gbc.weightx = 0; // Text field does not take extra space
	    gbc.anchor = GridBagConstraints.WEST; // Align text field to the left
	    inputPanel.add(textField, gbc);
	}




	private void addGenderOptions(GridBagConstraints gbc, int row) {
		gbc.gridx = 0;
		gbc.gridy = row;
		JLabel label = new JLabel("Gender:");
		inputPanel.add(label, gbc);

		gbc.gridx = 1;
		gbc.gridy = row;
		inputPanel.add(maleRadioButton, gbc);

		gbc.gridx = 2;
		gbc.gridy = row;
		inputPanel.add(femaleRadioButton, gbc);

		gbc.gridx = 3;
		gbc.gridy = row;
		inputPanel.add(otherRadioButton, gbc);
	}

	private void addBiologicalSexOptions(GridBagConstraints gbc, int row) {
		// Initialize the array to hold the selected biological sex
		BiologicalSex[] selectedSex = new BiologicalSex[1];

		// Set up the label for "Biological Sex"
		gbc.gridx = 0;
		gbc.gridy = row;
		JLabel label = new JLabel("Biological Sex:");
		inputPanel.add(label, gbc);

		// Set up the male radio button
		gbc.gridx = 1;
		gbc.gridy = row;
		inputPanel.add(maleRadioButton, gbc);

		// Set up the female radio button
		gbc.gridx = 2;
		gbc.gridy = row;
		inputPanel.add(femaleRadioButton, gbc);

		// Add action listeners to update selectedSex based on the selected radio button
		maleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.M);
		femaleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.F);

		// Default selection
		maleRadioButton.setSelected(true);  
		selectedSex[0] = BiologicalSex.M;
	}



	private void addHealthFundOption(GridBagConstraints gbc, int row) {
		gbc.gridx = 0;
		gbc.gridy = row;
		JLabel label = new JLabel("Health Fund:");
		inputPanel.add(label, gbc);

		gbc.gridx = 1;
		inputPanel.add(healthFundComboBox, gbc);
	}

	private void addLabelAndDateChooser(String labelText, JDateChooser dateChooser, GridBagConstraints gbc, int row) {
		gbc.gridx = 0;
		gbc.gridy = row;
		JLabel label = new JLabel(labelText);
		inputPanel.add(label, gbc);

		gbc.gridx = 1;
		inputPanel.add(dateChooser, gbc);
	}

	 
	    

}

