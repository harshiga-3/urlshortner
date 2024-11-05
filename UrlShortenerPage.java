
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UrlShortenerPage extends JFrame implements ActionListener {

    private JTextField inputLongURL, inputAlias;
    private JTextArea outputArea, historyArea, bulkInputArea, bulkOutputArea;
    private JButton shortenButton, backButton, bulkShortenButton;
    private HashMap<String, String> urlMap = new HashMap<>();
    private ArrayList<String> history = new ArrayList<>();
    private static final String DOMAIN = "https://shortify.com/";
    private int urlCount = 0; // Counter for shortened URLs

    public UrlShortenerPage() {
        setTitle("URL Shortener");
        setSize(600, 600); // Adjusted size for bulk URL section
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Shortify", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        add(titleLabel, BorderLayout.NORTH); // Add title to the top

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Long URL Label and TextField
        JLabel longUrlLabel = new JLabel("Enter Long URL:");
        inputLongURL = new JTextField(25);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(longUrlLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(inputLongURL, gbc);

        // Alias Label and TextField
        JLabel aliasLabel = new JLabel("Enter Alias (optional):");
        inputAlias = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(aliasLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(inputAlias, gbc);

        // Shorten Button
        shortenButton = new JButton("Shorten URL");
        shortenButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(shortenButton, gbc);

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> goBackToShortifyWebPage());
        gbc.gridy = 3; // Position below the Shorten Button
        inputPanel.add(backButton, gbc);

        // Bulk URL Shortening Section
        JLabel bulkUrlLabel = new JLabel("Bulk URL Shortening (one URL per line):");
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(bulkUrlLabel, gbc);

        // Increased size for bulk input
        bulkInputArea = new JTextArea(10, 30); // Increased rows for bulk input
        JScrollPane bulkInputScroll = new JScrollPane(bulkInputArea);
        gbc.gridy = 5;
        inputPanel.add(bulkInputScroll, gbc);

        bulkShortenButton = new JButton("Shorten Bulk URLs");
        bulkShortenButton.addActionListener(this);
        gbc.gridy = 6;
        inputPanel.add(bulkShortenButton, gbc);

        // Output Area
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Shortened URL"));

        // History Area
        historyArea = new JTextArea(10, 30);
        historyArea.setEditable(false);
        historyArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBorder(BorderFactory.createTitledBorder("History"));

        // Bulk Output Area
        bulkOutputArea = new JTextArea(10, 30); // Increased size for bulk output
        bulkOutputArea.setEditable(false);
        bulkOutputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane bulkOutputScroll = new JScrollPane(bulkOutputArea);
        bulkOutputScroll.setBorder(BorderFactory.createTitledBorder("Bulk Shortened URLs"));

        // Adding components to a main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputScroll, BorderLayout.CENTER);
        mainPanel.add(historyScroll, BorderLayout.SOUTH);
        mainPanel.add(bulkOutputScroll, BorderLayout.EAST); // Added bulk output area to the right

        // Adding the main panel to a JScrollPane for overall scrolling
        JScrollPane mainScroll = new JScrollPane(mainPanel); // Add scrolling for the main panel
        add(mainScroll, BorderLayout.CENTER);

        // Styling
        inputPanel.setBackground(Color.LIGHT_GRAY);
        shortenButton.setBackground(Color.BLUE);
        shortenButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.ORANGE);
        backButton.setForeground(Color.WHITE);
        bulkShortenButton.setBackground(Color.BLUE);
        bulkShortenButton.setForeground(Color.WHITE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shortenButton) {
            String longURL = inputLongURL.getText().trim();
            String alias = inputAlias.getText().trim();
            if (urlCount >= 5) {
                // Redirect to UserAuthApp if the count exceeds 5
                new UserAuthApp("For more URLs, please create an account or log in.");
                this.dispose(); // Close the current window
                return;
            }
            if (isValidURL(longURL)) {
                String shortURL = shortenURL(longURL, alias);
                outputArea.setText(shortURL);
                updateHistory(shortURL, longURL); // Update history with new shortened URL
                displayHistory(); // Automatically display history
                urlCount++; // Increment the counter for shortened URLs
            } else {
                outputArea.setText("Invalid URL.");
            }
        } else if (e.getSource() == bulkShortenButton) {
            List<String> longURLs = List.of(bulkInputArea.getText().split("\n"));
            List<String> shortURLs = shortenBatchURLs(longURLs);
            bulkOutputArea.setText(String.join("\n", shortURLs));
        }
    }

    private String shortenURL(String longURL, String alias) {
        String shortURL;
        if (!alias.isEmpty()) {
            // Check for alias conflict
            if (urlMap.containsKey(alias)) {
                return "Alias already exists. Please choose another.";
            }
            shortURL = DOMAIN + alias;
            urlMap.put(alias, longURL);
        } else {
            // Generate a unique 6-character short URL
            shortURL = DOMAIN + generateShortKey();
            urlMap.put(shortURL, longURL);
        }
        return shortURL;
    }

    private List<String> shortenBatchURLs(List<String> longURLs) {
        List<String> shortURLs = new ArrayList<>();
        for (String longURL : longURLs) {
            if (isValidURL(longURL)) {
                shortURLs.add(shortenURL(longURL, "")); // Pass empty string for alias
            } else {
                shortURLs.add("Invalid URL: " + longURL);
            }
        }
        return shortURLs;
    }

    private String generateShortKey() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortKey = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            shortKey.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortKey.toString();
    }

    private boolean isValidURL(String url) {
        try {
            new URI(url); // Keep this method for validation
            return true;
        } catch (URISyntaxException e) {
            return false; // Return false for invalid URIs
        }
    }

    private void updateHistory(String shortURL, String longURL) {
        history.add(shortURL + " -> " + longURL);
    }

    private void displayHistory() {
        StringBuilder sb = new StringBuilder();
        for (String record : history) {
            sb.append(record).append("\n");
        }
        historyArea.setText(sb.toString()); // Automatically update the history area
    }

    private void goBackToShortifyWebPage() {
        new ShortifyWebPage(); // Create an instance of ShortifyWebPage
        this.dispose(); // Close the current window
    }

    public static void main(String[] args) {
        new UrlShortenerPage();
    }
}
