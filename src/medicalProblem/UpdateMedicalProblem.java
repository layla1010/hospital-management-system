//212930952
//319098174
package medicalProblem;

import javax.swing.*;
import control.Hospital;
import exceptions.InvalidUserDetails;
import exceptions.ObjectDoesNotExist;
import model.Department;
import model.Disease;
import model.Fracture;
import model.Injury;
import model.MedicalProblem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class UpdateMedicalProblem extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldName;
    private JComboBox<Department> departmentsComboBox;
    private JTextField textFieldDescription;
    private JTextField textFieldLocation;
    private JTextField textFieldCommonRecoveryTime;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private ButtonGroup trueOrFalseGroup;
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JLabel nameLabel;
    private JLabel departmentLabel;
    private JLabel locationLabel;
    private JLabel requiresCastLabel;
    private JLabel descriptionLabel;
    private JLabel commonRecoveryTimeLabel;

    public UpdateMedicalProblem(MedicalProblems m,MedicalProblem medicalProblem) {
        setBackground(new Color(0xA9BED2));
        trueOrFalseGroup = new ButtonGroup();
		 this.setPreferredSize(new Dimension(500, 250)); 


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title Label
        JLabel lblTitle = new JLabel("Update Medical Problem");
        lblTitle.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitle, gbc);

        // Initialize card layout and panels
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Default empty panel
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0xA9BED2));
        cardsPanel.add(emptyPanel, "empty");

        // Fetch departments and add to combo box
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

        // Initialize JTextField components
        textFieldName = new JTextField(20);
        textFieldDescription = new JTextField(20);
        textFieldLocation = new JTextField(20);
        textFieldCommonRecoveryTime = new JTextField(20);
        textFieldName.setBackground(new Color(0x698DB0));
        textFieldDescription.setBackground(new Color(0x698DB0));
        textFieldLocation.setBackground(new Color(0x698DB0));
        textFieldCommonRecoveryTime.setBackground(new Color(0x698DB0));

        // Initialize Radio Buttons
        trueRadioButton = new JRadioButton("Yes");
        falseRadioButton = new JRadioButton("No");
        trueOrFalseGroup.add(trueRadioButton);
        trueOrFalseGroup.add(falseRadioButton);

        // Initialize Labels
        nameLabel = new JLabel("Name:");
        departmentLabel = new JLabel("Department:");
        locationLabel = new JLabel("Location:");
        requiresCastLabel = new JLabel("Requires Cast:");
        descriptionLabel = new JLabel("Description:");
        commonRecoveryTimeLabel = new JLabel("Common Recovery Time:");

        nameLabel.setForeground(Color.BLACK);
        departmentLabel.setForeground(Color.BLACK);
        locationLabel.setForeground(Color.BLACK);
        requiresCastLabel.setForeground(Color.BLACK);
        descriptionLabel.setForeground(Color.BLACK);
        commonRecoveryTimeLabel.setForeground(Color.BLACK);

        // Create panels for different problem types
        createPanels(medicalProblem);

        // Add the cardsPanel to the main layout
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(cardsPanel, gbc);

        // Update Button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    validateFields(medicalProblem);
                    Hospital.getInstance().getMedicalProblem(medicalProblem.getCode()).setDepartment((Department)departmentsComboBox.getSelectedItem());
                    Hospital.getInstance().getMedicalProblem(medicalProblem.getCode()).setName(textFieldName.getText());
                
                     m.refreshList();

                   
                    JOptionPane.showMessageDialog(null, "Update Successful");

                } catch (InvalidUserDetails ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }catch (ObjectDoesNotExist ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }catch(NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);

                }
                
            }
        });

        add(btnUpdate, gbc);

        // Update visibility based on the medical problem type
        updatePanel(medicalProblem);
    }

    private void updatePanel(MedicalProblem medicalProblem) {
        if (medicalProblem instanceof Injury) {
            cardLayout.show(cardsPanel, "Injury");
        } else if (medicalProblem instanceof Fracture) {
            cardLayout.show(cardsPanel, "Fracture");
        } else if (medicalProblem instanceof Disease) {
            cardLayout.show(cardsPanel, "Disease");
        }
    }
