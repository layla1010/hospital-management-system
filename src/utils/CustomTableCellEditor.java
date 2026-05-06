package utils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomTableCellEditor extends JPanel implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public CustomTableCellEditor() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	if(value instanceof JLabel) {
    		return (JLabel) value;
    	}
    	if(value instanceof JComboBox) {
    		return (JComboBox<String>) value;
    	}
        return (JComponent) value;
    }
}