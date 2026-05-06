package staffMember;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.Hospital;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.Specialization;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map.Entry;

public class DocsBySpec extends JPanel {

    private static final long serialVersionUID = 1L;
    private JList<String> list;
    private JScrollPane scrollPane;

    /**
     * Create the panel.
     */
    public DocsBySpec(StaffMembers s) {
    	this.setPreferredSize(new Dimension(390,235));
        this.setBackground(new Color(0xA9BED2));
        this.setLayout(new GridBagLayout());
        
        // Create the JList and JScrollPane
        list = new JList<>();
        scrollPane = new JScrollPane(list);
        list.setBackground(new Color(0xA9BED2));
        scrollPane.setBackground(new Color(0xA9BED2));


        
        // Populate the JList
        populateList();
        
        // Add the JScrollPane to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        this.add(scrollPane, gbc);
    }

    private void populateList() {
        // Sample HashMap
        HashMap<Specialization, Integer> map = Hospital.getInstance().getNumberOfDoctorsBySpecialization();
        
        // Convert HashMap to List<String>
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Entry<Specialization, Integer> entry : map.entrySet()) {
            listModel.addElement(entry.getKey() + ": " + entry.getValue());
        }
        list.setModel(listModel);
    }


}
