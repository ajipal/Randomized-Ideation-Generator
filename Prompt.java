import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Prompt {
    
    // Lists to store phrases from files
    private List<String> firstStatementPhrases = new ArrayList<>();
    private List<String> secondStatementPhrases = new ArrayList<>();
    
    //sentinels
    private boolean lock1 = false;
    private boolean lock2 = false;

    public Prompt(String firstFilePath, String secondFilePath) {
        getPhrases(firstFilePath, firstStatementPhrases);
        getPhrases(secondFilePath, secondStatementPhrases);
    }
    
    private void getPhrases(String fileName, List<String> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            e.printStackTrace();    //not necessary
        }
    }

    public void generateIdeas(JTextField txt1, JTextField txt2) {
        Random random = new Random();

        // Randomly select phrases from the lists
        if (!firstStatementPhrases.isEmpty() && !secondStatementPhrases.isEmpty()) {
            String firstIdea = firstStatementPhrases.get(random.nextInt(firstStatementPhrases.size()));
            String secondIdea = secondStatementPhrases.get(random.nextInt(secondStatementPhrases.size()));

            // Set the text of the text fields
            if (lock1 && !lock2) {
                txt2.setText(secondIdea);
            } 
            else if (!lock1 && lock2) {
                txt1.setText(firstIdea);
            } 
            else if (lock1 && lock2) {
                
            }
            else  {
                txt1.setText(firstIdea);
                txt2.setText(secondIdea);
            }
        } else {
            System.out.println("List is empty.");
        }
        
    }

    public void toggleLock1() {
        lock1 = !lock1;
    }

    public void toggleLock2() {
        lock2 = !lock2;
    }

    public void toggleLock(JTextField textField) {
        boolean currentState = textField.isEnabled();
        textField.setEnabled(!currentState);
    }
}
