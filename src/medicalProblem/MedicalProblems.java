//212930952
//319098174
package medicalProblem;

import javax.swing.JPanel;


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



public class MedicalProblems extends SectionPanel<MedicalProblem> {
		private static final long serialVersionUID = 1L;

	    public MedicalProblems(Role userRole, String sectionName, DefaultListModel<MedicalProblem> listModel, JPanel quickLinksPanel) {
	    	super(userRole, sectionName, listModel, quickLinksPanel);
	    	this.initGenericListPanel(this::removeMedicalProblemtFromHospital, this::showAddMedicalProblemDialog, this::showUpdateMedicalProblemDialog);
	    }

	    private void removeMedicalProblemtFromHospital(MedicalProblem v) {
	        Hospital.getInstance().removeMedicalProblem(v);
	        genericListPanel.refreshTableData(getTable());
	    }

	    private void showAddMedicalProblemDialog() {
	       AddMedicalProblem addMedicalProblem = new AddMedicalProblem(this);
	        JDialog dialog = new JDialog((Frame) null, "Add MedicalProblem", true);
	        dialog.getContentPane().add(addMedicalProblem);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	        genericListPanel.refreshTableData(getTable());
	    }
	    private void showUpdateMedicalProblemDialog(MedicalProblem m) {
	    	UpdateMedicalProblem updateMedicalProblem = new UpdateMedicalProblem(this,m);
	        JDialog dialog = new JDialog((Frame) null, "Update MedicalProblem", true);
	        dialog.getContentPane().add(updateMedicalProblem);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	        genericListPanel.refreshTableData(getTable());
	    }
	    
	    protected void load() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<String, MedicalProblem> MedicalProblem = hospital.getMedicalProblems();
	        for (MedicalProblem v : MedicalProblem.values()) {
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
				JButton addButton = UtilsMethods.createPanelButton("Add Medical Problem");
		    	addButton.addActionListener(e -> {
		    		showAddMedicalProblemDialog();
		    	});
		    	quickLinksPanel.add(addButton);
		    	
		    	JButton addTreatmentButton = UtilsMethods.createPanelButton("Add Treatment to Medical Problem");
		    	addTreatmentButton.addActionListener(e -> {
		    		AddTreatmentToMedicalProblem addDepartment = new AddTreatmentToMedicalProblem(this, getSelectedObject());
			        JDialog dialog = new JDialog((Frame) null, "Add Doctor To Department", true);
			        dialog.getContentPane().add(addDepartment);
			        dialog.pack();
			        dialog.setLocationRelativeTo(null);
			        dialog.setVisible(true);
		    	});
		    	quickLinksPanel.add(addTreatmentButton);
	    	}
		}

		@Override
		protected Object[][] getTable() {
			Object[][] table = new Object[listModel.getSize()][getColumns().length];
			
	    	for(int row=0; row < listModel.getSize(); row++) {
	    		MedicalProblem mp = listModel.get(row);
	    		
	    		String[] treatments = new String[mp.getTreatmentsList().size()];
				int idx = 0;
				for(Treatment treatment : mp.getTreatmentsList()) {
					treatments[idx] = "" + treatment.getSerialNumber();
				}
				
	    		table[row][0] = new JLabel("" + mp.getCode());
	    		table[row][1] = new JLabel(mp.getName());
	    		table[row][2] = new JLabel("" + mp.getDepartment().getName());
	    		table[row][3] = new JComboBox<String>(treatments);
	    	}
	    	return table;
		}

		@Override
		protected String[] getColumns() {
			return new String[] {
					"Code",
					"Name",
					"Department Name",
					"Treatments"
			};
		}
	}

