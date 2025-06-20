import javax.swing.*;

public class ATMOperations extends JFrame {
    private Account account;

    public ATMOperations(Account account) {
        this.account = account;

        setTitle("ATM Operations - Account " + account.getAccountNumber());
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton balanceBtn = new JButton("Check Balance");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton exitBtn = new JButton("Exit");

        balanceBtn.setBounds(20, 20, 150, 30);
        depositBtn.setBounds(20, 60, 150, 30);
        withdrawBtn.setBounds(200, 20, 150, 30);
        exitBtn.setBounds(200, 60, 150, 30);

        add(balanceBtn);
        add(depositBtn);
        add(withdrawBtn);
        add(exitBtn);

        balanceBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Balance: ₹" + account.getBalance()));

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter amount to deposit:");
            try {
                double amt = Double.parseDouble(input);
                if (amt > 0) {
                    account.deposit(amt);
                    JOptionPane.showMessageDialog(this, "Deposited ₹" + amt);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number.");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
            try {
                double amt = Double.parseDouble(input);
                if (amt > 0 && amt <= account.getBalance()) {
                    account.withdraw(amt);
                    JOptionPane.showMessageDialog(this, "Withdrawn ₹" + amt);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance or invalid amount.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number.");
            }
        });

        exitBtn.addActionListener(e -> {
            FileStorage.saveAccount(account);
            JOptionPane.showMessageDialog(this, "Account saved. Goodbye!");
            dispose();
        });

        setVisible(true);
    }
}
