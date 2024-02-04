import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Rate implements ActionListener {
    
    private DefaultListModel<String> savedPromptListModel;  // Model to store saved prompts
    private String ratingsFilePath;  // File path to store ratings
    private JList<String> listSavedPrompt1;  // JList to display saved prompts

    // Constructor to initialize the Rate object with necessary components
    public Rate(DefaultListModel<String> savedPromptListModel, String ratingsFilePath, JList<String> listSavedPrompt1) {
        this.savedPromptListModel = savedPromptListModel;
        this.ratingsFilePath = ratingsFilePath;
        this.listSavedPrompt1 = listSavedPrompt1;
    }

    // ActionListener implementation for handling button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement the functionality for btnRate
        JButton btnRate = (JButton) e.getSource();
        btnRate.setBackground(new Color(228, 93, 88));  // Change button background color

        int selectedIndex = listSavedPrompt1.getSelectedIndex();  // Get the selected index from JList

        if (selectedIndex != -1) {  // Check if a prompt is selected
            String[] options = {"1", "2", "3", "4", "5"};
            // Show a dialog box to get user rating for the selected prompt
            int rating = JOptionPane.showOptionDialog(null, "Rate this prompt:", "Rating",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String selectedPrompt = savedPromptListModel.getElementAt(selectedIndex);
            System.out.println("Rated prompt '" + selectedPrompt + "' with " + (rating + 1) + " stars");

            String ratings = "Rated prompt '" + selectedPrompt + "' with " + (rating + 1) + " stars";

            // Save the ratings to the specified file
            saveRatingsToFile(ratings, ratingsFilePath);

        } else {
            JOptionPane.showMessageDialog(null, "Please select a prompt to rate.");
        }
    }

    // Private method to save ratings to a file
    private void saveRatingsToFile(String ratings, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(ratings);  // Write ratings to file
            writer.newLine();  // Move to the next line
        } catch (IOException ex) {
            System.out.println("Error saving ratings to file");
            ex.printStackTrace();  // Print stack trace in case of an exception
        }
    }
}
