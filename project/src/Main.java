import java.awt.Color;
import java.awt.EventQueue;
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

public class Main extends JFrame implements ActionListener {

    private JPanel panelHeader = new JPanel();
    private JButton btnGenerator = new JButton("Generator");
    private JButton btnDashboard = new JButton("Dashboard");
    private JLabel lblTitle = new JLabel("Randomized Ideation Generator");

    private JButton btnLock1 = new JButton("Lock");
    private JButton btnLock2 = new JButton("Lock");
    private JButton btnGenerate = new JButton("Generate");
    private JButton btnAddIdea = new JButton("Add");
    private AddIdea addIdea = new AddIdea();
    private JButton btnSave = new JButton("Save");
    private JTextField txtFirstStatement = new JTextField("first statement");
    private JTextField txtSecondStatement = new JTextField("second statement");

    private JPanel dashboardPanel = new JPanel();
    private JPanel contentPane = new JPanel();
    public JPanel generatorPanel;
    private JButton btnDelete = new JButton("Delete");
    private JButton btnRate = new JButton("Rate");
    private static final long serialVersionUID = 1L;
    private DefaultListModel<String> savedPromptListModel = new DefaultListModel<>();
    private JList<String> listSavedPrompt1 = new JList<>(savedPromptListModel);
    private JScrollPane scrollPane = new JScrollPane(listSavedPrompt1);
    private JTextArea txtSavedPrompts = new JTextArea();

    private Prompt prompt = new Prompt("prompt\\firststatement.txt", "prompt\\secondstatement.txt");
    String ratingsFilePath = "ratings\\ratings.txt";

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

