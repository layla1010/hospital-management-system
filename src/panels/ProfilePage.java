package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Doctor;
import model.Nurse;
import model.StaffMember;

import java.awt.*;

public class ProfilePage extends JPanel {
    private static final long serialVersionUID = 1L;

    public ProfilePage(StaffMember staffMember) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#D4DEE8"));
        initializeProfile(staffMember);
    }

    private void initializeProfile(StaffMember staffMember) {
        // Load and scale the background image
        ImageIcon hBgIcon = new ImageIcon(getClass().getResource("/view/norahospital.jpg"));

        // Create a BackgroundImagePanel with the scaled image
        BackgroundImagePanel backgroundPanel = new BackgroundImagePanel(hBgIcon);
        add(backgroundPanel, BorderLayout.NORTH);

        // Create and configure the profile panel
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(Color.decode("#D4DEE8"));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        profilePanel.setPreferredSize(new Dimension(800, 700));

        // Create the new panel "laylapic" with GridBagLayout
        JPanel laylapicPanel = new JPanel(new GridBagLayout());
        laylapicPanel.setBackground(Color.decode("#D4DEE8")); // Set background color or other customization
        laylapicPanel.setPreferredSize(new Dimension(200, 700)); // Adjust size as needed

        // Profile Picture
        JLabel profilePictureLabel = new JLabel();
        profilePictureLabel.setPreferredSize(new Dimension(200, 200)); // Adjust size as needed
        profilePictureLabel.setHorizontalAlignment(JLabel.CENTER);

        // GridBagConstraints for positioning profile picture at the top
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10); // Adjust padding as needed

        // Add profile picture to laylapicPanel
        laylapicPanel.add(profilePictureLabel, gbc);

        JPanel innerPanel = new JPanel();
        innerPanel.setBackground(Color.decode("#D4DEE8"));

        // Add a filler component to ensure the profile picture stays at the top
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        laylapicPanel.add(innerPanel, gbc);

        // Create table model and JTable
        DefaultTableModel model = new DefaultTableModel();
        JTable profileTable = new JTable(model);

        // Define columns and data
        String[] columns = {"Label", "Value"};
        model.setColumnIdentifiers(columns);

        // Add profile info to the table
        addProfileInfo(model);

        // Remove the first and last rows
        if (model.getRowCount() > 0) {
            model.removeRow(0); // Remove the first row
        }
        if (model.getRowCount() > 0) {
            model.removeRow(model.getRowCount() - 1); // Remove the last row
        }

        // Customize table
        profileTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
        profileTable.setFillsViewportHeight(true);
        profileTable.setRowHeight(30); // Adjust row height as needed

        // Apply custom renderer to the table cells
        int cellFontSize = 16; // Adjust the font size for cell text as needed
//        profileTable.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer(cellFontSize));
//        profileTable.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer(cellFontSize));

        // Apply custom header renderer to the table
        int headerFontSize = 16; // Adjust the font size for header text as needed
