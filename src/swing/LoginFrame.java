package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel usernameLabel;
    private JTextField usernameText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JLabel resLabel;

    
    public LoginFrame() {
    	getContentPane().setBackground(new Color(128, 255, 255));
        setTitle("Login - Library Management System");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Library Management System");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(400, 30);
        title.setLocation(300, 30);
        c.add(title);

        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setSize(100, 20);
        usernameLabel.setLocation(100, 150);
        c.add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setForeground(Color.BLACK);
        usernameText.setBackground(new Color(255, 255, 255));
        usernameText.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameText.setSize(467, 20);
        usernameText.setLocation(233, 150);
        c.add(usernameText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(100, 220);
        c.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordText.setSize(467, 20);
        passwordText.setLocation(233, 220);
        c.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setForeground(new Color(0, 0, 255));
        loginButton.setBackground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        loginButton.setSize(100, 37);
        loginButton.setLocation(100, 302);
        loginButton.addActionListener(this);
        c.add(loginButton);

        resLabel = new JLabel("");
        resLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        resLabel.setSize(500, 25);
        resLabel.setLocation(100, 350);
        c.add(resLabel);

        setVisible(true);
    }

    // Method to define the action to be performed when the login button is clicked
    public void actionPerformed(ActionEvent e) {
        String username = usernameText.getText();
        String password = new String(passwordText.getPassword());
        
     // Inside LoginFrame actionPerformed method
        if (username.equals("admin") && password.equals("12345")) {
            resLabel.setText("Login successful");
            setVisible(false);  // Hide login window
            DashboardFrame dashboard = new DashboardFrame();
            dashboard.setVisible(true);
        } else {
            resLabel.setText("Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}