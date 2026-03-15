import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginframe extends JFrame implements ActionListener {
    JTextField t_us;
    JPasswordField t_pas;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JButton submit;
    JButton signup_but;
    JLabel message;

    public loginframe() {
        setTitle("UrbanStore.pk - Login");
        JLabel headlineLabel = new JLabel("UrbanStore.pk Login");
        headlineLabel.setBounds(110, 10, 200, 30);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headlineLabel);
        
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 50, 100, 50);
        t_us = new JTextField();
        t_us.setBounds(200, 65, 150, 20);
        add(usernameLabel);
        add(t_us);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 90, 100, 50);
        t_pas = new JPasswordField();
        t_pas.setBounds(200, 105, 150, 20);
        add(passwordLabel);
        add(t_pas);

        submit = new JButton("Submit");
        submit.setBounds(150, 160, 80, 30);
        add(submit);
        submit.addActionListener(this);
        
        signup_but = new JButton("Sign Up");
        signup_but.setBounds(240, 160, 80, 30);
        add(signup_but);
        signup_but.addActionListener(this);
        
        message = new JLabel("Don't have an account?");
        message.setBounds(100, 130, 200, 50);
        add(message);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new loginframe();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String user = t_us.getText();
            String pass = new String(t_pas.getPassword());
            if (login(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                new UserDashboard().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login details!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == signup_but) {
            new userRegistrationForm();
            this.dispose();
        }
    }

    private boolean login(String username, String password) {
        String encryptedPass = EncryptionHelper.getEncryptedPassword(password);
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection con = DriverManager.getConnection(
"jdbc:postgresql://localhost:5432/ecom",
"postgres",
"1234");
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, encryptedPass);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
