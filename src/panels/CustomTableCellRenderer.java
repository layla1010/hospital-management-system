package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private int fontSize;

    public CustomTableCellRenderer(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Set the font size and make it bold
        cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD, fontSize));

        return cellComponent;
    }
}
    

class CustomHeaderRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;
    private int fontSize;

    public CustomHeaderRenderer(int fontSize) {
        this.fontSize = fontSize;
        setHorizontalAlignment(JLabel.LEFT); // Align header text to the right
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Use the super method to get the default header component
        Component headerComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Set the font size and make it bold
        headerComponent.setFont(headerComponent.getFont().deriveFont(Font.BOLD, fontSize));
        
        return headerComponent;
    }
}


