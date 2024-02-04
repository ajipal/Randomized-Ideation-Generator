import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Prompt {
    
    // Lists to store phrases from files
    private List<String> firstStatementPhrases = new ArrayList<>();
    private List<String> secondStatementPhrases = new ArrayList<>();
    
    // Sentinels/flags to control randomness and locking of text fields
    private boolean lock1 = false;
    private boolean lock2 = false;

    // Open the file and get the phrases
    public Prompt(String firstFilePath, String secondFilePath) {
        getPhrases(firstFilePath, firstStatementPhrases);
        getPhrases(secondFilePath, secondStatementPhrases);
    }
    
    // Copy the phrases from the file to the list
    private void getPhrases(String fileName, List<String> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            e.printStackTrace();  // Print stack trace
        }
    }

    // Generate random ideas and update text fields accordingly
    public void generateIdeas(JTextField txtFirstStatement, JTextField txtSecondStatement) {
        Random random = new Random();

        // Check if the phrase lists are not empty
        if (!firstStatementPhrases.isEmpty() && !secondStatementPhrases.isEmpty()) {
            // Randomly select phrases from the lists
            String firstIdea = firstStatementPhrases.get(random.nextInt(firstStatementPhrases.size()));
            String secondIdea = secondStatementPhrases.get(random.nextInt(secondStatementPhrases.size()));

            // Set the text of the text fields based on the lock state
            if (lock1 && !lock2) {
                txtSecondStatement.setText(secondIdea);
            } 
            else if (!lock1 && lock2) {
                txtFirstStatement.setText(firstIdea);
            } 
            else if (lock1 && lock2) {
                // Do nothing if both locks are active
            }
            else  {
                txtFirstStatement.setText(firstIdea);
                txtSecondStatement.setText(secondIdea);
            }
        } else {
            System.out.println("List is empty.");
        }
    }

    // Toggle the state of lock1
    public void toggleLock1() {
        lock1 = !lock1;
    }

    // Toggle the state of lock2
    public void toggleLock2() {
        lock2 = !lock2;
    }

    // Check if lock1 is active
    public boolean isLock1() {
        return lock1;
    }

    // Check if lock2 is active
    public boolean isLock2() {
        return lock2;
    }

    // Toggle the state of locking for a specific JTextField
    public void toggleLock(JTextField textField) {
        boolean currentState = textField.isEnabled();
        textField.setEnabled(!currentState);
    }
}
