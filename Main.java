import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    // Components
    private JButton btnLock1 = new JButton("Unlock");
    private JButton btnLock2 = new JButton("Unlock");
    private JButton btnGenerate = new JButton("Generate");
    private JButton btnFeedback = new JButton("Add");
    private JTextField txtFirstStatement = new JTextField("First Statement");
    private JTextField txtSecondStatement = new JTextField("Second Statement");

    //Instantiate Prompt class
    private Prompt prompt = new Prompt("C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\firststatement.txt",
                               "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\secondstatement.txt");

    private Feedback feedback = new Feedback();
    private final JPanel panel = new JPanel();

    public Main() {
    	setTitle("Randomized Ideation Generator");
        // Frame settings
        getContentPane().setLayout(null);
        setVisible(true);
        setSize(1026, 617);
        setResizable(false);
        txtFirstStatement.setToolTipText("");
        txtFirstStatement.setBackground(new Color(36, 40, 52));
        txtFirstStatement.setEditable(false);
        txtSecondStatement.setBackground(new Color(36, 40, 52));
        txtSecondStatement.setEditable(false);
        getContentPane().add(txtFirstStatement);
        getContentPane().add(txtSecondStatement);
        getContentPane().add(btnGenerate);
        getContentPane().add(btnFeedback);
        btnGenerate.addActionListener(this);
        btnFeedback.addActionListener(this);
        txtFirstStatement.setBounds(231, 175, 660, 46);
        txtSecondStatement.setBounds(231, 265, 660, 46);
        btnGenerate.setBounds(464, 368, 133, 47);
        btnFeedback.setBounds(464, 441, 133, 47);


        getContentPane().setBackground(new Color(36, 40, 52));
        btnGenerate.setBackground(new Color(228, 93, 88)); 
        btnFeedback.setBackground(new Color(255, 255, 255));

        // Set text colors of the statements to black
        Color textColor = Color.BLACK;
        txtFirstStatement.setForeground(new Color(255, 255, 255));
        txtSecondStatement.setForeground(new Color(255, 255, 255));

        // set the text colors of the texts inside the buttons to white
        Color textColor2 = Color.WHITE;
        btnGenerate.setForeground(textColor2);
        btnFeedback.setForeground(new Color(228, 93, 88));
        btnGenerate.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnFeedback.setFont(new Font("SansSerif", Font.BOLD, 18));
        txtFirstStatement.setFont(new Font("SansSerif", Font.BOLD, 23));
        txtSecondStatement.setFont(new Font("SansSerif", Font.BOLD, 23));
        
        JPanel header = new JPanel();
        header.setBackground(new Color(228, 93, 88));
        header.setBounds(0, 0, 1012, 60);
        getContentPane().add(header);
        header.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Randomized Ideation Generator");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setBounds(35, 18, 358, 25);
        header.add(lblNewLabel);
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 23));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        
        panel.setBackground(new Color(36, 40, 52));
        panel.setBounds(105, 143, 117, 214);
        
        getContentPane().add(panel);
                panel.setLayout(null);
                btnLock1.setBounds(10, 33, 92, 46);
                panel.add(btnLock1);
        
                // Add action listeners
                btnLock1.addActionListener(this);
                btnLock1.setBackground(new Color(36, 40, 52));
                btnLock1.setForeground(new Color(228, 93, 88));
                
                        // Set font styles
                        btnLock1.setFont(new Font("SansSerif", Font.BOLD, 16));
                        btnLock2.setBounds(10, 123, 92, 46);
                        panel.add(btnLock2);
                        btnLock2.addActionListener(this);
                        btnLock2.setBackground(new Color(36, 40, 52));
                        btnLock2.setForeground(new Color(228, 93, 88));
                        btnLock2.setFont(new Font("SansSerif", Font.BOLD, 16));

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

            // Change the color and text of btnLock1 when clicked and locked
            if (prompt.isLock1()) {
            	btnLock1.setBackground(new Color(228, 93, 88)); // Reset to original background color
                btnLock1.setForeground(Color.WHITE); // Reset to original text color
                btnLock1.setText("Lock");
            } else {
                btnLock1.setBackground(new Color(36, 40, 52)); // Set to background color
                btnLock1.setForeground(new Color(228, 93, 88)); // Set to red
                btnLock1.setText("Unlock");
            }
        }

        // Second statement lock button
        else if (e.getSource() == btnLock2) {
            prompt.toggleLock2();
            prompt.toggleLock(txtSecondStatement);

            // Change the color and text of btnLock2 when clicked and locked
            if (prompt.isLock2()) {
            	btnLock2.setBackground(new Color(228, 93, 88)); // Reset to original background color
                btnLock2.setForeground(Color.WHITE); // Reset to original text color
                btnLock2.setText("Lock");
            } else {
            	btnLock2.setBackground(new Color(36, 40, 52)); // Set to background color
                btnLock2.setForeground(new Color(228, 93, 88)); // Set to red
                btnLock2.setText("Unlock");
            }
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
