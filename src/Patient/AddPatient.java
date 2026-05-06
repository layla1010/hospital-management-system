
package Patient;

import enums.*;
import control.*;
import exceptions.*;
import model.*;
import treatment.UpdateTreatment;
import utils.*;
import view.UserPage;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddPatient extends JPanel {

    private static final long serialVersionUID = 1L;
    private Patients patients;
    private JTextField idTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField addressTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private static final Date sdf = new Date("30/04/2024");


    /**
     * Create the panel.
     */
    public AddPatient(Patients patients) {
		 this.setPreferredSize(new Dimension(750, 350)); 


    	this.patients=patients;
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{84, 52, 111, 0, 88, 29, 101, 0, 0};
        gridBagLayout.rowHeights = new int[]{30, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        
        this.setBackground(new Color(0xA9BED2));

        
        JLabel addPatientLabel = new JLabel("Add Patient");
        addPatientLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        GridBagConstraints gbc_addPatientLabel = new GridBagConstraints();
        gbc_addPatientLabel.gridheight = 2;
        gbc_addPatientLabel.gridwidth = 5;
        gbc_addPatientLabel.insets = new Insets(0, 0, 5, 5);
        gbc_addPatientLabel.gridx = 1;
        gbc_addPatientLabel.gridy = 0;
        add(addPatientLabel, gbc_addPatientLabel);
        
        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_idLabel = new GridBagConstraints();
        gbc_idLabel.anchor = GridBagConstraints.EAST;
        gbc_idLabel.insets = new Insets(0, 0, 5, 5);
        gbc_idLabel.gridx = 0;
        gbc_idLabel.gridy = 2;
        add(idLabel, gbc_idLabel);
        
        idTextField = new JTextField();
        idTextField.setBackground(new Color(0x698DB0)); // Set the background color of the text field
        idTextField.setForeground(Color.WHITE); // Set the text color to white
        GridBagConstraints gbc_idTextField = new GridBagConstraints();
        gbc_idTextField.gridwidth = 4;
        gbc_idTextField.insets = new Insets(0, 0, 5, 5);
        gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_idTextField.gridx = 1;
        gbc_idTextField.gridy = 2;
        add(idTextField, gbc_idTextField);
        idTextField.setColumns(10);
        
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
        gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
        gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_firstNameLabel.gridx = 0;
        gbc_firstNameLabel.gridy = 3;
        add(firstNameLabel, gbc_firstNameLabel);
        
        firstNameTextField = new JTextField();
        firstNameTextField.setBackground(new Color(0x698DB0)); // Set the background color of the text field
        firstNameTextField.setForeground(Color.WHITE); // Set the text color to white
        firstNameTextField.setColumns(10);
        GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
        gbc_firstNameTextField.gridwidth = 3;
        gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
        gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_firstNameTextField.gridx = 1;
        gbc_firstNameTextField.gridy = 3;
        add(firstNameTextField, gbc_firstNameTextField);
        
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
        gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
        gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lastNameLabel.gridx = 4;
        gbc_lastNameLabel.gridy = 3;
        add(lastNameLabel, gbc_lastNameLabel);
        
        lastNameTextField = new JTextField();
        lastNameTextField.setBackground(new Color(0x698DB0)); // Set the background color of the text field
        lastNameTextField.setForeground(Color.WHITE); // Set the text color to white
        lastNameTextField.setColumns(10);
        GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
        gbc_lastNameTextField.gridwidth = 3;
        gbc_lastNameTextField.insets = new Insets(0, 0, 5, 0);
        gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_lastNameTextField.gridx = 5;
        gbc_lastNameTextField.gridy = 3;
        add(lastNameTextField, gbc_lastNameTextField);
        
        JLabel birthDateLabel = new JLabel("BirthDate:");
        birthDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_birthDateLabel = new GridBagConstraints();
        gbc_birthDateLabel.anchor = GridBagConstraints.EAST;
        gbc_birthDateLabel.insets = new Insets(0, 0, 5, 5);
        gbc_birthDateLabel.gridx = 0;
        gbc_birthDateLabel.gridy = 4;
        add(birthDateLabel, gbc_birthDateLabel);
        
        JDateChooser birthDateChooser = new JDateChooser();
        birthDateChooser.setBackground(new Color(0x698DB0)); // Set the background color of the text field
        birthDateChooser.setForeground(Color.WHITE); // Set the text color to white
        birthDateChooser.getCalendarButton().setPreferredSize(new Dimension(12, 12));
        GridBagConstraints gbc_birthDateChooser = new GridBagConstraints();
        gbc_birthDateChooser.gridwidth = 3;
        gbc_birthDateChooser.insets = new Insets(0, 0, 5, 5);
        gbc_birthDateChooser.fill = GridBagConstraints.BOTH;
        gbc_birthDateChooser.gridx = 1;
        gbc_birthDateChooser.gridy = 4;
        add(birthDateChooser, gbc_birthDateChooser);
        Date selectedDate = birthDateChooser.getDate();
        JTextField dateTextField = (JTextField) birthDateChooser.getDateEditor().getUiComponent();
        dateTextField.setBackground(new Color(0x698DB0)); // Match the background color of other text fields
        dateTextField.setForeground(Color.WHITE); // Set the text color to white
       
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_addressLabel = new GridBagConstraints();
        gbc_addressLabel.anchor = GridBagConstraints.EAST;
        gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
        gbc_addressLabel.gridx = 0;
        gbc_addressLabel.gridy = 5;
        add(addressLabel, gbc_addressLabel);
        
        addressTextField = new JTextField();
        addressTextField.setBackground(new Color(0x698DB0)); // Set the background color of the text field
        addressTextField.setForeground(Color.WHITE); // Set the text color to white
        addressTextField.setColumns(10);
        GridBagConstraints gbc_addressTextField = new GridBagConstraints();
        gbc_addressTextField.gridwidth = 4;
        gbc_addressTextField.insets = new Insets(0, 0, 5, 5);
        gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_addressTextField.gridx = 1;
        gbc_addressTextField.gridy = 5;
        add(addressTextField, gbc_addressTextField);
        
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
        gbc_phoneNumberLabel.anchor = GridBagConstraints.EAST;
        gbc_phoneNumberLabel.insets = new Insets(0, 0, 5, 5);
        gbc_phoneNumberLabel.gridx = 0;
        gbc_phoneNumberLabel.gridy = 6;
        add(phoneNumberLabel, gbc_phoneNumberLabel);
        
        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBackground(new Color(0x698DB0)); // Set the background color of the text field
        phoneNumberTextField.setForeground(Color.WHITE); // Set the text color to white
        phoneNumberTextField.setColumns(10);
        GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
        gbc_phoneNumberTextField.gridwidth = 4;
        gbc_phoneNumberTextField.insets = new Insets(0, 0, 5, 5);
        gbc_phoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_phoneNumberTextField.gridx = 1;
        gbc_phoneNumberTextField.gridy = 6;
        add(phoneNumberTextField, gbc_phoneNumberTextField);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_emailLabel = new GridBagConstraints();
        gbc_emailLabel.anchor = GridBagConstraints.EAST;
        gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
        gbc_emailLabel.gridx = 0;
        gbc_emailLabel.gridy = 7;
        add(emailLabel, gbc_emailLabel);
        
        emailTextField = new JTextField();
        emailTextField.setBackground(new Color(0x698DB0)); // Set the background color of the text field
        emailTextField.setForeground(Color.WHITE); // Set the text color to white
        emailTextField.setColumns(10);
        GridBagConstraints gbc_emailTextField = new GridBagConstraints();
        gbc_emailTextField.gridwidth = 4;
        gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
        gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
       

 gbc_emailTextField.gridx = 1;
        gbc_emailTextField.gridy = 7;
        add(emailTextField, gbc_emailTextField);
        
        JLabel genderLabel = new JLabel("Biological Sex:");
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_genderLabel = new GridBagConstraints();
        gbc_genderLabel.anchor = GridBagConstraints.EAST;
        gbc_genderLabel.insets = new Insets(0, 0, 5, 5);
        gbc_genderLabel.gridx = 0;
        gbc_genderLabel.gridy = 8;
        add(genderLabel, gbc_genderLabel);
        
        JRadioButton maleRadioButton = new JRadioButton("Male");
        GridBagConstraints gbc_maleRadioButton = new GridBagConstraints();
        gbc_maleRadioButton.insets = new Insets(0, 0, 5, 5);
        gbc_maleRadioButton.gridx = 2;
        gbc_maleRadioButton.gridy = 8;
        add(maleRadioButton, gbc_maleRadioButton);
        ButtonGroup biologicalSexGroup = new ButtonGroup();
        biologicalSexGroup .add(maleRadioButton);
        
        BiologicalSex[] selectedSex = new BiologicalSex[1];
        maleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.M);
        maleRadioButton.setSelected(true);  
        selectedSex[0] = BiologicalSex.M;
                
                JRadioButton femaleRadioButton = new JRadioButton("Female");
                GridBagConstraints gbc_femaleRadioButton = new GridBagConstraints();
                gbc_femaleRadioButton.insets = new Insets(0, 0, 5, 5);
                gbc_femaleRadioButton.gridx = 3;
                gbc_femaleRadioButton.gridy = 8;
                add(femaleRadioButton, gbc_femaleRadioButton);
                biologicalSexGroup .add(femaleRadioButton);
                femaleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.F);
        
       
                
                JLabel genderLabel_1 = new JLabel("Gender:");
                genderLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                GridBagConstraints gbc_genderLabel_1 = new GridBagConstraints();
                gbc_genderLabel_1.anchor = GridBagConstraints.EAST;
                gbc_genderLabel_1.insets = new Insets(0, 0, 5, 5);
                gbc_genderLabel_1.gridx = 0;
                gbc_genderLabel_1.gridy = 9;
                add(genderLabel_1, gbc_genderLabel_1);
                
                JRadioButton maleRadioButton2 = new JRadioButton("Male");
                maleRadioButton2.setSelected(true);
                GridBagConstraints gbc_maleRadioButton2 = new GridBagConstraints();
                gbc_maleRadioButton2.insets = new Insets(0, 0, 5, 5);
                gbc_maleRadioButton2.gridx = 2;
                gbc_maleRadioButton2.gridy = 9;
                add(maleRadioButton2, gbc_maleRadioButton2);
                ButtonGroup genderGroup = new ButtonGroup();
                genderGroup.add(maleRadioButton2);
                
                String[] selectedGender = new String[1];
                maleRadioButton2.addActionListener(e -> selectedGender[0] = "Male");
                
                maleRadioButton.setSelected(true);  
                selectedGender[0] = "Male";
                
                JRadioButton femaleRadioButton2 = new JRadioButton("Female");
                GridBagConstraints gbc_femaleRadioButton2 = new GridBagConstraints();
                gbc_femaleRadioButton2.fill = GridBagConstraints.VERTICAL;
                gbc_femaleRadioButton2.insets = new Insets(0, 0, 5, 5);
                gbc_femaleRadioButton2.gridx = 3;
                gbc_femaleRadioButton2.gridy = 9;
                add(femaleRadioButton2, gbc_femaleRadioButton2);
                genderGroup.add(femaleRadioButton2);
                femaleRadioButton2.addActionListener(e -> selectedGender[0] = "Female");
                
                JRadioButton otherRadioButton = new JRadioButton("Other");
                otherRadioButton.setSelected(true);
                GridBagConstraints gbc_otherRadioButton = new GridBagConstraints();
                gbc_otherRadioButton.insets = new Insets(0, 0, 5, 5);
                gbc_otherRadioButton.gridx = 4;
                gbc_otherRadioButton.gridy = 9;
                add(otherRadioButton, gbc_otherRadioButton);
                genderGroup.add(otherRadioButton);
                otherRadioButton.addActionListener(e -> selectedGender[0] = "Other");
        
               
                
                
                
                
                JLabel healthFundLabel = new JLabel("HealthFund:");
                healthFundLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
                GridBagConstraints gbc_healthFundLabel = new GridBagConstraints();
                gbc_healthFundLabel.anchor = GridBagConstraints.EAST;
                gbc_healthFundLabel.insets = new Insets(0, 0, 5, 5);
                gbc_healthFundLabel.gridx = 0;
                gbc_healthFundLabel.gridy = 10;
                add(healthFundLabel, gbc_healthFundLabel);
        
        JComboBox<HealthFund> healthFundComboBox = new JComboBox<>();
        healthFundComboBox.setModel(new DefaultComboBoxModel<>(HealthFund.values()));
        GridBagConstraints gbc_healthFundComboBox = new GridBagConstraints();
        gbc_healthFundComboBox.gridwidth = 3;
        gbc_healthFundComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_healthFundComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_healthFundComboBox.gridx = 2;
        gbc_healthFundComboBox.gridy = 10;
        add(healthFundComboBox, gbc_healthFundComboBox);
                        
                        JButton saveButton = new JButton("Save");
                        saveButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    // Validation
                                    if (idTextField.getText().trim().isEmpty() ||
                                        firstNameTextField.getText().trim().isEmpty() ||
                                        lastNameTextField.getText().trim().isEmpty() ||
                                        addressTextField.getText().trim().isEmpty() ||
                                        phoneNumberTextField.getText().trim().isEmpty() ||
                                        emailTextField.getText().trim().isEmpty() ||
                                        birthDateChooser.getDate() == null ||
                                        selectedGender[0] == null ||
                                        selectedSex[0] == null ||
                                        healthFundComboBox.getSelectedItem() == null) {
                                        
                                        throw new NullPointerException("All fields must be filled.");
                                    }

                                    // Validate ID is a number
                                    String idText = idTextField.getText().trim();
                                    if (!idText.matches("\\d+")) {
                                        throw new InvalidUserDetails("ID Must Be Only Numbers!");
                                    }
                                    
                                    // Validate phone number is a number
                                    String phoneNumberText = phoneNumberTextField.getText().trim();
                                    if (!phoneNumberText.matches("\\d+")) {
                                        throw new InvalidUserDetails("Phone Number Must Be Only Numbers");
                                    }

                                    
                                    if (birthDateChooser.getDate().after(sdf)) {
                                        throw new FutureDateException(sdf);
                                    }
                                    
                                    

                                    // Parse ID
                                    int id = Integer.parseInt(idText);
                                    String firstName = firstNameTextField.getText();
                                    String lastName = lastNameTextField.getText();
                                    Date selectedDate = birthDateChooser.getDate();
                                    String address = addressTextField.getText();
                                    String phoneNumber = phoneNumberTextField.getText();
                                    String email = emailTextField.getText();
                                    HealthFund healthFund = (HealthFund) healthFundComboBox.getSelectedItem();

                                    // Create and add patient
                                    Patient patient = new Patient(id, firstName, lastName, selectedDate, address, phoneNumber, email, selectedGender[0], healthFund, selectedSex[0]);
                                    Hospital.getInstance().addPatient(patient);
                                    patients.refreshList();
                                    if(Hospital.getInstance().getRealPatient(patient.getId())!=null) {
                                    	 JOptionPane.showMessageDialog(null, "Patient added successfully:\n" );
                                    }
                                    Window window = SwingUtilities.getWindowAncestor(AddPatient.this);
                                    if (window != null) {
                                        window.dispose();
                                    }
                                } catch (NullPointerException ex) {
                                    JOptionPane.showMessageDialog(null, ex.getMessage());
                                } catch (InvalidUserDetails ex) {
                                    JOptionPane.showMessageDialog(null, ex.getMessage());
			                 	}catch (FutureDateException ec) {
                                    JOptionPane.showMessageDialog(null, "Invalid Date Input!");
				                }catch(ObjectAlreadyExistsException ex) {
				        			JOptionPane.showMessageDialog(AddPatient.this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				                }
				                }
                                
                            
                        });
    
                        
                        
                                GridBagConstraints gbc_SaveButton = new GridBagConstraints();
                                gbc_SaveButton.gridwidth = 3;
                                gbc_SaveButton.fill = GridBagConstraints.HORIZONTAL;
                                gbc_SaveButton.insets = new Insets(0, 0, 5, 5);
                                gbc_SaveButton.gridx = 2;
                                gbc_SaveButton.gridy = 12;
                                add(saveButton, gbc_SaveButton);
        
       
       
    }
   
   
    	}



