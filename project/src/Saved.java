import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Saved implements ActionListener {
    
    private DefaultListModel<String> savedPromptListModel;  // Model to store saved prompts
    private JTextField txtFirstStatement;  // JTextField for the first statement
    private JTextField txtSecondStatement;  // JTextField for the second statement

    // Constructor to initialize the Saved object with necessary components
    public Saved(DefaultListModel<String> savedPromptListModel, JTextField txtFirstStatement, JTextField txtSecondStatement) {
        this.savedPromptListModel = savedPromptListModel;
        this.txtFirstStatement = txtFirstStatement;
        this.txtSecondStatement = txtSecondStatement;
    }
    
    // ActionListener implementation for handling button click events
    public void actionPerformed(ActionEvent e) {
        // Get text from the two JTextField components
        String save1 = txtFirstStatement.getText();
        String save2 = txtSecondStatement.getText();

        // Replace '-' characters in the second statement
        String newsave2 = save2.replace("-", "");
        
        // Combine the first and modified second statements
        String save3 = save1 + " " + newsave2;

        // Check if the idea is not already saved, then add it to the model
        if (!isIdeaAlreadySaved(save3)) {
            savedPromptListModel.addElement(save3);
        } else {
            // Show a message if the idea is already saved
            JOptionPane.showMessageDialog(null, "Idea already saved!");
        }
    }

    // Private method to check if an idea is already saved
    private boolean isIdeaAlreadySaved(String idea) {
        // Iterate through the saved prompts and compare with the new idea
        for (int i = 0; i < savedPromptListModel.size(); i++) {
            if (savedPromptListModel.getElementAt(i).equals(idea)) {
                return true;  // Idea is already saved
            }
        }
        return false;  // Idea is not already saved
    }
}
