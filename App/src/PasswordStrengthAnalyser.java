import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class PasswordStrengthAnalyser {

    private static final Pattern UPPER = Pattern.compile("[A-Z]");
    private static final Pattern LOWER = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");

    public static String analyzePassword(String password) {
        int score = 0;

        if (password.length() >= 12) score += 2;
        else if (password.length() >= 8) score += 1;

        if (UPPER.matcher(password).find()) score++;
        if (LOWER.matcher(password).find()) score++;
        if (DIGIT.matcher(password).find()) score++;
        if (SPECIAL.matcher(password).find()) score++;

        if (score >= 6) return "🔥 Very Strong";
        else if (score >= 4) return "💪 Strong";
        else if (score >= 3) return "⚠️ Moderate";
        else return "❌ Weak";
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("🔐 Password Strength Analyzer");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton checkButton = new JButton("Check Strength");
        JLabel resultLabel = new JLabel(" ");

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                String result = analyzePassword(password);
                resultLabel.setText("Strength: " + result);
            }
        });

        frame.add(label);
        frame.add(passwordField);
        frame.add(checkButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }
}