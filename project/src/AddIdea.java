import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddIdea {

    private JPanel parentPanel; 
    private JTextField ideaField1;
    private JTextField ideaField2;
    private JButton revertButton1;
    private JButton revertButton2;

    public void showIdeaDialog() {
        // File paths where ideas will be saved.
        String idea1FilePath = "Add Prompt Ideas\\Idea1.txt";
        String idea2FilePath = "Add Prompt Ideas\\Idea2.txt";

        parentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20)); 
        parentPanel.setBackground(new Color(36, 40, 52)); 
        parentPanel.setPreferredSize(new Dimension(575, 125));

        // IdeaField Sizes
        ideaField1 = new JTextField(20);
        ideaField2 = new JTextField(20);
        ideaField1.setPreferredSize(new Dimension(ideaField1.getPreferredSize().width, 40));
        ideaField2.setPreferredSize(new Dimension(ideaField2.getPreferredSize().width, 40));

        JButton ideaButton1 = createButton("Add Idea for Statement 1", idea1FilePath, ideaField1);
        JButton ideaButton2 = createButton("Add Idea for Statement 2", idea2FilePath, ideaField2);

        JLabel label1 = new JLabel("First Statement (Create/Design...)");
        JLabel label2 = new JLabel("Second Statement (for)                "); //Spaces added to align textfields

        customizeLabel(label1);
        customizeLabel(label2);

        parentPanel.add(label1);
        parentPanel.add(ideaButton1);

        parentPanel.add(label2);
        parentPanel.add(ideaButton2);

        int option = JOptionPane.showConfirmDialog(null, parentPanel, "Add Idea", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            saveIdeaOnOK(ideaButton1, ideaField1, revertButton1);
            saveIdeaOnOK(ideaButton2, ideaField2, revertButton2);
            JOptionPane.showMessageDialog(null, "Thank you for adding input!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private JButton createButton(String buttonText, String filePath, JTextField ideaField) {
        JButton button = new JButton(buttonText);
        customizeButton(button);
        button.addActionListener(new ButtonToTextFieldListener(button, filePath, ideaField));
        return button;
    }

    private JButton createRevertButton(JButton originalButton, JTextField ideaField) {
        JButton revertButton = new JButton("X");
        customizeRevertButton(revertButton);
        revertButton.setEnabled(true);
        revertButton.addActionListener(e -> {
            int originalIndex = parentPanel.getComponentZOrder(ideaField);
            parentPanel.remove(ideaField);
            parentPanel.add(originalButton, originalIndex);
            parentPanel.remove(revertButton);
            parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight() - 30));
            parentPanel.revalidate();
            parentPanel.repaint();
            
            // Reset text field to empty when revert is clicked
            ideaField.setText("");
        });
        return revertButton;
    }
    

    private void customizeButton(JButton button) {
        button.setBackground(new Color(228, 93, 88));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setFocusable(false);
    }

    private void customizeRevertButton(JButton revertButton) {
        revertButton.setPreferredSize(new Dimension(50, 30));
        revertButton.setBackground(Color.RED);
        revertButton.setForeground(Color.WHITE);
        revertButton.setFocusable(false);
    }

    private void customizeLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
    }

    private void saveIdeaToFile(String idea, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(idea);
            writer.newLine();
            System.out.println("Idea saved to file: " + idea);
        } catch (IOException ex) {
            System.out.println("Error saving idea to file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private class ButtonToTextFieldListener implements ActionListener {

        private JButton button;
        private String filePath;
        private JTextField ideaField;
        private JButton revertButton;

        public ButtonToTextFieldListener(JButton button, String filePath, JTextField ideaField) {
            this.button = button;
            this.filePath = filePath;
            this.ideaField = ideaField;
            this.revertButton = createRevertButton(button, ideaField);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            customizeTextField(ideaField);

            int index = parentPanel.getComponentZOrder(button);

            parentPanel.remove(button);
            parentPanel.add(ideaField, index);
            parentPanel.add(revertButton, index + 1);

            parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight() + 30));

            parentPanel.revalidate();
            parentPanel.repaint();

            ideaField.requestFocus();
        }

        private void customizeTextField(JTextField textField) {
            textField.setBackground(Color.WHITE);
            textField.setForeground(Color.BLACK);
            textField.setFont(new Font("SansSerif", Font.PLAIN, 14));

            }
        }
    

    private void saveIdeaOnOK(JButton button, JTextField ideaField, JButton revertButton) {
        String userIdea = ideaField.getText().trim();
        if (!userIdea.isEmpty()) {
            saveIdeaToFile(userIdea, ((ButtonToTextFieldListener) button.getActionListeners()[0]).filePath);
            if (revertButton != null) {
                revertButton.setEnabled(true);
            }
        } else {
            if (revertButton != null) {
                revertButton.setEnabled(false);
            }
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddIdea().showIdeaDialog();
        });
    }
}
