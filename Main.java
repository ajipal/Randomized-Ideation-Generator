import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    // Components
    private JButton btnLock1 = new JButton("Lock 1");
    private JButton btnLock2 = new JButton("Lock 2");
    private JButton btnGenerate = new JButton("Generate");
    private JButton btnFeedback = new JButton("Add");
    private JTextField txtFirstStatement = new JTextField("first statement");
    private JTextField txtSecondStatement = new JTextField("second statement");

    //Instantiate Prompt class
    private Prompt prompt = new Prompt("C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\firststatement.txt",
                               "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\secondstatement.txt");

    private Feedback feedback = new Feedback();

    public Main() {
        // Frame settings
        setLayout(null);
        setVisible(true);
        setSize(500, 300);
        setResizable(false);
        txtFirstStatement.setEditable(false);
        txtSecondStatement.setEditable(false);

        // Add the components to the frame
        add(btnLock1);
        add(txtFirstStatement);
        add(btnLock2);
        add(txtSecondStatement);
        add(btnGenerate);
        add(btnFeedback);

        // Add action listeners
        btnLock1.addActionListener(this);
        btnLock2.addActionListener(this);
        btnGenerate.addActionListener(this);
        btnFeedback.addActionListener(this);

        // Set Bounds for the Components
        btnLock1.setBounds(25, 60, 80, 20);
        txtFirstStatement.setBounds(115, 55, 350, 30);
        btnLock2.setBounds(25, 105, 80, 20);
        txtSecondStatement.setBounds(115, 100, 350, 30);
        btnGenerate.setBounds(210, 140, 90, 20);
        btnFeedback.setBounds(310, 140, 90, 20);

        // Exit the program
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        // Generator button
        if (e.getSource() == btnGenerate) {
            prompt.generateIdeas(txtFirstStatement, txtSecondStatement);
        }

        // First statement lock button
        else if (e.getSource() == btnLock1) {
            prompt.toggleLock1();
            prompt.toggleLock(txtFirstStatement);
        }

        // Second statement lock button
        else if (e.getSource() == btnLock2) {
            prompt.toggleLock2();
            prompt.toggleLock(txtSecondStatement);
        }

        // Feedback Button
        else if (e.getSource() == btnFeedback) {
            // Call the showFeedbackDialog method from the Feedback class
            feedback.showFeedbackDialog();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
