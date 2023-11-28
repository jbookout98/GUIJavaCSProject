import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MySwingFrame extends JFrame {
    public JPanel drawablePanel;
    public JLabel updatableTextField;
    private InputListener inputGuesser;
    private JButton quitButton;
    private JButton replayButton;
    private JButton displayStringButton;
    private boolean showPlayerScore = false;
    private Player currentPlayer;

    private JTextField nameInputField;
    private JLabel nameLabel;
    private JPanel namePanel;
    public MySwingFrame(HangmanGame hangmanGame,Player player){
        currentPlayer = player;
        setSize(800,500);
        setTitle("Hangman the Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        drawablePanel = new JPanel();
        drawablePanel.setBackground(Color.LIGHT_GRAY);
        drawablePanel.setPreferredSize(new Dimension(400, 400));
        hangmanGame.setBaseFrame(this);
        hangmanGame.setVisible(true);
        add(drawablePanel);
        drawablePanel.add(hangmanGame,BorderLayout.WEST);
        inputGuesser = new InputListener(hangmanGame);
        hangmanGame.textLabel = new JLabel();

        drawablePanel.add(hangmanGame.textLabel,BorderLayout.NORTH);
        this.add(inputGuesser);

        quitButton = new JButton("Quit");
        replayButton = new JButton("Replay");

        // Set their initial visibility to false
        quitButton.setVisible(false);
        replayButton.setVisible(false);
        displayStringButton = new JButton("Show Player Score");
        JLabel stringDisplayLabel = new JLabel("");

        add(quitButton);
        add(replayButton);
        add(displayStringButton);
        add(stringDisplayLabel);

        hangmanGame.topAttemptsLabel = new JLabel("Current Wrong Attempts Left: 4");

        this.add(hangmanGame.topAttemptsLabel);



        layout.putConstraint(SpringLayout.NORTH, displayStringButton, 20, SpringLayout.SOUTH, hangmanGame.topAttemptsLabel);
        layout.putConstraint(SpringLayout.WEST, displayStringButton, 0, SpringLayout.WEST, hangmanGame.topAttemptsLabel);

        layout.putConstraint(SpringLayout.NORTH, stringDisplayLabel, 10, SpringLayout.SOUTH, displayStringButton);
        layout.putConstraint(SpringLayout.WEST, stringDisplayLabel, 0, SpringLayout.WEST, displayStringButton);

        layout.putConstraint(SpringLayout.NORTH, drawablePanel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, drawablePanel, 10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, inputGuesser, 10, SpringLayout.SOUTH, drawablePanel);
        layout.putConstraint(SpringLayout.WEST, inputGuesser, 10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, hangmanGame.topAttemptsLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, hangmanGame.topAttemptsLabel, 10, SpringLayout.EAST, drawablePanel);



        layout.putConstraint(SpringLayout.SOUTH, quitButton, -10, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, quitButton, 64, SpringLayout.EAST, replayButton);

        layout.putConstraint(SpringLayout.SOUTH, replayButton, -10, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, replayButton, 10, SpringLayout.WEST, this);

        namePanel = new JPanel(new BorderLayout());
        nameLabel = new JLabel("Enter Your Name:");
        nameInputField = new JTextField(20);

        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(nameInputField, BorderLayout.CENTER);

        // Add the name panel to the frame
        add(namePanel, BorderLayout.NORTH);

        // Initially set other components to invisible
        setGameComponentsVisibility(false);

        // Action listener for the text field
        nameInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInputField.getText();
                if (!name.trim().isEmpty()) {
                    player.setName(name); // Set player's name
                    setGameComponentsVisibility(true); // Make game components visible
                    namePanel.setVisible(false); // Hide the name input panel
                } else {
                    JOptionPane.showMessageDialog(MySwingFrame.this,
                            "Please enter a name to continue.",
                            "Name Required", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        quitButton.addActionListener(e -> System.exit(0));
        replayButton.addActionListener(e -> {

            hangmanGame.resetGame();
            quitButton.setVisible(false);
            replayButton.setVisible(false);
            drawablePanel.setVisible(true);
            inputGuesser.setVisible(true);
        });

        displayStringButton.addActionListener(e -> {
            showPlayerScore=!showPlayerScore;
            if(showPlayerScore){
                stringDisplayLabel.setVisible(true);
                displayStringButton.setText("Hide Player Score");

            }else{
                stringDisplayLabel.setVisible(false);
                displayStringButton.setText("Show Player Score");
            }
            stringDisplayLabel.setText( player.getName() +"'s score: "+player.getScore());


        });

    }
    private void setGameComponentsVisibility(boolean visible) {
        drawablePanel.setVisible(visible);
        inputGuesser.setVisible(visible);

        displayStringButton.setVisible(visible);
        // ... [set visibility of other components as needed] ...
    }
    public void AddToScore(int actualPoints){
        currentPlayer.incrementScores(actualPoints);


    }
    public void showGameOverButtons() {

        drawablePanel.setVisible(false);
        inputGuesser.setVisible(false);
        quitButton.setVisible(true);
        replayButton.setVisible(true);
    }
}