//        profileTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer(headerFontSize));

        // Set the background color of the JTable to match the profile panel
        profileTable.setBackground(Color.decode("#D4DEE8"));

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(profileTable);
        profilePanel.add(scrollPane, BorderLayout.CENTER);

        add(profilePanel, BorderLayout.CENTER);

        // Add the "laylapic" panel to the east side of ProfilePage
        add(laylapicPanel, BorderLayout.EAST);

        // Initialize the specific profile based on staffMember type
        if (staffMember instanceof Doctor) {
            initializeDoctorProfile((Doctor) staffMember, profilePictureLabel);
        } else if (staffMember instanceof Nurse) {
            initializeNurseProfile((Nurse) staffMember, profilePictureLabel);
        }
    }

    private void addProfileInfo(DefaultTableModel model) {
        // Array of labels and sample values
        String[] labels = { "First Name", "Last Name", "Departments", "ID", "Gender",
                           "Address", "Phone Number", "Email", "Date of Birth", "Work Start Date",
                           "Finished Internship", "Salary", "Specialization", "License Number"};

        // Add rows to the table model
        for (String label : labels) {
            model.addRow(new Object[]{label + ":", ""});
        }
    }

    private void initializeDoctorProfile(Doctor doctor, JLabel profilePictureLabel) {
        profilePictureLabel.setIcon(loadScaledImage(doctor.getProfilePicture()));

        // Access the JScrollPane from the ProfilePanel
        JPanel profilePanel = (JPanel) ((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER);
        JScrollPane scrollPane = (JScrollPane) profilePanel.getComponent(0);
        JTable profileTable = (JTable) scrollPane.getViewport().getView();
        DefaultTableModel model = (DefaultTableModel) profileTable.getModel();

        // Clear existing rows (if any) before adding new data
        model.setRowCount(0);

        // Add the profile information to the table model
        addProfileInfo(model, doctor);
    }

    private void initializeNurseProfile(Nurse nurse, JLabel profilePictureLabel) {
        profilePictureLabel.setIcon(loadScaledImage(nurse.getProfilePicture()));

        // Access the JScrollPane from the ProfilePanel
        JPanel profilePanel = (JPanel) ((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER);
        JScrollPane scrollPane = (JScrollPane) profilePanel.getComponent(0);
        JTable profileTable = (JTable) scrollPane.getViewport().getView();
        DefaultTableModel model = (DefaultTableModel) profileTable.getModel();

        // Clear existing rows (if any) before adding new data
        model.setRowCount(0);

        // Add the profile information to the table model
        addProfileInfo(model, nurse);
    }

    private void addProfileInfo(DefaultTableModel model, StaffMember staffMember) {
        // Add rows with actual data from the Doctor object
        if (staffMember instanceof Doctor) {
            Doctor doctor = (Doctor) staffMember;
            model.addRow(new Object[]{"First Name:", doctor.getFirstName()});
            model.addRow(new Object[]{"Last Name:", doctor.getLastName()});
            model.addRow(new Object[]{"Departments:", doctor.getDepartments()});
            model.addRow(new Object[]{"ID:", doctor.getId()});
            model.addRow(new Object[]{"Gender:", doctor.getGender()});
            model.addRow(new Object[]{"Address:", doctor.getAddress()});
            model.addRow(new Object[]{"Phone Number:", doctor.getPhoneNumber()});
            model.addRow(new Object[]{"Email:", doctor.getEmail()});
            model.addRow(new Object[]{"Date of Birth:", doctor.getBirthDate()});
            model.addRow(new Object[]{"Work Start Date:", doctor.getWorkStartDate()});
            model.addRow(new Object[]{"Finished Internship:", doctor.isFinishInternship() ? "Yes" : "No"});
            model.addRow(new Object[]{"Salary:", doctor.getSalary()});
            model.addRow(new Object[]{"Specialization:", doctor.getSpecialization()});
            model.addRow(new Object[]{"License Number:", doctor.getLicenseNumber()});
        } else if (staffMember instanceof Nurse) {
            Nurse nurse = (Nurse) staffMember;
            model.addRow(new Object[]{"First Name:", nurse.getFirstName()});
            model.addRow(new Object[]{"Last Name:", nurse.getLastName()});
            model.addRow(new Object[]{"Departments:", nurse.getDepartments()});
            model.addRow(new Object[]{"ID:", nurse.getId()});
            model.addRow(new Object[]{"Gender:", nurse.getGender()});
            model.addRow(new Object[]{"Address:", nurse.getAddress()});
            model.addRow(new Object[]{"Phone Number:", nurse.getPhoneNumber()});
            model.addRow(new Object[]{"Email:", nurse.getEmail()});
            model.addRow(new Object[]{"Date of Birth:", nurse.getBirthDate()});
            model.addRow(new Object[]{"Work Start Date:", nurse.getWorkStartDate()});
            model.addRow(new Object[]{"Salary:", nurse.getSalary()});
        }
    }

    private ImageIcon loadScaledImage(String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    public JPanel getProfilePagePanel() {
        return this;
    }
}