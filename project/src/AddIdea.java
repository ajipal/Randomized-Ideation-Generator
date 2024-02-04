import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddIdea {

    private JPanel parentPanel; // Reference to the parent panel
    private JTextField ideaField1;
    private JTextField ideaField2;

    public void showIdeaDialog() {
        // Update the file paths as per your requirement
        String idea1FilePath = "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\Add Prompt Ideas\\Idea1.txt";
        String idea2FilePath = "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\Add Prompt Ideas\\Idea2.txt";

        parentPanel = new JPanel(new GridLayout(4, 1));
        parentPanel.setBackground(new Color(36, 40, 52)); // Set background color

        ideaField1 = new JTextField();
        ideaField2 = new JTextField();

        JButton ideaButton1 = createButton("Add Idea for Statement 1", idea1FilePath, ideaField1);
        JButton ideaButton2 = createButton("Add Idea for Statement 2", idea2FilePath, ideaField2);

        JLabel label1 = new JLabel("First Statement (Create/Design...)");
        JLabel label2 = new JLabel("Second Statement (for)");

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);

        parentPanel.add(label1);
        parentPanel.add(ideaButton1);
        parentPanel.add(label2);
        parentPanel.add(ideaButton2);

        int option = JOptionPane.showConfirmDialog(null, parentPanel, "Add Idea", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            saveIdeaOnOK(ideaButton1, ideaField1);
            saveIdeaOnOK(ideaButton2, ideaField2);
            JOptionPane.showMessageDialog(null, "Thank you for adding input!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private JButton createButton(String buttonText, String filePath, JTextField ideaField) {
        JButton button = new JButton(buttonText);
        customizeButton(button);
        button.addActionListener(new ButtonToTextFieldListener(button, filePath, ideaField));
        return button;
    }

    private void customizeButton(JButton button) {
        button.setBackground(new Color(228, 93, 88));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
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

        public ButtonToTextFieldListener(JButton button, String filePath, JTextField ideaField) {
            this.button = button;
            this.filePath = filePath;
            this.ideaField = ideaField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            customizeTextField(ideaField);

            int index = parentPanel.getComponentZOrder(button);

            parentPanel.remove(button);
            parentPanel.add(ideaField, index);

            // Adjust panel size
            parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight() + 30));

            parentPanel.revalidate();
            parentPanel.repaint();

            ideaField.requestFocus();

            ideaField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String userIdea = ideaField.getText().trim(); // Trim leading and trailing spaces

                    // Switch back to the button
                    parentPanel.remove(ideaField);
                    parentPanel.add(button, index);

                    // Adjust panel size
                    parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), parentPanel.getHeight() - 30));

                    parentPanel.revalidate();
                    parentPanel.repaint();

                    if (!userIdea.isEmpty()) { // Check if the input is not empty
                        saveIdeaToFile(userIdea, filePath);
                    }
                }
            });
        }

        private void customizeTextField(JTextField textField) {
            textField.setBackground(Color.WHITE);
            textField.setForeground(Color.BLACK);
            textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
    }

    private void saveIdeaOnOK(JButton button, JTextField ideaField) {
        String userIdea = ideaField.getText().trim();
        if (!userIdea.isEmpty()) {
            saveIdeaToFile(userIdea, ((ButtonToTextFieldListener) button.getActionListeners()[0]).filePath);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddIdea().showIdeaDialog();
        });
    }
}