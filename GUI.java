import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GUI extends JFrame {
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private JLabel scoreLabel;
    private JLabel gameText;

    public GUI() {
        Game game = new Game();
        setTitle("Simple Math Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        scoreLabel = new JLabel("Score: " + Game.score);
        add(scoreLabel);

        // Initialize radio buttons
        option1 = new JRadioButton(Game.currentOptions[0]);
        option2 = new JRadioButton(Game.currentOptions[1]);
        option3 = new JRadioButton(Game.currentOptions[2]);
        option4 = new JRadioButton(Game.currentOptions[3]);

        // Create a ButtonGroup to group the radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);

        // Add radio buttons to the frame
        gameText = new JLabel(Game.currentProblem);
        add(gameText);
        add(new JPanel()); // Empty panel for spacing
        add(option1);
        add(new JPanel()); // Empty panel for spacing
        add(option2);
        add(new JPanel()); // Empty panel for spacing
        add(option3);
        add(new JPanel()); // Empty panel for spacing
        add(option4);

        // Add a button (for future use, you can add logic to it)
        JButton submitButton = new JButton("Submit");
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check which option is selected
                if (option1.isSelected()) {
                    game.processAnswer(Game.currentOptions[0]);
                } else if (option2.isSelected()) {
                    game.processAnswer(Game.currentOptions[1]);
                } else if (option3.isSelected()) {
                    game.processAnswer(Game.currentOptions[2]);
                } else if (option4.isSelected()) {
                    game.processAnswer(Game.currentOptions[3]);
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "Please select an option");
                }

                resetGameState();
            }
        });

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void resetGameState() {
        Game game = new Game();
        scoreLabel.setText("Score: " + Game.score);
        gameText.setText(Game.currentProblem);
        option1.setText(game.currentOptions[0]);
        option2.setText(game.currentOptions[1]);
        option3.setText(game.currentOptions[2]);
        option4.setText(game.currentOptions[3]);
    }

}
