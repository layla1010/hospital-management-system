package panels;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

import control.Hospital;

public class Information extends JPanel {

    private static final long serialVersionUID = 1L;
    private JList<String> list;
    private JScrollPane scrollPane;
    
    /**
     * Create the panel.
     */
    public Information() {
        this.setBackground(new Color(0xA9BED2));
        this.setPreferredSize(new Dimension(500,150));
        this.setLayout(new BorderLayout()); // Use BorderLayout for simplicity

        // Initialize the JList and JScrollPane
        list = new JList<>();
        list.setBackground(new Color(0xA9BED2)); // Set background color for JList
        list.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size for JList
        list.setCellRenderer(new CustomListCellRenderer()); // Set custom cell renderer
        scrollPane = new JScrollPane(list);

        // Populate the JList
        populateList();

        // Add the JScrollPane to the panel
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void populateList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        try {
            // Add the results of the methods to the list model
            listModel.addElement("Number of Intensive Care Staff Members: " + Hospital.getInstance().howManyIntensiveCareStaffMembers());
            listModel.addElement("Average Salary: " + Hospital.getInstance().avgSalary());
            listModel.addElement("Complies with Ministry of Health Standard: " + Hospital.getInstance().isCompliesWithTheMinistryOfHealthStandard());
        } catch (Exception e) {
            listModel.addElement("Error retrieving information.");
            e.printStackTrace();
        }
        
        // Set the model to the JList
        list.setModel(listModel);
    }

    // Custom ListCellRenderer to set font size
    private static class CustomListCellRenderer extends JLabel implements ListCellRenderer<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);
            setFont(new Font("Arial", Font.BOLD, 20)); // Set the font size here
            setOpaque(true);
            if (isSelected) {
                setBackground(new Color(0x4e7a9a)); // Optional: change background for selected item
                setForeground(Color.WHITE); // Optional: change text color for selected item
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }

    
}