//TODO when updating Disease Department Label and combo box and name label and field are missing
    //TODO when updating Fracture Department Label and combo box and name label and field and Location label and Field are missing 
    private void createPanels(MedicalProblem m) {
        // Disease Panel
    	if(m instanceof Disease) {
        JPanel diseasePanel = new JPanel(new GridBagLayout());
        diseasePanel.setBackground(new Color(0xA9BED2));
        diseasePanel.add(nameLabel, createGbc(0, 0));
        diseasePanel.add(textFieldName, createGbc(1, 0));
        diseasePanel.add(descriptionLabel, createGbc(0, 1));
        diseasePanel.add(textFieldDescription, createGbc(1, 1));
        diseasePanel.add(departmentLabel, createGbc(0, 2));
        diseasePanel.add(departmentsComboBox, createGbc(1, 2));
        cardsPanel.add(diseasePanel, "Disease");
    	}

        // Fracture Panel
    	if(m instanceof Fracture) {
        JPanel fracturePanel = new JPanel(new GridBagLayout());
        fracturePanel.setBackground(new Color(0xA9BED2));
        fracturePanel.add(nameLabel, createGbc(0, 0));
        fracturePanel.add(textFieldName, createGbc(1, 0));
        fracturePanel.add(locationLabel, createGbc(0, 1));
        fracturePanel.add(textFieldLocation, createGbc(1, 1));
        fracturePanel.add(requiresCastLabel, createGbc(0, 2));
        JPanel castPanel = new JPanel();
        castPanel.setBackground(new Color(0xA9BED2));
        castPanel.add(trueRadioButton);
        castPanel.add(falseRadioButton);
        fracturePanel.add(castPanel, createGbc(1, 2));
        fracturePanel.add(departmentLabel, createGbc(0, 3));
        fracturePanel.add(departmentsComboBox, createGbc(1, 3));
        cardsPanel.add(fracturePanel, "Fracture");
    	}

        // Injury Panel
    	if(m instanceof Injury) {
        JPanel injuryPanel = new JPanel(new GridBagLayout());
        injuryPanel.setBackground(new Color(0xA9BED2));
        injuryPanel.add(nameLabel, createGbc(0, 0));
        injuryPanel.add(textFieldName, createGbc(1, 0));
        injuryPanel.add(locationLabel, createGbc(0, 1));
        injuryPanel.add(textFieldLocation, createGbc(1, 1));
        injuryPanel.add(commonRecoveryTimeLabel, createGbc(0, 2));
        injuryPanel.add(textFieldCommonRecoveryTime, createGbc(1, 2));
        injuryPanel.add(departmentLabel, createGbc(0, 3));
        injuryPanel.add(departmentsComboBox, createGbc(1, 3));
        cardsPanel.add(injuryPanel, "Injury");
    	}
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private void validateFields(MedicalProblem medicalProblem) throws InvalidUserDetails {
        if (textFieldName.getText().trim().isEmpty()) {
            throw new NullPointerException("Name cannot be empty");
        }
      MedicalProblem m=  Hospital.getInstance().getMedicalProblem(medicalProblem.getCode());

        if (medicalProblem instanceof Disease) {
            if (textFieldDescription.getText().trim().isEmpty()) {
                throw new NullPointerException("Description cannot be empty");
            }((Disease)m).setDescription(textFieldDescription.getText());
        } else if (medicalProblem instanceof Fracture) {
            if (textFieldLocation.getText().trim().isEmpty()) {
                throw new NullPointerException("Location cannot be empty");
            }
            if (!trueRadioButton.isSelected() && !falseRadioButton.isSelected()) {
                throw new NullPointerException("Select if cast is required");
            }
            ((Fracture)m).setLocation(textFieldLocation.getText());
            if(trueRadioButton.isSelected()) {
            	 ((Fracture)m).setRequiresCast(true);
            }
           if(falseRadioButton.isSelected()) {
        	   ((Fracture)m).setRequiresCast(false);
           }
            

        } else if (medicalProblem instanceof Injury) {
            if (textFieldLocation.getText().trim().isEmpty()) {
                throw new NullPointerException("Location cannot be empty");
            }
            if (textFieldCommonRecoveryTime.getText().trim().isEmpty()) {
                throw new NullPointerException("Common Recovery Time cannot be empty");
            }
            if(!textFieldCommonRecoveryTime.getText().matches("\\d*\\.?\\d+")) {	
				throw new InvalidUserDetails("Common Recovery Time Can Only Contain Numbers.");
			}
            ((Injury)m).setCommonRecoveryTime(Integer.parseInt(textFieldCommonRecoveryTime.getText()));
        }
    }
   

}
