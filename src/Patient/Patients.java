package Patient;

import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Hospital;
import enums.Role;
import model.*;
import panels.GenericListPanel;
import utils.UtilsMethods;

public class Patients extends SectionPanel<Patient> {
    private static final long serialVersionUID = 1L;
    
    public Patients(Role userRole, String sectionName, DefaultListModel<Patient> listModel, JPanel quickLinksPanel) {
    	super(userRole, sectionName, listModel, quickLinksPanel);
    	this.initGenericListPanel(this::removePatientFromHospital, this::showAddPatientDialog, this::showUpdatePatientDialog);
    }

    @Override
    protected void load() {
        Hospital hospital = Hospital.getInstance();
        HashMap<Integer, Patient> patients = hospital.getPatients();
        for (Patient patient : patients.values()) {
            listModel.addElement(patient);
        }
    }
    
    private void removePatientFromHospital(Patient patient) {
        Hospital.getInstance().removePatient(patient);
        genericListPanel.refreshTableData(getTable());
    }

    private void showAddPatientDialog() {
        AddPatient addPatient = new AddPatient(this);
        JDialog dialog = new JDialog((Frame) null, "Add Patient", true);
        dialog.getContentPane().add(addPatient);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        genericListPanel.refreshTableData(getTable());
    }

    private void showUpdatePatientDialog(Patient patient) {
        UpdatePatient updatePatient = new UpdatePatient(this,patient);
        JDialog dialog = new JDialog((Frame) null, "Update Patient", true);
        dialog.getContentPane().add(updatePatient);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        genericListPanel.refreshTableData(getTable());
    }

    @Override
    public void initializeQuickPanelButtons() {
    	quickLinksPanel.removeAll();
    	quickLinksPanel.add(UtilsMethods.getRightPanelTitleLabel(UtilsMethods.QUICK_LINKS_TITLE));
    	
    	if(canAdd()) {
	    	JButton addButton = UtilsMethods.createPanelButton("Add Patient");
	    	addButton.addActionListener(e -> {
	    		showAddPatientDialog();
	    	});
	    	quickLinksPanel.add(addButton);
    	}
    	
    	JButton wordButton = UtilsMethods.createPanelButton("Export to Word");
    	wordButton.addActionListener(e -> {
    		showAddPatientDialog();
    	});
    	quickLinksPanel.add(wordButton);
    	
	}

	@Override
	protected Object[][] getTable() {
		Object[][] table = new Object[listModel.getSize()][getColumns().length];

    	for(int row=0; row < listModel.getSize(); row++) {
    		Patient patient = listModel.get(row);
    		
    		String[] visits = new String[patient.getVisitsList().size()];
    		int idx = 0;
    		for(Visit visit: patient.getVisitsList()) {
    			visits[idx] = "" + visit.getNumber();
    		}
			
    		table[row][0] = new JLabel("" + patient.getId());
    		table[row][1] = new JLabel(patient.getFirstName());
    		table[row][2] = new JLabel(patient.getLastName());
    		table[row][3] = new JLabel(patient.getBirthDate().toString());
    		table[row][4] = new JLabel(patient.getAddress());
    		table[row][5] = new JLabel("" + patient.getPhoneNumber());
    		table[row][6] = new JLabel(patient.getEmail());
    		table[row][7] = new JLabel(patient.getGender());
    		table[row][8] = new JComboBox<String>(visits);
    		table[row][9] = new JLabel(patient.getHealthFund().toString());
    		table[row][10] = new JLabel(patient.getBiologicalSex().toString());
    	}
    	return table;
	}

	@Override
	protected String[] getColumns() {
		return new String[] {
				"ID",
				"First Name",
				"Last Name",
				"Birth Date",
				"Address",
				"Phone Number",
				"Email",
				"Gender",
				"Visits",
				"Health Fund",
				"Biological Sex"
		};
	}
}
