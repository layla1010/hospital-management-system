package model;

import java.awt.Frame;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import enums.Role;
import panels.GenericListPanel;
import staffMember.AddStaffMember;
import staffMember.UpdateStaffMember;

public abstract class SectionPanel<T>  extends JPanel {
	protected static final long serialVersionUID = 1L;
	protected String sectionName;
	protected GenericListPanel<T> genericListPanel;
    protected DefaultListModel<T> listModel;
    protected Role userRole;
    protected JPanel quickLinksPanel;
    
    public SectionPanel(Role userRole, String sectionName, DefaultListModel<T> listModel, JPanel quickLinksPanel) {
    	this.sectionName = sectionName;
    	this.userRole = userRole;
        this.listModel = listModel;
        this.quickLinksPanel = quickLinksPanel;
        
//        initializeQuickPanelButtons(quickLinksPanel);
        load();
    }
    
    public void initGenericListPanel(Consumer<T> removeCallback, Runnable addCallback, Consumer<T> updateCallback) {
    	genericListPanel = new GenericListPanel<>(
    			getTable(),
    			getColumns(),
        		sectionName,
        		listModel, 
        		canRemove() ? removeCallback : null,
        		canAdd() ? addCallback : null,
        		canUpdate() ? updateCallback : null
        	);
    }

	public JPanel getPanel() {
        return genericListPanel.getPanel();
    }


    public void refreshList() {
        listModel.clear();
        load();
        if(genericListPanel != null) {
        	genericListPanel.refreshTableData(getTable());
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
    
    public T getSelectedObject() {
    	return genericListPanel.getSelectedObject();
    }
    
    protected abstract void load();
    public abstract void initializeQuickPanelButtons();
    protected abstract Object[][] getTable();
    protected abstract String[] getColumns();
}
