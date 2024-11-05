import java.awt.*;
import java.awt.event.*;

public class UserAuthApp extends Frame implements ActionListener, WindowListener {
    Label title, shortifyTitle, usernameLabel, emailLabel, passwordLabel, messageLabel;
    TextField usernameField, emailField, passwordField;
    Button signupButton, returnButton;

    UserAuthApp() {
        this(""); // Call the constructor that takes a message
    }

    UserAuthApp(String message) {
        setTitle("Sign-Up Page");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setBackground(new Color(173, 216, 230));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        shortifyTitle = new Label("Shortify");
        shortifyTitle.setFont(new Font("Arial", Font.BOLD, 50));
        shortifyTitle.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(shortifyTitle, gbc);

        title = new Label("Sign Up");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.BLUE);
        gbc.gridy = 1;
        add(title, gbc);

        messageLabel = new Label(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 2;
        add(messageLabel, gbc);

        usernameLabel = new Label("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);

        usernameField = new TextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        emailLabel = new Label("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(emailLabel, gbc);

        emailField = new TextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        passwordLabel = new Label("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(passwordLabel, gbc);

        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        gbc.gridx = 1;
        add(passwordField, gbc);

        signupButton = new Button("Sign Up");
        signupButton.setBackground(Color.GREEN);
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Arial", Font.BOLD, 18));
        signupButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(signupButton, gbc);

        returnButton = new Button("Return to Home Page");
        returnButton.setBackground(Color.ORANGE);
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Arial", Font.BOLD, 18));
        returnButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(returnButton, gbc);

        addWindowListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            if (registerUser(username, email, password)) {
                System.out.println("Sign Up Successful for: " + username);
                dispose();
                new Login(); // Make sure this class exists
            } else {
                System.out.println("Sign Up Failed. Username may already exist.");
                messageLabel.setText("Sign Up Failed. Username may already exist.");
            }
        } else if (e.getSource() == returnButton) {
            dispose();
            new Login(); // Change to ShortifyWebPage if applicable
        }
    }

    private boolean registerUser(String username, String email, String password) {
        // Simulate successful registration (replace with actual logic if needed)
        System.out.println("Simulated registration for: " + username);
        return true; // Return true for testing purposes
    }

    @Override
    public void windowClosing(WindowEvent we) {
        dispose();
    }

    @Override
    public void windowOpened(WindowEvent we) {}
    @Override
    public void windowClosed(WindowEvent we) {}
    @Override
    public void windowIconified(WindowEvent we) {}
    @Override
    public void windowDeiconified(WindowEvent we) {}
    @Override
    public void windowActivated(WindowEvent we) {}
    @Override
    public void windowDeactivated(WindowEvent we) {}

    public static void main(String[] args) {
        new UserAuthApp();
    }
}
