import java.io.*;
import java.util.*;

public class FileStorage {
    private static final String FILE_PATH = "data/accounts.txt";

    public static Account loadAccount(String accountNumber, String pin) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(accountNumber) && parts[1].equals(pin)) {
                    return new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return null;
    }

    public static void saveAccount(Account account) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(account.getAccountNumber() + ",")) {
                    lines.add(account.getAccountNumber() + "," + account.getPin() + "," + account.getBalance());
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String l : lines) writer.write(l + "\n");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}
