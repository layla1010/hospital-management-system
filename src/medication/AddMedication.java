//212930952
//319098174
package medication;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Patient.UpdatePatient;
import control.Hospital;
import exceptions.InvalidUserDetails;
import exceptions.NegativeDosageException;
import exceptions.NegativeNumberOfDosesException;
import exceptions.ObjectAlreadyExistsException;
import model.Medication;

public class AddMedication extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField dosageField;
    private JTextField numOfDosageField;

    public AddMedication(Medications m) {

        this.setBackground(new Color(0xA9BED2));
		 this.setPreferredSize(new Dimension(420, 270)); 


        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 59, 0, 0, 0, 0, 0, 0, 20, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("Add A Medication");
        lblNewLabel.setFont(new Font("Traditional Arabic", Font.PLAIN, 22));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridheight = 2;
        gbc_lblNewLabel.gridwidth = 11;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 1;
        add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Code:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 2;
        gbc_lblNewLabel_1.gridy = 3;
        add(lblNewLabel_1, gbc_lblNewLabel_1);

        codeField = new JTextField();
        codeField.setBackground(new Color(0x698DB0));
        codeField.setForeground(Color.WHITE); 
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 6;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 3;
        gbc_textField.gridy = 3;
        add(codeField, gbc_textField);
        codeField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Name:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 2;
        gbc_lblNewLabel_2.gridy = 4;
        add(lblNewLabel_2, gbc_lblNewLabel_2);

        nameField = new JTextField();
        nameField.setBackground(new Color(0x698DB0));
        nameField.setForeground(Color.WHITE); 
        nameField.setColumns(10);
        GridBagConstraints gbc_nameField = new GridBagConstraints();
        gbc_nameField.gridwidth = 6;
        gbc_nameField.insets = new Insets(0, 0, 5, 5);
        gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
        gbc_nameField.gridx = 3;
        gbc_nameField.gridy = 4;
        add(nameField, gbc_nameField);

        JLabel lblNewLabel_3 = new JLabel("Dosage:");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 2;
        gbc_lblNewLabel_3.gridy = 5;
        add(lblNewLabel_3, gbc_lblNewLabel_3);

        dosageField = new JTextField();
        dosageField.setBackground(new Color(0x698DB0));
        dosageField.setForeground(Color.WHITE); 
        dosageField.setColumns(10);
        GridBagConstraints gbc_dosageField = new GridBagConstraints();
        gbc_dosageField.gridwidth = 6;
        gbc_dosageField.insets = new Insets(0, 0, 5, 5);
        gbc_dosageField.fill = GridBagConstraints.HORIZONTAL;
        gbc_dosageField.gridx = 3;
        gbc_dosageField.gridy = 5;
        add(dosageField, gbc_dosageField);

        JLabel lblNewLabel_4 = new JLabel("Number Of Dose:");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 2;
        gbc_lblNewLabel_4.gridy = 6;
        add(lblNewLabel_4, gbc_lblNewLabel_4);

        numOfDosageField = new JTextField();
        numOfDosageField.setBackground(new Color(0x698DB0));
        numOfDosageField.setForeground(Color.WHITE); 
        numOfDosageField.setColumns(10);
        GridBagConstraints gbc_numOfDosageField = new GridBagConstraints();
        gbc_numOfDosageField.gridwidth = 6;
        gbc_numOfDosageField.insets = new Insets(0, 0, 5, 5);
        gbc_numOfDosageField.fill = GridBagConstraints.HORIZONTAL;
        gbc_numOfDosageField.gridx = 3;
        gbc_numOfDosageField.gridy = 6;
        add(numOfDosageField, gbc_numOfDosageField);

        JButton btnNewButton = new JButton("Save");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 11;
        gbc_btnNewButton.gridy = 8;
        add(btnNewButton, gbc_btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(validateFields()) {
                    	Medication med=new Medication(Integer.parseInt(codeField.getText()), nameField.getText(),Double.parseDouble(dosageField.getText()), 
                    			Integer.parseInt(numOfDosageField.getText()));
                    	Hospital.getInstance().addMedication(med);
                    	m.refreshList();
                        JOptionPane.showMessageDialog(null, "Medication saved successfully.");
                    }
                } catch (InvalidUserDetails ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xA9BED2));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 14;
        gbc_panel.gridy = 9;
        add(panel, gbc_panel);
    }
//TODO addmeds
    private boolean validateFields() throws InvalidUserDetails  {
    	try {
		        if (codeField.getText().isEmpty() || nameField.getText().isEmpty() || dosageField.getText().isEmpty()
		        || numOfDosageField.getText().isEmpty()) {
		            throw new NullPointerException("All Fields Must Be Filled.");
		        }
		        if(!codeField.getText().trim().matches("\\d+")) {
		            throw new InvalidUserDetails("Code Must Only Contain Numbers.");
		
		        }
		        if(!numOfDosageField.getText().trim().matches("\\d+" )) {
		            throw new InvalidUserDetails("Number Of Doses Must Only Contain Numbers.");
		
		        }
		        if(Integer.parseInt(numOfDosageField.getText())<0) {
		        	throw new NegativeNumberOfDosesException(Integer.parseInt(numOfDosageField.getText()));
		        }
		        if(!dosageField.getText().trim().matches("\\d+(\\.\\d+)?" )) {
		            throw new InvalidUserDetails("Dosage Must Only Contain Numbers.");
		        }
		        if(Double.parseDouble(dosageField.getText())<0) {
		        	throw new NegativeDosageException(Double.parseDouble(dosageField.getText()));
		        }
    	}catch(InvalidUserDetails ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
    	}catch(ObjectAlreadyExistsException o){
            JOptionPane.showMessageDialog(null, "Medication Already Exists!");
		}catch(NullPointerException ec) {
			JOptionPane.showMessageDialog(null, ec.getMessage());
		}
    	return true;

        
    }
    
   

}
