//212930952
//319098174
package medication;


import javax.swing.JPanel;

import Patient.UpdatePatient;

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



public class Medications extends SectionPanel<Medication> {


		private static final long serialVersionUID = 1L;
		
	    public Medications(Role userRole, String sectionName, DefaultListModel<Medication> listModel, JPanel quickLinksPanel) {
	    	super(userRole, sectionName, listModel, quickLinksPanel);
	    	this.initGenericListPanel(this::removeMedicationtFromHospital, this::showAddMedicationDialog, this::showUpdateMedicationDialog);
	    }
	    
	    private void removeMedicationtFromHospital(Medication v) {
	        Hospital.getInstance().removeMedication(v);
	        genericListPanel.refreshTableData(getTable());
	    }

	    private void showAddMedicationDialog() {
	       AddMedication addMedication = new AddMedication(this);
	        JDialog dialog = new JDialog((Frame) null, "Add Medication", true);
	        dialog.getContentPane().add(addMedication);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	        genericListPanel.refreshTableData(getTable());
	    }
	    private void showUpdateMedicationDialog(Medication m) {
	        UpdateMedication updateMedication = new UpdateMedication(this,m);
	        JDialog dialog = new JDialog((Frame) null, "Update Medication", true);
	        dialog.getContentPane().add(updateMedication);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	        genericListPanel.refreshTableData(getTable());
	    }
	    
	    @Override
	    protected void load() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Medication> Medication = hospital.getMedications();
	        for (Medication v : Medication.values()) {
	            listModel.addElement(v);
	        }
	    }
	    
	    protected boolean canAdd() {
	    	return userRole != Role.Nurse;
	    }
	    
	    protected boolean canRemove() {
	    	return userRole != Role.Nurse;
	    }
	    
	    protected boolean canUpdate() {
	    	return userRole != Role.Nurse;
	    }

		@Override
		public void initializeQuickPanelButtons() {
			quickLinksPanel.removeAll();
	    	quickLinksPanel.add(UtilsMethods.getRightPanelTitleLabel(UtilsMethods.QUICK_LINKS_TITLE));
	    	
	    	if(canAdd()) {
	    		JButton addButton1 = UtilsMethods.createPanelButton("Count Medication");
	    		
	            addButton1.addActionListener(e -> {
	                CountMedication countMedication = new CountMedication(this);
	                JDialog dialog = new JDialog((Frame) null, "Count Medication", true);
	                dialog.getContentPane().add(countMedication);
	                dialog.pack();
	                dialog.setLocationRelativeTo(null);
	                dialog.setVisible(true);
	            });
	            quickLinksPanel.add(addButton1);
				JButton addButton = UtilsMethods.createPanelButton("Add Medication");
		    	addButton.addActionListener(e -> {
		    		showAddMedicationDialog();
		    	});
		    	quickLinksPanel.add(addButton);
	    	}
		}

		@Override
		protected Object[][] getTable() {
			Object[][] table = new Object[listModel.getSize()][getColumns().length];

	    	for(int row=0; row < listModel.getSize(); row++) {
	    		Medication med = listModel.get(row);
				
	    		table[row][0] = new JLabel("" + med.getCode());
	    		table[row][1] = new JLabel(med.getName());
	    		table[row][2] = new JLabel("" + med.getDosage());
	    		table[row][3] = new JLabel("" + med.getNumberOfDose());
	    	}
	    	return table;
		}

		@Override
		protected String[] getColumns() {
			return new String[] {
					"Code",
					"Name",
					"Dosage",
					"Number of Dose"
			};
		}
	}


