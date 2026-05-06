package treatment;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.Hospital;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectDoesNotExist;
import model.Treatment;
import visit.UpdateVisit;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTreatment extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField descriptionField;
    private JLabel serialNumberLabel;
    private JLabel descriptionLabel;
    private JButton updateButton;
    private String selectedItem;

    public UpdateTreatment(Treatments t,Treatment treatment) {
    	
        this.setBackground(new Color(0xA9BED2));
		this.setPreferredSize(new Dimension(430, 220)); 


        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("What Do You Want To Update?");
        lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridheight = 3;
        gbc_lblNewLabel.gridwidth = 12;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 2;
        add(lblNewLabel, gbc_lblNewLabel);

 
        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
        gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
        gbc_descriptionLabel.gridwidth = 3;
        gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
        gbc_descriptionLabel.gridx = 5;
        gbc_descriptionLabel.gridy = 9;
        add(descriptionLabel, gbc_descriptionLabel);
        descriptionLabel.setVisible(true);

        descriptionField = new JTextField();
        descriptionField.setForeground(Color.WHITE);
        descriptionField.setBackground(new Color(0x698DB0)); 
        GridBagConstraints gbc_descriptionField = new GridBagConstraints();
        gbc_descriptionField.gridwidth = 4;
        gbc_descriptionField.insets = new Insets(0, 0, 5, 5);
        gbc_descriptionField.fill = GridBagConstraints.HORIZONTAL;
        gbc_descriptionField.gridx = 9;
        gbc_descriptionField.gridy = 9;
        add(descriptionField, gbc_descriptionField);
        descriptionField.setColumns(10);
        descriptionField.setVisible(true);

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			if(descriptionField.getText().trim().isEmpty()) {
        				throw new NullPointerException("Field Must Be Filled.");
        			}
        			Hospital.getInstance().getRealTreatment(treatment.getSerialNumber()).setDescription(descriptionField.getText());
        			
        			
        			JOptionPane.showMessageDialog(null, "Treatment Updated Successfully!" );

        			
        		}catch(NullPointerException ec) {
        			JOptionPane.showMessageDialog(UpdateTreatment.this, ec.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        		}catch(ObjectDoesNotExist es) {
        			JOptionPane.showMessageDialog(null, es.getMessage());
        		}
        	}
        });
        
        
        updateButton.setBackground(new Color(0x698DB0));
        updateButton.setFont(new Font("Traditional Arabic", Font.PLAIN, 15));
        GridBagConstraints gbc_updateButton = new GridBagConstraints();
        gbc_updateButton.gridwidth = 2;
        gbc_updateButton.insets = new Insets(0, 0, 5, 5);
        gbc_updateButton.gridx = 14;
        gbc_updateButton.gridy = 12;
        add(updateButton, gbc_updateButton);

    }
   
   
}
