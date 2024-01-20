import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Feedback {

    private boolean feedbackProcessed = false; // Tracks whether feedback is processed

    public void showFeedbackDialog() {
        feedbackProcessed = false;

        // File path where responses from feedback are stored
        String feedback1FilePath = "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\feedback\\feedback1st.txt";
        String feedback2FilePath = "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\feedback\\feedback2nd.txt";

        JTextField feedbackField1 = new JTextField();
        JTextField feedbackField2 = new JTextField();

        // Customize colors and font styles
        feedbackField1.setBackground(Color.WHITE);
        feedbackField2.setBackground(Color.WHITE);
        feedbackField1.setForeground(Color.BLACK); // Text color black
        feedbackField2.setForeground(Color.BLACK); // Text color black
        feedbackField1.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Font style SansSerif
        feedbackField2.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Font style SansSerif

        JLabel label1 = new JLabel("First Statement (Create/Design...)");
        JLabel label2 = new JLabel("Second Statement (for)");

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);

        Object[] dialogContent = {
            label1, feedbackField1,
            label2, feedbackField2
        };

        // Set the background color and button color
        UIManager.put("OptionPane.background", new Color(99, 188, 229));
        UIManager.put("Panel.background", new Color(99, 188, 229));
        UIManager.put("Button.background", new Color(40, 85, 154)); // Button color
        UIManager.put("Button.foreground", Color.WHITE); // Text color

        int option = JOptionPane.showConfirmDialog(null, dialogContent, "Feedback", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Process user input if OK is clicked
        if (option == JOptionPane.OK_OPTION) {
            String userFeedback1 = feedbackField1.getText();
            String userFeedback2 = feedbackField2.getText();
            processFeedback(userFeedback1, feedback1FilePath);
            processFeedback(userFeedback2, feedback2FilePath);
        }

        // Reset the colors to default
        UIManager.put("OptionPane.background", UIManager.get("OptionPane.messageBackground"));
        UIManager.put("Panel.background", UIManager.get("OptionPane.background"));
        UIManager.put("Button.background", UIManager.get("Button.defaultButtonBackground"));
        UIManager.put("Button.foreground", UIManager.get("Button.foreground"));
    }

    private void processFeedback(String feedback, String filePath) {
        System.out.println("User Feedback: " + feedback);

        saveFeedbackToFile(feedback, filePath);

        if (!feedbackProcessed) {
            // Set the background color for the message dialog
            UIManager.put("OptionPane.background", new Color(99, 188, 229));
            UIManager.put("Panel.background", new Color(99, 188, 229));
            UIManager.put("OptionPane.messageForeground", Color.WHITE); // Message text color

            JOptionPane.showMessageDialog(null, "Thank you for your feedback!", "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);

            // Reset the colors to default
            UIManager.put("OptionPane.background", UIManager.get("OptionPane.messageBackground"));
            UIManager.put("Panel.background", UIManager.get("OptionPane.background"));
            UIManager.put("OptionPane.messageForeground", UIManager.get("OptionPane.messageForeground"));

            feedbackProcessed = true;
        }
    }

    private void saveFeedbackToFile(String feedback, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(feedback);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving feedback to file");
            e.printStackTrace();
        }
    }
}