        //Frame settings
        setResizable(false);
        setTitle("Randomized Ideation Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 831, 494);
        contentPane.setBackground(new Color(58, 65, 71));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        //Header Container
        panelHeader.setBackground(new Color(228, 93, 88));
        panelHeader.setBounds(0, 0, 832, 39);
        contentPane.add(panelHeader);
        panelHeader.setLayout(null);

        //HEADER: Title
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 23));
        lblTitle.setBounds(10, 2, 401, 36);
        panelHeader.add(lblTitle);

        //HEADER: Button Generator
        panelHeader.add(btnGenerator);
        btnGenerator.setBounds(598, 5, 102, 29);
        btnGenerator.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGenerator.setForeground(new Color(255, 255, 255));
        btnGenerator.setBackground(new Color(54, 57, 63));
        btnGenerator.addActionListener(this);

        //HEADER: Button Dashboard
        panelHeader.add(btnDashboard);
        btnDashboard.setBounds(702, 5, 106, 29);
        btnDashboard.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnDashboard.setForeground(new Color(255, 255, 255));
        btnDashboard.setBackground(new Color(54, 57, 63));
        btnDashboard.addActionListener(this);

        //DASHBOARD PAGE Container
        contentPane.add(dashboardPanel);
        dashboardPanel.setBackground(new Color(58, 66, 69));
        dashboardPanel.setBounds(105, 41, 620, 416);
        dashboardPanel.setVisible(false);
        dashboardPanel.setLayout(null);

        //DASHBOARD: TEXT AREA SAVED PROMPTS
        dashboardPanel.add(scrollPane);
        dashboardPanel.add(txtSavedPrompts);
        txtSavedPrompts.setText("Saved Prompts");
        scrollPane.setBounds(10, 83, 600, 289);
        listSavedPrompt1.setBackground(new Color(255, 255, 255));
        txtSavedPrompts.setForeground(new Color(228, 93, 88));
        txtSavedPrompts.setBackground(new Color(58, 66, 69));
        txtSavedPrompts.setFont(new Font("SansSerif", Font.BOLD, 25));
        txtSavedPrompts.setBounds(10, 36, 197, 37);
        txtSavedPrompts.setLineWrap(true);
        txtSavedPrompts.setWrapStyleWord(true);
        
        //DASHBOARD: Rate Button
        dashboardPanel.add(btnRate);
        btnRate.setForeground(new Color(255, 255, 255));
        btnRate.setBackground(new Color(36, 40, 42));
        btnRate.setBounds(394, 50, 89, 23);
        btnRate.addActionListener(this);

        //DASHBOARD: Delete Button
        dashboardPanel.add(btnDelete);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(36, 40, 42));
        btnDelete.setBounds(503, 50, 89, 23);
        btnDelete.addActionListener(this);

        //MAIN PAGE
        generatorPanel = new JPanel();
        contentPane.add(generatorPanel);
        generatorPanel.setBackground(new Color(58, 66, 69));
        generatorPanel.setBounds(105, 41, 620, 416);
        generatorPanel.setLayout(null);

        //MAIN PAGE: FIRST PROMPT IDEA COMPONENTS
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

        //MAIN PAGE: SECOND PROMPT IDEA COMPONENTS
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

        //MAIN PAGE: GENERATE BUTTON
        btnGenerate.setBounds(243, 250, 134, 47);
        btnGenerate.setBackground(new Color(228, 93, 88));
        btnGenerate.setForeground(Color.WHITE);
        btnGenerate.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnGenerate.addActionListener(this);

        //MAIN PAGE: ADD IDEA BUTTON
        btnAddIdea.setBounds(161, 317, 134, 47);
        btnAddIdea.setBackground(Color.WHITE);
        btnAddIdea.setForeground(new Color(228, 93, 88));
        btnAddIdea.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnAddIdea.addActionListener(this);

        //MAIN PAGE: SAVE BUTTON
        btnSave.setBounds(325, 317, 134, 47);
        btnSave.setBackground(Color.WHITE);
        btnSave.setForeground(new Color(228, 93, 88));
        btnSave.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnSave.addActionListener(this);

        generatorPanel.add(btnLock1);
        generatorPanel.add(btnLock2);
        generatorPanel.add(btnGenerate);
        generatorPanel.add(btnAddIdea);
        generatorPanel.add(txtFirstStatement);
        generatorPanel.add(txtSecondStatement);
        generatorPanel.add(btnSave);

        setupButtonMouseListener(btnGenerator, new Color(228, 93, 88), new Color(54, 57, 63));
        setupButtonMouseListener(btnDashboard, new Color(228, 93, 88), new Color(54, 57, 63));
        setupButtonMouseListener(btnRate, new Color(228, 93, 88), new Color(36, 40, 42));
    }

    private void setupButtonMouseListener(JButton button, Color enterColor, Color exitColor) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(enterColor);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(exitColor);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

        //BUTTON FUNCTION FOR MAIN PAGE
        if (e.getSource() == btnGenerate) {
            prompt.generateIdeas(txtFirstStatement, txtSecondStatement);
        } 
        else if (e.getSource() == btnLock1) {
            prompt.toggleLock1();
            prompt.toggleLock(txtFirstStatement);
            if (prompt.isLock1()) {
                btnLock1.setBackground(new Color(228, 93, 88));
                btnLock1.setForeground(Color.WHITE);
                btnLock1.setText("Unlock");
            } 
            else {
                btnLock1.setBackground(new Color(36, 40, 52));
                btnLock1.setForeground(new Color(228, 93, 88));
                btnLock1.setText("Lock");
            }
        }
        else if (e.getSource() == btnLock2) {
            prompt.toggleLock2();
            prompt.toggleLock(txtSecondStatement);
            if (prompt.isLock2()) {
                btnLock2.setBackground(new Color(228, 93, 88));
                btnLock2.setForeground(Color.WHITE);
                btnLock2.setText("Unlock");
            } else {
                btnLock2.setBackground(new Color(36, 40, 52));
                btnLock2.setForeground(new Color(228, 93, 88));
                btnLock2.setText("Lock");
            }
        }
        else if (e.getSource() == btnAddIdea) {
            addIdea.showIdeaDialog();
        }
        else if (e.getSource() == btnSave) {
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

        //BUTTON FUNCTION FOR HEADER
        else if (e.getSource() == btnGenerator) {
            btnGenerator.setBackground(new Color(228, 93, 88));
            generatorPanel.setVisible(true);
            dashboardPanel.setVisible(false);
        }
        else if (e.getSource() == btnDashboard) {
            btnDashboard.setBackground(new Color(228, 93, 88));
            dashboardPanel.setVisible(true);
            generatorPanel.setVisible(false);
        }

        //BUTTON FUNCTION FOR DASHBOARD PAGE
        else if (e.getSource() == btnRate) {
            btnRate.setBackground(new Color(228, 93, 88));
            Rate rateHandler = new Rate(savedPromptListModel, ratingsFilePath, listSavedPrompt1);
            rateHandler.actionPerformed(e);
        }
        else if (e.getSource() == btnDelete) {
            int selectedIndex = listSavedPrompt1.getSelectedIndex();
            if (selectedIndex != -1) {
                savedPromptListModel.remove(selectedIndex);
            }
            else {
                JOptionPane.showMessageDialog(null, "Please select a prompt to delete.");
            }
        }
    }

    //METHODS FOR SAVED CHECKING
    private boolean isIdeaAlreadySaved(String idea) {
        for (int i = 0; i < savedPromptListModel.size(); i++) {
            if (savedPromptListModel.getElementAt(i).equals(idea)) {
                return true;
            }
        }
        return false;
    }
    
}