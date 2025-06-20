import java.awt.*;
import javax.swing.*;

public class ATMGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        JLabel accLabel = new JLabel("Account Number:");
        JTextField accField = new JTextField(15);
        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField(15);
        JButton loginBtn = new JButton("Login");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(accLabel, gbc);
        gbc.gridx = 1;
        panel.add(accField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(pinLabel, gbc);
        gbc.gridx = 1;
        panel.add(pinField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(loginBtn, gbc);

        frame.add(panel);
        frame.setVisible(true);

        loginBtn.addActionListener(e -> {
            String acc = accField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();
            Account account = FileStorage.loadAccount(acc, pin);

            if (account != null) {
                frame.dispose();
                new ATMOperations(account); // Your operations GUI
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid account or PIN");
            }
        });
    }
}
