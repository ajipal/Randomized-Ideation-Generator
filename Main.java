import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JFrame implements ActionListener {

    // File declaration
    static BufferedReader openFirst, openSecond;

    // Components
    JButton btn1 = new JButton("Lock 1");
    JButton btn2 = new JButton("Lock 2");
    JButton btn3 = new JButton("Generate");
    JTextField txt1 = new JTextField("first statement");
    JTextField txt2 = new JTextField("second statement");

    // Lists to store phrases from files
    List<String> firstStatementPhrases = new ArrayList<>();
    List<String> secondStatementPhrases = new ArrayList<>();

    public Main() {
        // Frame settings
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500, 500);

        // Add the components to the frame
        add(btn1);
        add(txt1);
        add(btn2);
        add(txt2);
        add(btn3);

        // Add action listeners
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);

        // Exit the program
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Load phrases from files
        // Change the directory files
        loadPhrases("C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\firststatement.txt", firstStatementPhrases);
        loadPhrases("C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\secondstatement.txt", secondStatementPhrases);
    }

    //open the files
    private void loadPhrases(String fileName, List<String> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        // Generator button
        if (e.getSource() == btn3) {
            generateIdeas();
        }

        // First statement lock button
        else if (e.getSource() == btn1) {
            toggleLock(txt1);
        }

        // Second statement lock button
        else if (e.getSource() == btn2) {
            toggleLock(txt2);
        }
    }

    private void generateIdeas() {
        Random random = new Random();

        // Randomly select phrases from the lists
        String firstIdea = firstStatementPhrases.get(random.nextInt(firstStatementPhrases.size()));
        String secondIdea = secondStatementPhrases.get(random.nextInt(secondStatementPhrases.size()));

        // Set the text of the text fields
        txt1.setText(firstIdea);
        txt2.setText(secondIdea);
    }

    private void toggleLock(JTextField textField) {
        boolean currentState = textField.isEnabled();
        textField.setEnabled(!currentState);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
