package visit;

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

public class AddTreatmentToVisit extends JPanel {

    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardsPanel;

    public AddTreatmentToVisit(Visits v, Visit curVisit) {
    	this.setBackground(new Color(0xA9BED2));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel lblTitle = new JLabel("Add Medical Problem to Visit number " + curVisit.getNumber());
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitle, gbc);

        // ComboBox for options
        JComboBox<Treatment> comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(0x698DB0));
        
        comboBox.setModel(new DefaultComboBoxModel<>((Treatment[]) Hospital.getInstance().getTreatments().values().toArray(new Treatment[0])));
        comboBox.setRenderer(new ListCellRenderer<Treatment>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Treatment> list, Treatment value, int index,
					boolean isSelected, boolean cellHasFocus) {
				
				JLabel label = new JLabel();
				String repr = "" + value.getSerialNumber();
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
            	Treatment selectedTreatment = (Treatment) comboBox.getSelectedItem();
            	curVisit.addTreatment(selectedTreatment);
                v.refreshList();
                
                JOptionPane.showMessageDialog(null, "Added Treatment to Visit successfully!");
                
                
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
}
