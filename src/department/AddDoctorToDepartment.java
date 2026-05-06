//212930952
//319098174
package department;

import javax.swing.*;

import control.Hospital;
import enums.Specialization;
import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectDoesNotExist;
import medication.UpdateMedication;
import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDoctorToDepartment extends JPanel {

    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardsPanel;

    public AddDoctorToDepartment(Departments d, Department department) {
    	this.setBackground(new Color(0xA9BED2));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel lblTitle = new JLabel("Add Doctor for Department " + department.getName());
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitle, gbc);

        // ComboBox for options
        JComboBox<Doctor> comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(0x698DB0));
        
        comboBox.setModel(new DefaultComboBoxModel<>(getDoctors()));
        comboBox.setRenderer(new ListCellRenderer<Doctor>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Doctor> list, Doctor value, int index,
					boolean isSelected, boolean cellHasFocus) {
				
				JLabel label = new JLabel();
				String repr = value.getId() + ", " + value.getFirstName() + " " + value.getLastName();
				label.setText(repr);
				return label;
			}
        	
        });
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 1; // Position the combo box in the second row
        add(comboBox, gbc);

        // Create and add cards for each option
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        gbc.gridy = 2; // Position the cards panel in the third row
        gbc.weighty = 1.0; // Allow the panel to expand vertically
        gbc.fill = GridBagConstraints.BOTH;
        add(cardsPanel, gbc);


        // Update Button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(0x698DB0));
        btnUpdate.addActionListener(e -> {
            try {
                Doctor selectedDoctor = (Doctor) comboBox.getSelectedItem();
                Hospital.getInstance().addDoctorToDepartment(department, selectedDoctor);
                d.refreshList();
                
                JOptionPane.showMessageDialog(null, "Doctor added to DepartmentsSuccessfuly!");
                
                
            }catch(ObjectDoesNotExist ex) {
                JOptionPane.showMessageDialog(null, "Department Does Not Exists!");
            }catch(NullPointerException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        });
        
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        gbc.gridy = 3; // Position the update button in the fourth row
        gbc.weighty = 0; // Reset vertical weight
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnUpdate, gbc);
    }
    
    private Doctor[] getDoctors() {
    	int departmentsLength = Hospital.getInstance().getStaffMembers().size();
    	int doctorsCount = 0;
    	for(StaffMember sm : Hospital.getInstance().getStaffMembers().values()) {
    		if(sm instanceof Doctor) {
    			doctorsCount++;
    		}
    	}
    	
        Doctor[] doctors = new Doctor[doctorsCount];
        int idx = 0;
        for(StaffMember sm : Hospital.getInstance().getStaffMembers().values()) {
        	if(sm instanceof Doctor) {
        		doctors[idx++] = (Doctor) sm;
        	}
        }
        
        return doctors;
    }
    
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    
    
    
}
