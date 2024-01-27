import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame implements ActionListener{
	
	private JButton btnLock1;
    private JButton btnLock2;
    private JButton btnGenerate;
    private JButton btnFeedback;
    private JButton btnSave;
    private JButton btnDelete;
    private JTextField txtFirstStatement;
    private JTextField txtSecondStatement;
    private JList<String> listSavedPrompt1;
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel dashboardPanel;
    public JPanel generatorPanel; // New panel for the generator content
    private DefaultListModel<String> savedPromptListModel;
    //Instantiate Prompt class
    private Prompt prompt = new Prompt("C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\firststatement.txt",
                               "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\prompt\\secondstatement.txt");


    private Feedback feedback = new Feedback();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
    	setResizable(false);
        setTitle("Randomized Ideation Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 831, 494);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(58, 65, 71));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        

        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(228, 93, 88));
        panelHeader.setBounds(0, 0, 832, 39);
        contentPane.add(panelHeader);
        panelHeader.setLayout(null);

        JButton btnGenerator = new JButton("Generator");
        btnGenerator.setBounds(598, 5, 102, 29);
        panelHeader.add(btnGenerator);

        btnGenerator.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGenerator.setForeground(new Color(255, 255, 255));
        btnGenerator.setBackground(new Color(54, 57, 63));

        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setBounds(702, 5, 106, 29);
        panelHeader.add(btnDashboard);
        
        btnDashboard.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnDashboard.setForeground(new Color(255, 255, 255));
        btnDashboard.setBackground(new Color(54, 57, 63));

        JLabel lblNewLabel = new JLabel("Randomized Ideation Generator");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 23));
        lblNewLabel.setBounds(10, 2, 401, 36);
        panelHeader.add(lblNewLabel);

        dashboardPanel = new JPanel();
        dashboardPanel.setBackground(new Color(58, 66, 69));
        dashboardPanel.setBounds(105, 41, 620, 416);
        contentPane.add(dashboardPanel);
        dashboardPanel.setLayout(null);

        savedPromptListModel = new DefaultListModel<>();

        listSavedPrompt1 = new JList<>(savedPromptListModel);
        listSavedPrompt1.setBackground(new Color(255, 255, 255));
        listSavedPrompt1.setBounds(10, 83, 600, 289);
        dashboardPanel.add(listSavedPrompt1);
        
        JButton btnRate = new JButton("Rate");
        btnRate.setForeground(new Color(255, 255, 255));
        btnRate.setBackground(new Color(36, 40, 42));
        btnRate.setBounds(394, 50, 89, 23);
        dashboardPanel.add(btnRate);

        JTextArea txtrToday = new JTextArea();
        txtrToday.setForeground(new Color(228, 93, 88));
        txtrToday.setBackground(new Color(58, 66, 69));
        txtrToday.setFont(new Font("SansSerif", Font.BOLD, 25));
        txtrToday.setText("Saved Prompts");
        txtrToday.setBounds(10, 36, 197, 37);
        dashboardPanel.add(txtrToday);

      //Show the main Page
        btnGenerator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnGenerator.setBackground(new Color(228, 93, 88));
                generatorPanel.setVisible(true);
                dashboardPanel.setVisible(false);
            }
        });

        btnGenerator.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnGenerator.setBackground(new Color(228, 93, 88)); // Set the hover color
            }

            public void mouseExited(MouseEvent e) {
                btnGenerator.setBackground(new Color(54, 57, 63)); // Set the default color
            }
        });
        
        //show the DashBoard Page
        btnDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDashboard.setBackground(new Color(228, 93, 88));
                dashboardPanel.setVisible(true);
                generatorPanel.setVisible(false);
            }
        });
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(36, 40, 42));
        btnDelete.setBounds(503, 50, 89, 23);
        dashboardPanel.add(btnDelete);
        dashboardPanel.setVisible(false);
        
        btnDashboard.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnDashboard.setBackground(new Color(228, 93, 88)); // Set the hover color
            }

            public void mouseExited(MouseEvent e) {
                btnDashboard.setBackground(new Color(54, 57, 63)); // Set the default color
            }
        });

        // Rate button logic
        String ratingsFilePath = "C:\\Users\\ANTONETTE\\Documents\\GitHub\\Randomized-Ideation-Generator\\ratings\\ratings.txt";
        
        btnRate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRate.setBackground(new Color(228, 93, 88));

                // Existing code for rating
                int selectedIndex = listSavedPrompt1.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Show rating dialog
                    String[] options = { "1", "2", "3", "4", "5" };
                    int rating = JOptionPane.showOptionDialog(null, "Rate this prompt:", "Rating",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    String selectedPrompt = savedPromptListModel.getElementAt(selectedIndex);
                    System.out.println("Rated prompt '" + selectedPrompt + "' with " + (rating + 1) + " stars");
                    
                    String ratings = "Rated prompt '" + selectedPrompt + "' with " + (rating + 1) + " stars";
                    
                    saveRatingsToFile(ratings, ratingsFilePath);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a prompt to rate.");
                }
            }
            
            public void saveRatingsToFile(String ratings, String filePath) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    writer.write(ratings);
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println("Error saving feedback to file");
                    e.printStackTrace();
                }
            }
        });
        
        
        btnRate.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnRate.setBackground(new Color(228, 93, 88)); // Set the hover color
            }

            public void mouseExited(MouseEvent e) {
                btnRate.setBackground(new Color(36, 40, 42)); // Set the default color
            }
        });
        
     // Set up the action listener for the Delete button
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listSavedPrompt1.getSelectedIndex();
                if (selectedIndex != -1) {
                    savedPromptListModel.remove(selectedIndex);  // Use remove
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a prompt to delete.");
                }
            }
        });
        
        generatorPanel = new JPanel();
        generatorPanel.setBackground(new Color(58, 66, 69));
        generatorPanel.setBounds(105, 41, 620, 416);
        contentPane.add(generatorPanel);
        generatorPanel.setLayout(null);
        
        //Generator Panel
        btnLock1 = new JButton("Lock");
        btnLock2 = new JButton("Lock");
        btnGenerate = new JButton("Generate");
        btnFeedback = new JButton("Add");
        btnSave = new JButton("Save");
        txtFirstStatement = new JTextField("first statement");
        txtSecondStatement = new JTextField("second statement");
        JLabel label = new JLabel("Randomized Ideation");
        
        //Generator design
        btnLock1.setBounds(0, 90, 92, 46);
        btnLock1.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLock1.addActionListener(this);
        btnLock1.setBackground(new Color(36, 40, 52));
        btnLock1.setForeground(new Color(228, 93, 88));
        
        
        txtFirstStatement.setBounds(120, 90, 500, 46);
        txtFirstStatement.setFont(new Font("Arial Black", Font.PLAIN, 15));
        txtFirstStatement.setForeground(new Color(255, 255, 255));
        txtFirstStatement.setBackground(new Color(36, 40, 52));
        txtFirstStatement.setEditable(false);

        btnLock2.setBounds(0, 180, 92, 46);
        btnLock2.addActionListener(this);
        btnLock2.setBackground(new Color(36, 40, 52));
        btnLock2.setForeground(new Color(228, 93, 88));
        btnLock2.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        txtSecondStatement.setBounds(120, 180, 500, 46);
        txtSecondStatement.setFont(new Font("Arial Black", Font.PLAIN, 15));
        txtSecondStatement.setForeground(new Color(255, 255, 255));
        txtSecondStatement.setBackground(new Color(36, 40, 52));
        txtSecondStatement.setEditable(false);
        
        btnGenerate.setBounds(243, 250, 134, 47);
        btnGenerate.setBackground(new Color(228, 93, 88));
        btnGenerate.setForeground(Color.WHITE);
        btnGenerate.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnGenerate.addActionListener(this);
        
        btnFeedback.setBounds(161, 317, 134, 47);
        btnFeedback.setBackground(Color.WHITE);
        btnFeedback.setForeground(new Color(228, 93, 88));
        btnFeedback.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnFeedback.addActionListener(this);
        
        btnSave.setBounds(325, 317, 134, 47);
        btnSave.setBackground(Color.WHITE);
        btnSave.setForeground(new Color(228, 93, 88));
        btnSave.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnSave.addActionListener(this);
        
        
      //Adding the generator parts
        generatorPanel.add(btnLock1);
        generatorPanel.add(btnLock2);
        generatorPanel.add(btnGenerate);
        generatorPanel.add(btnFeedback);
        generatorPanel.add(txtFirstStatement);
        generatorPanel.add(txtSecondStatement);
        generatorPanel.add(label);  
        generatorPanel.add(btnSave);
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
                btnLock1.setText("Unlock");
            } else {
                btnLock1.setBackground(new Color(36, 40, 52)); // Set to background color
                btnLock1.setForeground(new Color(228, 93, 88)); // Set to red
                btnLock1.setText("Lock");
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
                btnLock2.setText("Unlock");
            } else {
                btnLock2.setBackground(new Color(36, 40, 52)); // Set to background color
                btnLock2.setForeground(new Color(228, 93, 88)); // Set to red
                btnLock2.setText("Lock");
            }
        }

        // Feedback Button
        else if (e.getSource() == btnFeedback) {
            // Call the showFeedbackDialog method from the Feedback class
            feedback.showFeedbackDialog();
        } else if (e.getSource() == btnSave) {
        	String save1 = txtFirstStatement. getText();
        	String save2 = txtSecondStatement. getText();
        	String newsave2 = save2.replace("-", "");
        	String save3 = save1 +" "+ newsave2;
        	
        	savedPromptListModel.addElement(save3);	
        }
    }
}
