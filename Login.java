import java.awt.*;
import java.awt.event.*;

public class Login extends Frame implements ActionListener, WindowListener {
    Label title, usernameLabel, passwordLabel;
    TextField usernameField, passwordField;
    Button loginButton, backButton;

    Login() {
        setTitle("Login Page");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setBackground(new Color(173, 216, 230));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        title = new Label("Shortify");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        usernameLabel = new Label("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);

        usernameField = new TextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        passwordLabel = new Label("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        gbc.gridx = 1;
        add(passwordField, gbc);

        loginButton = new Button("Login");
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(loginButton, gbc);

        backButton = new Button("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> {
            new UserAuthApp("For more URLs, please create an account or log in.");
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(backButton, gbc);

        addWindowListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            System.out.println("Login Successful for: " + username);
            new ShortifyWebPage(); // Ensure this class is defined
            dispose();
        }
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
        new Login();
    }
}
