import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class userRegistrationForm extends JFrame implements ActionListener {
    private JTextField firstNameField;
    private JTextField pincodeField;
    private JTextField mobileNoField;
    private JTextField addressField;
    private JTextField dateField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public userRegistrationForm() {
        setTitle("UrbanStore.pk - User Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2));

        JLabel firstNameLabel = new JLabel("Username:");
        firstNameField = new JTextField();

        JLabel pincodeLabel = new JLabel("Pincode:");
        pincodeField = new JTextField();

        JLabel mobileNoLabel = new JLabel("Mobile number:");
        mobileNoField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();

        JLabel dateLabel = new JLabel("DOB (YYYY-MM-DD):");
        dateField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        add(firstNameLabel);
        add(firstNameField);
        add(passwordLabel);
        add(passwordField);
        add(emailLabel);
        add(emailField);
        add(mobileNoLabel);
        add(mobileNoField);
        add(dateLabel);
        add(dateField);
        add(addressLabel);
        add(addressField);
        add(pincodeLabel);
        add(pincodeField);
        add(registerButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(userRegistrationForm::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(registerButton)) {
            String firstName = firstNameField.getText();
            String pin = pincodeField.getText();
            int pincode = Integer.parseInt(pin);
            String mobile = mobileNoField.getText();
            String address = addressField.getText();
            String dob = dateField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            // Perform registration logic here
            try {
                Class.forName("org.postgresql.Driver");
                Connection con = DriverManager.getConnection(
"jdbc:postgresql://localhost:5432/ecom",
"postgres",
"1234");
                PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, email, mobile_number, dob, address, pincode) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, firstName);
                ps.setString(2, EncryptionHelper.getEncryptedPassword(password));
                ps.setString(3, email);
                ps.setString(4, mobile);
                ps.setDate(5, java.sql.Date.valueOf(dob));
                ps.setString(6, address);
                ps.setInt(7, pincode);

                ps.execute();
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new loginframe();
                this.dispose();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
