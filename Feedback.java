import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Feedback {

    private boolean feedbackProcessed = false; // Tracks whether feedback is processed

    public void showFeedbackDialog() {
        // File path where responses from feedback are stored
        String feedback1FilePath = "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\feedback\\feedback1st.txt";
        String feedback2FilePath = "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\feedback\\feedback2nd.txt";

        JTextField feedbackField1 = new JTextField();
        JTextField feedbackField2 = new JTextField();
        Object[] dialogContent = {
                "First Statement", feedbackField1,
                "Second Statement", feedbackField2
        };

        int option = JOptionPane.showConfirmDialog(null, dialogContent, "Feedback", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Process user input if OK is clicked
        if (option == JOptionPane.OK_OPTION) {
            String userFeedback1 = feedbackField1.getText();
            String userFeedback2 = feedbackField2.getText();
            processFeedback(userFeedback1, feedback1FilePath);
            processFeedback(userFeedback2, feedback2FilePath);
        }
    }

    private void processFeedback(String feedback, String filePath) {
        System.out.println("User Feedback: " + feedback);

        saveFeedbackToFile(feedback, filePath);

        if (!feedbackProcessed) {
            JOptionPane.showMessageDialog(null, "Thank you for your feedback!", "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);
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