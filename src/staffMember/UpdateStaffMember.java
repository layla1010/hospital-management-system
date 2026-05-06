package staffMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import java.io.File;
import control.Hospital;
import enums.Specialization;
import exceptions.FutureDateException;
import exceptions.ObjectDoesNotExist;
import model.Doctor;
import model.IntensiveCareNurse;
import model.*;
import model.StaffMember;

public class UpdateStaffMember extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel inputPanel;
    private JComboBox<String> attributeComboBox;
    private JTextField textField;
    private JDateChooser dateChooser;
    private JPasswordField passwordField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton otherRadioButton;
    private ButtonGroup genderGroup;
    private JButton updateButton;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Date LIMIT_DATE;
    private ButtonGroup trueOrFalseGroup;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private JComboBox<Specialization> specializationComboBox;
    private StaffMember staffMember;
    private StaffMembers staffMembers;
    private JLabel profilePictureLabel;
    private JButton chooseImageButton;
    private File selectedImageFile;
    static {
        try {
            LIMIT_DATE = sdf.parse("30/04/2024");
        } catch (ParseException e) {
            throw new RuntimeException("Error initializing date limit", e);
        }
    }

    public UpdateStaffMember(StaffMembers s,StaffMember staffMember) {
        this.staffMember = staffMember; 
        this.staffMembers = s; 
        profilePictureLabel = new JLabel();
        profilePictureLabel.setPreferredSize(new Dimension(100, 100)); // Set a preferred size
        chooseImageButton = new JButton("Choose Profile Picture");

        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "gif");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedImageFile = fileChooser.getSelectedFile();
                    ImageIcon profileImage = new ImageIcon(selectedImageFile.getAbsolutePath());
                    Image scaledImage = profileImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    profilePictureLabel.setIcon(new ImageIcon(scaledImage));
                }
            }
        });

        // Add to your layout
        JPanel imagePanel = new JPanel(new FlowLayout());
        imagePanel.setBackground(new Color(0xA9BED2));
        imagePanel.add(profilePictureLabel);
        imagePanel.add(chooseImageButton);

    

        this.setBackground(new Color(0xA9BED2));
        setLayout(new BorderLayout());

        JLabel chooseLabel = new JLabel("Choose an attribute to change:");
        chooseLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(0xA9BED2));

        attributeComboBox = new JComboBox<>();
        specializationComboBox = new JComboBox<>(Specialization.values());
        specializationComboBox.setVisible(false);

        GridBagConstraints gbc_specializationComboBox = new GridBagConstraints();
        gbc_specializationComboBox.gridwidth = 3;
        gbc_specializationComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_specializationComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_specializationComboBox.gridx = 2;
        gbc_specializationComboBox.gridy = 10;
        inputPanel.add(specializationComboBox, gbc_specializationComboBox);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(0xA9BED2));
        topPanel.add(chooseLabel);
        topPanel.add(attributeComboBox);

        add(topPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        textField = new JTextField(10);
        dateChooser = new JDateChooser();
        passwordField = new JPasswordField(10);
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        otherRadioButton = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);
        trueOrFalseGroup = new ButtonGroup();
        trueRadioButton = new JRadioButton();
        falseRadioButton = new JRadioButton();
        trueOrFalseGroup.add(trueRadioButton);
        trueOrFalseGroup.add(falseRadioButton);

        textField.setBackground(new Color(0x698DB0));
        textField.setForeground(Color.WHITE);
        dateChooser.setBackground(new Color(0x698DB0));
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setBackground(new Color(0x698DB0));
        passwordField.setBackground(new Color(0x698DB0));
        passwordField.setForeground(Color.WHITE);

        updateAttributeComboBox(staffMember);
        attributeComboBox.addActionListener(e -> updateInputPanel());

        updateButton = new JButton("Update");
        updateButton.addActionListener(e -> handleUpdateButtonClick());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(0xA9BED2));
        bottomPanel.add(updateButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
   

  
    private void updateAttributeComboBox(StaffMember staffMember) {
        attributeComboBox.removeAllItems();

        String[] doctorAttributes = {"", "First Name", "Last Name", "Birth Date", "Address", "Phone Number", "Email", "Gender", "Work Start Date", "Salary", "User Name", "Password", "License Number", "Is Finished Internship", "Specialization","Profile Picture"};
        String[] nurseAttributes = {"", "First Name", "Last Name", "Birth Date", "Address", "Phone Number", "Email", "Gender", "Work Start Date", "Salary", "User Name", "Password", "License Number", "Works in Intensive Care","Profile Picture"};

        if (staffMember instanceof Doctor) {
            for (String attribute : doctorAttributes) {
                attributeComboBox.addItem(attribute);
            }
        } else if (staffMember instanceof Nurse) {
            for (String attribute : nurseAttributes) {
                attributeComboBox.addItem(attribute);
            }
        }
    }

    private void updateInputPanel() {
        inputPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String selectedAttribute = (String) attributeComboBox.getSelectedItem();

        if (selectedAttribute == null || selectedAttribute.isEmpty()) {
            return;
        }

        try {
            switch (selectedAttribute) {
                case "First Name":
                case "Last Name":
                case "Address":
                case "Phone Number":
                case "Email":
                case "Salary":
                case "User Name":
                case "License Number":
                    addLabelAndTextField(selectedAttribute + ":", textField, gbc, 0);
                    break;
                case "Birth Date":
                case "Work Start Date":
                    addLabelAndDateChooser(selectedAttribute + ":", dateChooser, gbc, 0);
                    break;
                case "Gender":
                    addGenderOptions(gbc, 0);
                    break;
                case "Password":
                    addLabelAndPasswordField("Password:", passwordField, gbc, 0);
                    break;
                case "Is Finished Internship":
                    addLabelAndRadioButtons("Is Finished Internship:", new String[]{"True", "False"}, trueOrFalseGroup, gbc, 0);
                    break;
                case "Specialization":
                    addSpecializationOption(gbc, 0);
                    specializationComboBox.setVisible(true);
                    break;
                case "Works in Intensive Care":
                    addLabelAndRadioButtons("Works in Intensive Care:", new String[]{"True", "False"}, trueOrFalseGroup, gbc, 0);
                    break;
                case "Profile Picture":
                    JButton chooseFileButton = new JButton("Choose File");
                    chooseFileButton.addActionListener(e -> handleChooseFile());
                    addLabelAndComponent("Profile Picture:", chooseFileButton, gbc, 0);
                    
                    break;
                default:
                    specializationComboBox.setVisible(false);
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating input panel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }
    private void handleChooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            textField.setText(selectedFile.getAbsolutePath()); // Display file path in the text field
        }
    }
    private void handleUpdateButtonClick() throws ObjectDoesNotExist {
        try {
            String selectedAttribute = (String) attributeComboBox.getSelectedItem();
            if (selectedAttribute == null || selectedAttribute.isEmpty()) {
                throw new Exception("Please select an attribute to update.");
            }

            validateInput(selectedAttribute);

            StaffMember realStaffMember = Hospital.getInstance().getStaffMember(staffMember.getId());

            switch (selectedAttribute) {
            
                case "First Name":
                    realStaffMember.setFirstName(textField.getText());
                    break;

                case "Last Name":
                    realStaffMember.setLastName(textField.getText());
                    break;

                case "Address":
                    realStaffMember.setAddress(textField.getText());
                    break;

                case "Phone Number":
                    realStaffMember.setPhoneNumber(textField.getText());
                    break;

                case "Email":
                    realStaffMember.setEmail(textField.getText());
                    break;

                case "Salary":
                    realStaffMember.setSalary(Double.parseDouble(textField.getText()));
                    break;

                case "User Name":
                    realStaffMember.setUserName(textField.getText());
                    break;

                case "License Number":
                    if (realStaffMember instanceof Doctor) {
                        ((Doctor) realStaffMember).setLicenseNumber(Integer.parseInt(textField.getText()));
                    } else if (realStaffMember instanceof Nurse) {
                        ((Nurse) realStaffMember).setLicenseNumber(Integer.parseInt(textField.getText()));
                    }
                    break;

                case "Password":
                    realStaffMember.setPassword(new String(passwordField.getPassword()));
                    break;

                case "Birth Date":
                    realStaffMember.setBirthDate(dateChooser.getDate());
                    break;

                case "Work Start Date":
                    realStaffMember.setWorkStartDate(dateChooser.getDate());
                    break;

                case "Gender":
                    if (maleRadioButton.isSelected()) {
                        realStaffMember.setGender("Male");
                    } else if (femaleRadioButton.isSelected()) {
                        realStaffMember.setGender("Female");
                    } else if (otherRadioButton.isSelected()) {
                        realStaffMember.setGender("Other");
                    }
                    break;

                case "Is Finished Internship":
                    if (realStaffMember instanceof Doctor) {
                        ((Doctor) realStaffMember).setFinishInternship(trueRadioButton.isSelected());
                    }
                    break;

                case "Works in Intensive Care":
                	if (realStaffMember instanceof Nurse) {
                	    if (trueRadioButton.isSelected()) {
                	    	Hospital.getInstance().addIntensiveCareNurse(((IntensiveCareNurse) realStaffMember));

                	    } else {
                	    	if(realStaffMember instanceof IntensiveCareNurse){
                	    		((IntensiveCareNurse) realStaffMember).getIntensiveCareDepartment().removeNurse((IntensiveCareNurse)realStaffMember);
                	    	}
//TODO IF THIS CODE WORKS FOR INTENSIVE CARE
                	    }
                    }
                    break;
                    
                case "Profile Picture":
                    String imagePath = textField.getText();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        realStaffMember.setProfilePicture(imagePath);
                        JOptionPane.showMessageDialog(this, "Profile picture updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        throw new Exception("Please choose a valid image file.");
                    }
                    break;
                case "Specialization":
                    if (realStaffMember instanceof Doctor) {
                        ((Doctor) realStaffMember).setSpecialization((Specialization) specializationComboBox.getSelectedItem());
                    }
                    break;

                default:
                    throw new Exception("Invalid attribute selected.");
                   
            }
            
            staffMembers.refreshList();
            JOptionPane.showMessageDialog(this, "Update successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void validateInput(String selectedAttribute) throws Exception {
    	if (selectedAttribute.equals("Profile Picture") && (textField.getText() == null || textField.getText().isEmpty())) {
            throw new Exception("Please choose a valid image file.");
        }

        if (selectedAttribute.equals("Birth Date") || selectedAttribute.equals("Work Start Date")) {
            if (dateChooser.isVisible()) {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate == null) {
                    throw new NullPointerException("Date field must be filled.");
                }
                if (selectedDate.after(LIMIT_DATE)) {
                    throw new FutureDateException(LIMIT_DATE);
                }
            }
        }

        if (selectedAttribute.equals("Is Finished Internship")) {
            if (trueOrFalseGroup.getSelection().equals(null)) {
                throw new NullPointerException("Field must be filled.");
            }
        }
        if(selectedAttribute.equals("Works in Intensive Care")){
        	if (trueOrFalseGroup.getSelection().equals(null)) {
                throw new NullPointerException("Field must be filled.");
            }
        }

        if (textField.isVisible()) {
            if (selectedAttribute.equals("Salary") && textField.getText().isEmpty()) {
                throw new NullPointerException("Salary Field must be filled.");
            }
            if (selectedAttribute.equals("First Name") && textField.getText().isEmpty()) {
                throw new NullPointerException("First Name Field must be filled.");
            }
            if (selectedAttribute.equals("Last Name") && textField.getText().isEmpty()) {
                throw new NullPointerException("Last Name Field must be filled.");
            }
            if (selectedAttribute.equals("Phone Number") && textField.getText().isEmpty()) {
                throw new NullPointerException("Phone Number Field must be filled.");
            }
            if (selectedAttribute.equals("Address") && textField.getText().isEmpty()) {
                throw new NullPointerException("Address Field must be filled.");
            }
            if (selectedAttribute.equals("Email") && textField.getText().isEmpty()) {
                throw new NullPointerException("Email Field must be filled.");
            }
            if (selectedAttribute.equals("User Name") && textField.getText().isEmpty()) {
                throw new NullPointerException("User Name Field must be filled.");
            }
            if (selectedAttribute.equals("License Number") && textField.getText().isEmpty()) {
                throw new NullPointerException("License Number Field must be filled.");
            }
        }
    }

    private void addLabelAndTextField(String labelText, JTextField textField, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        inputPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        inputPanel.add(textField, gbc);
    }

    private void addLabelAndDateChooser(String labelText, JDateChooser dateChooser, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        inputPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        inputPanel.add(dateChooser, gbc);
    }

    private void addLabelAndPasswordField(String labelText, JPasswordField passwordField, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        inputPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);
    }

    private void addLabelAndRadioButtons(String labelText, String[] options, ButtonGroup group, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        inputPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(new Color(0xA9BED2));
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            radioButton.setBackground(new Color(0xA9BED2));
            group.add(radioButton);
            radioPanel.add(radioButton);
        }
        inputPanel.add(radioPanel, gbc);
    }

    private void addGenderOptions(GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        inputPanel.add(new JLabel("Gender:"), gbc);

        gbc.gridx = 1;
        JPanel genderPanel = new JPanel();
        genderPanel.setBackground(new Color(0xA9BED2));
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);
        inputPanel.add(genderPanel, gbc);
    }

    private void addSpecializationOption(GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        inputPanel.add(new JLabel("Specialization:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(specializationComboBox, gbc);
    }
    private void addLabelAndComponent(String labelText, JComponent component, GridBagConstraints gbc, int gridY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(component, gbc);
    }
  
    
}
