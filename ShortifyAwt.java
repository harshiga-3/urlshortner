import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ShortifyAwt extends Frame implements ActionListener {
    private TextField inputLongURL, inputCustomAlias, outputShortURL, inputShortURL;
    private TextArea bulkInputURLs, bulkOutputURLs, urlHistory;
    private Button shortenButton, customAliasButton, bulkShortenButton, viewURLsButton, clearURLsButton, expandURLButton;
    private static final String DOMAIN = "https://shortify.com/";
    private HashMap<String, String> urlMap = new HashMap<>();
    private HashMap<String, String> reverseMap = new HashMap<>();
    private HashMap<String, Long> expirationMap = new HashMap<>();
    private ArrayList<String> history = new ArrayList<>();
    private int id = 100000;

    public ShortifyAwt() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        // Title
        Label title = new Label("Shortify - URL Shortener");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        // Long URL Label
        Label longUrlLabel = new Label("Enter Long URL:");
        longUrlLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(longUrlLabel, gbc);

        // Long URL input field
        inputLongURL = new TextField(30);
        gbc.gridx = 1;
        add(inputLongURL, gbc);

        // Custom Alias Label
        Label customAliasLabel = new Label("Custom Alias (Optional):");
        customAliasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(customAliasLabel, gbc);

        // Custom Alias input field
        inputCustomAlias = new TextField(20);
        gbc.gridx = 1;
        add(inputCustomAlias, gbc);

        // Shorten URL button
        shortenButton = new Button("Shorten URL");
        shortenButton.setFont(new Font("Arial", Font.BOLD, 12));
        shortenButton.setBackground(Color.BLUE);
        shortenButton.setForeground(Color.WHITE);
        shortenButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(shortenButton, gbc);

        // Custom Alias button
        customAliasButton = new Button("Shorten with Alias");
        customAliasButton.setFont(new Font("Arial", Font.BOLD, 12));
        customAliasButton.setBackground(Color.BLUE);
        customAliasButton.setForeground(Color.WHITE);
        customAliasButton.addActionListener(this);
        gbc.gridx = 1;
        add(customAliasButton, gbc);

        // Shortened URL Label
        Label shortUrlLabel = new Label("Shortened URL:");
        shortUrlLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(shortUrlLabel, gbc);

        // Output shortened URL field
        outputShortURL = new TextField(30);
        outputShortURL.setEditable(false);
        gbc.gridx = 1;
        add(outputShortURL, gbc);

        // Expand URL Label
        Label expandUrlLabel = new Label("Enter Short URL to Expand:");
        expandUrlLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(expandUrlLabel, gbc);

        // Short URL input field
        inputShortURL = new TextField(30);
        gbc.gridx = 1;
        add(inputShortURL, gbc);

        // Expand URL button
        expandURLButton = new Button("Expand URL");
        expandURLButton.setFont(new Font("Arial", Font.BOLD, 12));
        expandURLButton.setBackground(Color.GREEN);
        expandURLButton.setForeground(Color.WHITE);
        expandURLButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(expandURLButton, gbc);

        // Bulk URL Label
        Label bulkUrlLabel = new Label("Bulk URL Shortening (Enter multiple URLs, each on a new line):");
        bulkUrlLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(bulkUrlLabel, gbc);

        // Bulk URL input field
        bulkInputURLs = new TextArea(5, 30);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(bulkInputURLs, gbc);

        // Bulk shorten button
        bulkShortenButton = new Button("Shorten All URLs");
        bulkShortenButton.setFont(new Font("Arial", Font.BOLD, 12));
        bulkShortenButton.setBackground(Color.BLUE);
        bulkShortenButton.setForeground(Color.WHITE);
        bulkShortenButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(bulkShortenButton, gbc);

        // Bulk Output shortened URLs field
        bulkOutputURLs = new TextArea(5, 30);
        bulkOutputURLs.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        add(bulkOutputURLs, gbc);

        // View URLs button
        viewURLsButton = new Button("View All Shortened URLs");
        viewURLsButton.setFont(new Font("Arial", Font.BOLD, 12));
        viewURLsButton.setBackground(Color.BLUE);
        viewURLsButton.setForeground(Color.WHITE);
        viewURLsButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 11;
        add(viewURLsButton, gbc);

        // Clear URLs button
        clearURLsButton = new Button("Clear All URLs");
        clearURLsButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearURLsButton.setBackground(Color.RED);
        clearURLsButton.setForeground(Color.WHITE);
        clearURLsButton.addActionListener(this);
        gbc.gridx = 1;
        add(clearURLsButton, gbc);

        // URL History Label
        Label historyLabel = new Label("Shortened URL History:");
        historyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        add(historyLabel, gbc);

        // URL History TextArea
        urlHistory = new TextArea(5, 30);
        urlHistory.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        add(urlHistory, gbc);

        // Frame properties
        setSize(600, 700);
        setTitle("Shortify - URL Shortener");
        setBackground(new Color(220, 240, 255)); // Light blue background
        setVisible(true);

        // Window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    // Action handling for buttons
    public void actionPerformed(ActionEvent ae) {
        String longURL = inputLongURL.getText().trim();
        String customAlias = inputCustomAlias.getText().trim();
        String shortURL;

        if (ae.getSource() == shortenButton) {
            if (longURL.isEmpty() || !isValidURL(longURL)) {
                outputShortURL.setText("Please enter a valid URL.");
            } else {
                shortURL = shortenURL(longURL);
                outputShortURL.setText(shortURL);
                history.add(shortURL); // Track shortened URL
                updateHistory();
            }
        } else if (ae.getSource() == customAliasButton) {
            if (customAlias.isEmpty() || !isValidURL(longURL)) {
                outputShortURL.setText("Please enter a valid URL and custom alias.");
            } else {
                shortURL = shortenURLWithCustomAlias(longURL, customAlias);
                outputShortURL.setText(shortURL);
                history.add(shortURL); // Track shortened URL
                updateHistory();
            }
        } else if (ae.getSource() == bulkShortenButton) {
            java.util.List<String> longURLs = java.util.Arrays.asList(bulkInputURLs.getText().split("\n"));
            java.util.List<String> shortURLs = shortenBatchURLs(longURLs);
            bulkOutputURLs.setText(String.join("\n", shortURLs));
        } else if (ae.getSource() == viewURLsButton) {
            viewAllShortenedURLs();
        } else if (ae.getSource() == clearURLsButton) {
            clearAllURLs();
        } else if (ae.getSource() == expandURLButton) {
            String shortURLToExpand = inputShortURL.getText().trim();
            if (reverseMap.containsKey(shortURLToExpand)) {
                String originalURL = reverseMap.get(shortURLToExpand);
                JOptionPane.showMessageDialog(this, "Original URL: " + originalURL);
            } else {
                JOptionPane.showMessageDialog(this, "Short URL does not exist.");
            }
        }
    }

    // Shorten URL without custom alias
    private String shortenURL(String longURL) {
        if (reverseMap.containsKey(longURL)) {
            return reverseMap.get(longURL);
        }
        String shortURL = DOMAIN + idToShortURL(id++);
        urlMap.put(shortURL, longURL);
        reverseMap.put(longURL, shortURL);
        return shortURL;
    }

    // Shorten URL with custom alias
    private String shortenURLWithCustomAlias(String longURL, String customAlias) {
        if (!isValidAlias(customAlias)) {
            return "Custom alias can only contain alphanumeric characters.";
        }
        if (urlMap.containsKey(DOMAIN + customAlias)) {
            return "Custom alias already taken. Please choose another.";
        }
        urlMap.put(DOMAIN + customAlias, longURL);
        reverseMap.put(longURL, DOMAIN + customAlias);
        return DOMAIN + customAlias;
    }

    // Shorten batch URLs
    private java.util.List<String> shortenBatchURLs(java.util.List<String> longURLs) {
        java.util.List<String> shortURLs = new ArrayList<>();
        for (String longURL : longURLs) {
            if (isValidURL(longURL)) {
                shortURLs.add(shortenURL(longURL));
            } else {
                shortURLs.add("Invalid URL: " + longURL);
            }
        }
        return shortURLs;
    }

    // Validate URL
    private boolean isValidURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    // Validate custom alias
    private boolean isValidAlias(String alias) {
        return alias.matches("^[a-zA-Z0-9]+$"); // Only alphanumeric characters
    }

    // View all shortened URLs
    private void viewAllShortenedURLs() {
        StringBuilder sb = new StringBuilder("Shortened URLs:\n");
        for (String shortURL : urlMap.keySet()) {
            sb.append(shortURL).append(" -> ").append(urlMap.get(shortURL)).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    // Clear all URLs
    private void clearAllURLs() {
        urlMap.clear();
        reverseMap.clear();
        history.clear();
        updateHistory();
        JOptionPane.showMessageDialog(this, "All URLs have been cleared.");
    }

    // Update history display
    private void updateHistory() {
        StringBuilder sb = new StringBuilder();
        for (String url : history) {
            sb.append(url).append("\n");
        }
        urlHistory.setText(sb.toString());
    }

    // Convert integer ID to Base62
    private String idToShortURL(int n) {
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder shortURL = new StringBuilder();
        while (n > 0) {
            shortURL.append(map[n % 62]);
            n /= 62;
        }
        return shortURL.reverse().toString(); // Reverse to get the correct order
    }

    // Main method to run the application
    public static void main(String[] args) {
        new ShortifyAwt();
    }
}
