import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Saved implements ActionListener {
    
    private DefaultListModel<String> savedPromptListModel;
    private JTextField txtFirstStatement;
    private JTextField txtSecondStatement;

    
    public Saved(DefaultListModel<String> savedPromptListModel, JTextField txtFirstStatement, JTextField txtSecondStatement) {
        this.savedPromptListModel = savedPromptListModel;
        this.txtFirstStatement = txtFirstStatement;
        this.txtSecondStatement = txtSecondStatement;
    }
    
    public void actionPerformed(ActionEvent e) {

        String save1 = txtFirstStatement.getText();
        String save2 = txtSecondStatement.getText();
        String newsave2 = save2.replace("-", "");
        String save3 = save1 + " " + newsave2;

        if (!isIdeaAlreadySaved(save3)) {
            savedPromptListModel.addElement(save3);
        } else {
            JOptionPane.showMessageDialog(null, "Idea already saved!");
        }
    }

    private boolean isIdeaAlreadySaved(String idea) {
        for (int i = 0; i < savedPromptListModel.size(); i++) {
            if (savedPromptListModel.getElementAt(i).equals(idea)) {
                return true;
            }
        }
        return false;
    }
    
}
