import lib.SmartScroller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
    private JFrame frame;
    private JTextField input;
    private JTextArea result;
    private JButton submitButton;
    private JLabel inputTitle, resultTitle, counterArea;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem helpMenuItem, newGameMenuItem;

    public GUI() {
        this.frame = new JFrame("Bulls and cows");

        menuBar = new JMenuBar();

        menu = new JMenu("Menu");

        newGameMenuItem = new JMenuItem("New Game");
        helpMenuItem = new JMenuItem("Help");

        menu.add(newGameMenuItem);
        menu.add(helpMenuItem);

        newGameMenuItem.addActionListener(this);
        helpMenuItem.addActionListener(this);

        menuBar.add(menu);

        inputTitle = new JLabel();
        inputTitle.setBounds(50, 25, 200, 30);
        inputTitle.setText("Type a number (4 digits):");

        input = new JTextField();
        input.setBounds(50, 50, 150, 20);

        counterArea = new JLabel();
        counterArea.setBounds(50, 75, 100, 30);
        counterArea.setText("Tries: " + BullsAndCows.getCounter());

        submitButton = new JButton("<html><font color=white>CHECK</font></html>");
        submitButton.setBounds(50, 100, 100, 40);
        Color colorGreen = new Color(66, 184, 221);
        submitButton.setBackground(colorGreen);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(true);
        submitButton.setContentAreaFilled(true);
        submitButton.addActionListener(this);

        resultTitle = new JLabel();
        resultTitle.setBounds(250, 25, 100, 30);
        resultTitle.setText("Result:");

        result = new JTextArea();
        result.setEditable(false);
        result.append("Computer picked a number. You can start guessing!");
        result.append(System.lineSeparator());
        JScrollPane resultScrollPane = new JScrollPane(result);
        resultScrollPane.setBounds(250, 50, 300, 400);
        new SmartScroller(resultScrollPane);

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/cow.png")));
        frame.setJMenuBar(menuBar);
        frame.add(inputTitle);
        frame.add(input);
        frame.add(counterArea);
        frame.add(submitButton);
        frame.getRootPane().setDefaultButton(submitButton);
        frame.add(resultTitle);
        frame.add(resultScrollPane);
        frame.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton) {

            String message = "";

            if (input.getText().length() == 0 || !InputValidator.isValid(input.getText())) {
                if (BullsAndCows.isGuessed) {
                    message = "Go to menu to start a new game!";
                } else {
                    message = "Invalid input!";
                }
            } else {
                String inputString = input.getText();

                int number = Integer.parseInt(inputString);

                BullsAndCows.increaseCounterByOne();
                this.counterArea.setText("Tries: " + BullsAndCows.getCounter());

                message = BullsAndCows.getMessage(number);

            }

            this.result.append(message);
            this.result.append(System.lineSeparator());

            input.setText("");

        } else if (e.getSource() == newGameMenuItem) {
            BullsAndCows.startNewGame();
            this.result.setText("Computer picked a number. You can start guessing!");
            this.result.append(System.lineSeparator());
            this.counterArea.setText("Tries: 0");
        } else if (e.getSource() == helpMenuItem) {
            JOptionPane.showMessageDialog(frame, "Info:\nComputer thinks of a 4 digit number and you have to guess it.\n" +
                    "Cow is when a digit is contained in the number but not in the proper place.\n" +
                    "Bull is when a digit is in the correct place.\n" +
                    "The number will always contain 4 digits.\n" +
                    "There will be only digits from 1-9.\n" +
                    "Digits will always be unique.\n" +
                    "Example: Number: 1234, Guess: 1356 - Bulls: 1, Cows: 1\n" +
                    "1 is a bull and 3 is a cow.\n" +
                    "Cheat code: 9999.");
        }

    }
}