package department;

//212930952
//319098174																																																																																																																																																																																																																																																																																																																														package department;


import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Hospital;
import enums.Role;
import model.*;
import panels.GenericListPanel;
import utils.UtilsMethods;
public class Departments extends SectionPanel<Department> {
	    private static final long serialVersionUID = 1L;

	    public Departments(Role userRole, String sectionName, DefaultListModel<Department> listModel, JPanel quickLinksPanel) {
	    	super(userRole, sectionName, listModel, quickLinksPanel);
	    	this.initGenericListPanel(this::removeDepartmentFromHospital, this::showAddDepartmentDialog, this::showUpdateDepartmentDialog);
	    }

	    private void removeDepartmentFromHospital(Department department) {
	        Hospital.getInstance().removeDepartment(department);
	        genericListPanel.refreshTableData(getTable());
	    }

	    private void showAddDepartmentDialog() {
	        AddDepartment addDepartment = new AddDepartment(this);
	        JDialog dialog = new JDialog((Frame) null, "Add Department", true);
	        dialog.getContentPane().add(addDepartment);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	        genericListPanel.refreshTableData(getTable());
	    }

	    private void showUpdateDepartmentDialog(Department department) {
	        UpdateDepartment updateDepartment = new UpdateDepartment(this,department);
	        JDialog dialog = new JDialog((Frame) null, "Update Department", true);
	        dialog.getContentPane().add(updateDepartment);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	        genericListPanel.refreshTableData(getTable());
	    }
	    
	    protected void load() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Department> departments = hospital.getDepartments();
	        for (Department department : departments.values()) {
	            listModel.addElement(department);
	        }
	    }
	    
	    protected boolean canAdd() {
	    	return userRole == Role.Admin;
	    }
	    
	    protected boolean canRemove() {
	    	return userRole == Role.Admin;
	    }
	    
	    protected boolean canUpdate() {
	    	return userRole == Role.Admin;
	    }

		@Override
		public void initializeQuickPanelButtons() {
			quickLinksPanel.removeAll();
	    	quickLinksPanel.add(UtilsMethods.getRightPanelTitleLabel(UtilsMethods.QUICK_LINKS_TITLE));
	    	
	    	if(canAdd()) {
				JButton addButton = UtilsMethods.createPanelButton("Add Department");
		    	addButton.addActionListener(e -> {
		    		showAddDepartmentDialog();
		    	});
		    	quickLinksPanel.add(addButton);
	    	}
	    	
	    	if(userRole == Role.Admin) {
	    		
	    		JButton managerButton = UtilsMethods.createPanelButton("Appoint A New Manager");
		    	managerButton.addActionListener(e -> {
		    		AppointANewManager appointANewManager = new AppointANewManager(this, getSelectedObject());
		    		 JDialog dialog = new JDialog((Frame) null, "Appoint A New Manager", true);
		    		 dialog.getContentPane().add(appointANewManager);
				        dialog.pack();
				        dialog.setLocationRelativeTo(null);
				        dialog.setVisible(true);
		    	});
		    	
	    		        quickLinksPanel.add(managerButton);
		    	JButton addButton = UtilsMethods.createPanelButton("Add Doctor to Department");
		    	addButton.addActionListener(e -> {
		    		AddDoctorToDepartment addDepartment = new AddDoctorToDepartment(this, getSelectedObject());
			        JDialog dialog = new JDialog((Frame) null, "Add Doctor To Department", true);
			        dialog.getContentPane().add(addDepartment);
			        dialog.pack();
			        dialog.setLocationRelativeTo(null);
			        dialog.setVisible(true);
		    	});
		    	quickLinksPanel.add(addButton);
	    	}
		}

		@Override
		protected Object[][] getTable() {
			Object[][] table = new Object[listModel.getSize()][getColumns().length];

	    	for(int row=0; row < listModel.getSize(); row++) {
	    		Department dep = listModel.get(row);
	    		table[row][0] = new JLabel("" + dep.getNumber());
	    		table[row][1] = new JLabel(dep.getName());
	    		table[row][2] = new JLabel(dep.getmanager().getFirstName() + " " + dep.getmanager().getLastName());
	    		table[row][3] = new JLabel(dep.getLocation());
	    		table[row][4] = new JLabel(dep.getSpecialization().toString());
	    		
	    	}
	    	return table;
	    	
		}
		

		@Override
		protected String[] getColumns() {
			return new String[] {
					"Number",
					"Name",
					"Manager Name",
					"Location",
					"Specialization"
			};
		}
	}


