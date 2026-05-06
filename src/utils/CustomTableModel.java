package utils;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private String[] columnNames;
    private Object[][] data;
    public CustomTableModel(Object[][] data, String[] columns) {
    	this.columnNames = columns;
    	this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void setData(Object[][] newData) {
        this.data = newData;
        fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex] instanceof JComboBox;
    }
}
