import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    // Components
    private JButton btn1 = new JButton("Lock 1");
    private JButton btn2 = new JButton("Lock 2");
    private JButton btn3 = new JButton("Generate");
    private JTextField txt1 = new JTextField("first statement");
    private JTextField txt2 = new JTextField("second statement");

    //Instantiate classes
    private Prompt prompt = new Prompt("C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\firststatement.txt",
                               "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\secondstatement.txt");

    public Main() {
        // Frame settings
        setLayout(null);
        setVisible(true);
        setSize(500, 300);
        setResizable(false);

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

        // Set Bounds for the Components
        btn1.setBounds(25, 60, 80, 20);
        txt1.setBounds(115, 55, 350, 30);
        btn2.setBounds(25, 105, 80, 20);
        txt2.setBounds(115, 100, 350, 30);
        btn3.setBounds(210, 140, 90, 20);

        // Exit the program
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        // Generator button
        if (e.getSource() == btn3) {
            prompt.generateIdeas(txt1, txt2);
        }

        // First statement lock button
        else if (e.getSource() == btn1) {
            prompt.toggleLock1();
            prompt.toggleLock(txt1);
        }

        // Second statement lock button
        else if (e.getSource() == btn2) {
            prompt.toggleLock2();
            prompt.toggleLock(txt2);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
