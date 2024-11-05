import java.awt.*;
import java.awt.event.*;

public class ShortifyWebPage extends Frame implements ActionListener, WindowListener {
    Label title, servicesLabel, contactLabel;
    Button urlShortenerButton, loginPageButton, signUpButton; // Added Sign Up button
    ScrollPane scrollPane;

    ShortifyWebPage() {
        setTitle("Company Details - Shortify");

        // Set full-screen size and layout
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Set background color to light blue
        setBackground(new Color(173, 216, 230));

        // Create a panel for content with GridBagLayout
        Panel contentPanel = new Panel();
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        title = new Label("Shortify");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(title, gbc);

        // About Us (Line by Line with separate Labels)
        Label aboutTitle = new Label("About Us:");
        aboutTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPanel.add(aboutTitle, gbc);

        Label aboutLine1 = new Label("Shortify is a revolutionary URL shortening platform.");
        aboutLine1.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPanel.add(aboutLine1, gbc);

        Label aboutLine2 = new Label("We make link sharing fast, simple, and secure.");
        aboutLine2.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPanel.add(aboutLine2, gbc);



        // Services Provided
        servicesLabel = new Label("Services Provided:");
        servicesLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        contentPanel.add(servicesLabel, gbc);

        // Service points
        Label service1 = new Label("1. URL Shortening");
        service1.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        contentPanel.add(service1, gbc);

        Label service2 = new Label("2. Custom Short URLs");
        service2.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        contentPanel.add(service2, gbc);

        Label service3 = new Label("3.Covert Bulk URLs");
        service3.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        contentPanel.add(service3, gbc);

        Label service4 = new Label("4. Redirecting URLs");
        service4.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        contentPanel.add(service4, gbc);

        // Contact Information
        contactLabel = new Label("Contact: info@shortify.com | +91 457 932 7641");
        contactLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        contentPanel.add(contactLabel, gbc);

        // URL Shortener Button
        urlShortenerButton = new Button("Go to URL Shortener");
        urlShortenerButton.setBackground(Color.BLUE);
        urlShortenerButton.setForeground(Color.WHITE);
        urlShortenerButton.setFont(new Font("Arial", Font.BOLD, 18));
        urlShortenerButton.addActionListener(this);
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        contentPanel.add(urlShortenerButton, gbc);

        // Login Page Button
        loginPageButton = new Button("Go to Login Page");
        loginPageButton.setBackground(Color.ORANGE);
        loginPageButton.setForeground(Color.WHITE);
        loginPageButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginPageButton.addActionListener(this);
        gbc.gridy = 12; // Position the login button below the URL shortener button
        contentPanel.add(loginPageButton, gbc);

        // Sign Up Button
        signUpButton = new Button("Sign Up");
        signUpButton.setBackground(Color.GREEN);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        signUpButton.addActionListener(this);
        gbc.gridy = 13; // Position the sign-up button below the login button
        contentPanel.add(signUpButton, gbc);

        // Create ScrollPane for contentPanel
        scrollPane = new ScrollPane();
        scrollPane.add(contentPanel);
        add(scrollPane); // Add scroll pane to the frame

        // Add window listener to handle close button
        addWindowListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == urlShortenerButton) {
            System.out.println("Navigating to URL Shortener Page...");
            dispose(); // Close the company details page
            new UrlShortenerPage(); // Open URL Shortener Page
        } else if (e.getSource() == loginPageButton) {
            System.out.println("Navigating to Login Page...");
            dispose(); // Close the current page
            new Login(); // Open Login Page
        } else if (e.getSource() == signUpButton) {
            System.out.println("Navigating to Sign Up Page...");
            dispose(); // Close the current page
            new UserAuthApp(); // Open Sign Up Page
        }
    }

    public void windowClosing(WindowEvent we) {
        dispose(); // Closes the window
    }

    // Empty methods for other WindowListener events
    public void windowOpened(WindowEvent we) {}
    public void windowClosed(WindowEvent we) {}
    public void windowIconified(WindowEvent we) {}
    public void windowDeiconified(WindowEvent we) {}
    public void windowActivated(WindowEvent we) {}
    public void windowDeactivated(WindowEvent we) {}

    public static void main(String[] args) {
        new ShortifyWebPage();
    }
}
