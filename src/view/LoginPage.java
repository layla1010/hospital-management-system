package view;

import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import exceptions.*;
import control.*;
import model.*;
import java.awt.GridBagLayout;
import java.awt.Font;
import enums.Role;
import exceptions.InvalidUserDetails;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField textField;
    private ImageIcon backgroundIcon; // Store the background image once

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 725, 532);

        // Load the background image once
        backgroundIcon = new ImageIcon(getClass().getResource("/view/DNA_4K_3D_Animation_Background_V1__FREE_Download_Video__Med (1).gif"));

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        contentPane.setForeground(new Color(64, 128, 128));
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 0, true), new LineBorder(new Color(0, 0, 0), 0, true)));
        panel.setBackground(new Color(0, 0, 0, 130));
        panel.setBounds(167, 131, 304, 280);
        contentPane.add(panel);

        passwordField = new JPasswordField();
        passwordField.setForeground(new Color(255, 255, 255));
        passwordField.setCaretColor(new Color(0, 0, 0));
        passwordField.setBackground(new Color(0, 0, 94, 110));
        passwordField.setBounds(25, 135, 201, 28);
        panel.add(passwordField);

        textField = new JTextField();
        textField.setForeground(new Color(255, 255, 255));
        textField.setBackground(new Color(0, 0, 94, 110));
        textField.setColumns(10);
        textField.setBounds(25, 81, 201, 28);
        panel.add(textField);

        JLabel lblNewLabel = new JLabel("Enter UserName:");
        lblNewLabel.setForeground(new Color(128, 128, 128));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(25, 50, 201, 28);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Enter Password:");
        lblNewLabel_1.setForeground(new Color(128, 128, 128));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(25, 106, 201, 28);
        panel.add(lblNewLabel_1);

        Button button = new Button("Login");
        button.setForeground(new Color(192, 192, 192));
        button.setBackground(new Color(62, 62, 62, 100));
        button.setBounds(25, 198, 201, 37);
        panel.add(button);
        
     // Add a checkbox to show/hide the password
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(25, 170, 150, 20);
        showPasswordCheckBox.setBackground(new Color(0, 0, 0, 0));
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('*'); // Hide password
                }
            }
        });
        panel.add(showPasswordCheckBox);

        // Add action listener for the login button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    /**
     * Handle the login logic.
     */
    private void handleLogin() {
        String username = textField.getText();
        String password = new String(passwordField.getPassword());

        try {
            // Check if the username or password is empty
            if (username.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Username or Password cannot be empty.");
            }

            // Authentication logic
            int userID = Hospital.getInstance().authenticate(username, password);

            if (userID == 1) {
                // Redirect to AdminPage
                new UserPage(Role.Admin, null).setVisible(true);
                dispose();
            } else {
                StaffMember user = Hospital.getInstance().getStaffMember(userID);

                if (userID == -1) {
                    throw new InvalidUserDetails("The Entered Username Or password is invalid, Please Try Again");
                } else if (user instanceof Doctor) {
                    new UserPage(Role.Doctor, user).setVisible(true);
                    dispose();
                } else if (user instanceof Nurse) {
                    new UserPage(Role.Nurse, user).setVisible(true);
                    dispose();
                }
            }
        } catch (IllegalArgumentException ex) {
            // Show a message dialog for empty fields
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (InvalidUserDetails ex) {
            // Show a message dialog for invalid user details
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Catch any other exceptions
            javax.swing.JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}