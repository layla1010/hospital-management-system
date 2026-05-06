package utils;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TableCellRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBox;
    private JLabel label;
    private boolean isComboBox;

    public TableCellRendererEditor() {
        comboBox = new JComboBox<>();
        label = new JLabel();
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value instanceof JComboBox) {
            isComboBox = true;
            JComboBox<String> comboValue = (JComboBox<String>) value;
            comboBox.setModel(comboValue.getModel());
            comboBox.setSelectedItem(((JComboBox<?>) value).getSelectedItem());
            return comboBox;
        } else if (value instanceof JLabel) {
            isComboBox = false;
            label.setText(((JLabel) value).getText());
            return label;
        } else {
            isComboBox = false;
            label.setText(value != null ? value.toString() : "");
            return label;
        }
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof JComboBox) {
            isComboBox = true;
            JComboBox<String> comboValue = (JComboBox<String>) value;
            comboBox.setModel(comboValue.getModel());
            comboBox.setSelectedItem(((JComboBox<?>) value).getSelectedItem());
            return comboBox;
        } else if (value instanceof JLabel) {
            isComboBox = false;
            label.setText(((JLabel) value).getText());
            return label;
        } else {
            // Default rendering behavior
            isComboBox = false;
            label.setText(value != null ? value.toString() : "");
            return label;
        }
	}
	
	@Override
    public Object getCellEditorValue() {
        if (isComboBox) {
            return comboBox.getSelectedItem();
        } else {
            return label.getText();
        }
    }
}