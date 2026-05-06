package visit;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import Patient.AddPatient;
import control.Hospital;
import department.UpdateDepartment;
import exceptions.FutureDateException;

import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectDoesNotExist;
import medicalProblem.UpdateMedicalProblem;
import model.Department;
import model.Patient;
import model.Visit;
import utils.UtilsMethods;

import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;


public class AddVisit extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField number;
	private JTextField patientID;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final Date MAX_DATE;

	static {
		try {
			MAX_DATE = DATE_FORMAT.parse("30/04/2024");
		} catch (ParseException e) {
			throw new RuntimeException("Date parsing error", e);
		}
	}




	/**
	 * Create the panel.
	 */
	public AddVisit(Visits v) {

		 this.setPreferredSize(new Dimension(420, 280)); 
		 
		this.setBackground(new Color(0xA9BED2));


		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color (0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);

		JLabel lblNewLabel = new JLabel("Add A Visit");
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 3;
		gbc_lblNewLabel.gridwidth = 14;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 6;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		number = new JTextField();
		number.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_number = new GridBagConstraints();
		gbc_number.gridwidth = 9;
		gbc_number.insets = new Insets(0, 0, 5, 5);
		gbc_number.fill = GridBagConstraints.HORIZONTAL;
		gbc_number.gridx = 7;
		gbc_number.gridy = 6;
		add(number, gbc_number);
		number.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Patient ID:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.gridwidth = 5;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 2;
		gbc_lblNewLabel_1_1.gridy = 7;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);

		patientID = new JTextField();
		patientID.setBackground(new Color(0x698DB0)); // Set the background color of the text field
		patientID.setColumns(10);
		GridBagConstraints gbc_patientID = new GridBagConstraints();
		gbc_patientID.gridwidth = 9;
		gbc_patientID.insets = new Insets(0, 0, 5, 5);
		gbc_patientID.fill = GridBagConstraints.HORIZONTAL;
		gbc_patientID.gridx = 7;
		gbc_patientID.gridy = 7;
		add(patientID, gbc_patientID);

		JLabel lblNewLabel_1_1_1 = new JLabel("Start Date:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.gridwidth = 5;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 2;
		gbc_lblNewLabel_1_1_1.gridy = 8;
		add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);

		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBackground(new Color(0x698DB0)); // Set the background color of the text field
		startDateChooser.setForeground(Color.WHITE); // Set the text color to white
		startDateChooser.getCalendarButton().setPreferredSize(new Dimension(12, 12));
		GridBagConstraints gbc_startDateChooser = new GridBagConstraints();
		gbc_startDateChooser.gridwidth = 9;
		gbc_startDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_startDateChooser.fill = GridBagConstraints.BOTH;
		gbc_startDateChooser.gridx = 7;
		gbc_startDateChooser.gridy = 8;
		add(startDateChooser, gbc_startDateChooser);
		Date selectedDate = startDateChooser.getDate();
		JTextField dateTextField = (JTextField) startDateChooser.getDateEditor().getUiComponent();
		dateTextField.setBackground(new Color(0x698DB0)); 


		JLabel lblNewLabel_1_1_1_1 = new JLabel("End Date:");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_1.gridwidth = 5;
		gbc_lblNewLabel_1_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_1.gridx = 2;
		gbc_lblNewLabel_1_1_1_1.gridy = 9;
		add(lblNewLabel_1_1_1_1, gbc_lblNewLabel_1_1_1_1);

		JDateChooser endDateChooser = new JDateChooser();
		endDateChooser.getCalendarButton().setPreferredSize(new Dimension(12, 12));
		endDateChooser.setForeground(Color.WHITE);
		endDateChooser.setBackground(UIManager.getColor("Button.light"));
		GridBagConstraints gbc_endDateChooser = new GridBagConstraints();
		gbc_endDateChooser.gridwidth = 9;
		gbc_endDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_endDateChooser.fill = GridBagConstraints.BOTH;
		gbc_endDateChooser.gridx = 7;
		gbc_endDateChooser.gridy = 9;
		add(endDateChooser, gbc_endDateChooser);
		Date selectedDate_1 = endDateChooser.getDate();
		JTextField dateTextField_1 = (JTextField) endDateChooser.getDateEditor().getUiComponent();
		dateTextField_1.setBackground(new Color(0x698DB0));

		JButton btnNewButton = new JButton("Save\r\n");
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 16;
		gbc_btnNewButton.gridy = 12;
		add(btnNewButton, gbc_btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(number.getText().trim().isEmpty() || patientID.getText().trim().isEmpty() || startDateChooser.getDate()==null 
							|| endDateChooser.getDate()==null) {
						throw new NullPointerException("All Fields Must Be Filled.");
					}
					if (!number.getText().matches("\\d+") ) {
						throw new InvalidUserDetails("Number Field Must Only Contain Numbers.");
					}
					if(!patientID.getText().matches("\\d+")) {
						throw new InvalidUserDetails("ID Field Must Only Contain Numbers.");

					}
					if(startDateChooser.getDate().after(MAX_DATE) || endDateChooser.getDate().after(MAX_DATE)) {
						throw new FutureDateException(MAX_DATE);
					}
					if(startDateChooser.getDate().after(endDateChooser.getDate())) {
						throw new InvalidUserDetails("Start Date Cannot Be After End Date.");
					}
					Patient patient = (Patient) Hospital.getInstance().getRealPatient(Integer.parseInt(patientID.getText()));
					Visit visit = new Visit(Integer.parseInt(number.getText())
							,patient,startDateChooser.getDate(),endDateChooser.getDate());

							Hospital.getInstance().addVisit(visit);
							v.refreshList();
	                if(!Hospital.getInstance().getPatients().containsKey(Integer.parseInt(patientID.getText()))) {
						throw new ObjectDoesNotExist(patient.getId(), patient.getClass().getSimpleName(), this.getClass().getSimpleName());
				}
					JOptionPane.showMessageDialog(null, "Visit Added Successfully!");


				} catch (InvalidUserDetails ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(FutureDateException ec) {
					JOptionPane.showMessageDialog(null, "Invalid Date Input.");
				}catch(ObjectAlreadyExistsException ex) {
        			JOptionPane.showMessageDialog(AddVisit.this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}catch(NullPointerException es) {
					JOptionPane.showMessageDialog(null, es.getMessage());
				}catch(ObjectDoesNotExist ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				//TODO why the heck is there treatments an medicalproblems, should be deleted need laylas approval
				Patient patient = Hospital.getInstance().getRealPatient(Integer.parseInt(patientID.getText()));


				Visit visit = new Visit(Integer.parseInt(number.getText()),patient,startDateChooser.getDate(),
						endDateChooser.getDate());

				Hospital.getInstance().addVisit(visit);
				v.refreshList();
				JOptionPane.showMessageDialog(null, "Visit Added Successfully!.");
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color (0xA9BED2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 17;
		gbc_panel.gridy = 13;
		add(panel, gbc_panel);

	}
	
	

	   


	  
	
}