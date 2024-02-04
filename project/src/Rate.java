import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Rate implements ActionListener {

    private DefaultListModel<String> savedPromptListModel;
    private String ratingsFilePath;
    private JList<String> listSavedPrompt1;

    public Rate(DefaultListModel<String> savedPromptListModel, String ratingsFilePath, JList<String> listSavedPrompt1) {
        this.savedPromptListModel = savedPromptListModel;
        this.ratingsFilePath = ratingsFilePath;
        this.listSavedPrompt1 = listSavedPrompt1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement the functionality for btnRate
        JButton btnRate = (JButton) e.getSource();
        btnRate.setBackground(new Color(228, 93, 88));

        int selectedIndex = listSavedPrompt1.getSelectedIndex();
        if (selectedIndex != -1) {
            String[] options = {"1", "2", "3", "4", "5"};
            int rating = JOptionPane.showOptionDialog(null, "Rate this prompt:", "Rating",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            String selectedPrompt = savedPromptListModel.getElementAt(selectedIndex);
            System.out.println("Rated prompt '" + selectedPrompt + "' with " + (rating + 1) + " stars");

            String ratings = "Rated prompt '" + selectedPrompt + "' with " + (rating + 1) + " stars";

            saveRatingsToFile(ratings, ratingsFilePath);

        } else {
            JOptionPane.showMessageDialog(null, "Please select a prompt to rate.");
        }
    }

    private void saveRatingsToFile(String ratings, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(ratings);
            writer.newLine();
        } catch (IOException ex) {
            System.out.println("Error saving ratings to file");
            ex.printStackTrace();
        }
    }
}