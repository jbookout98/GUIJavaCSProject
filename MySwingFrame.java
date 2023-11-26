import javax.swing.*;
import java.awt.*;

public class MySwingFrame extends JFrame {
    public JPanel drawablePanel;
    public JLabel updatableTextField;

    private JButton quitButton;
    private JButton replayButton;

    public MySwingFrame(HangmanGame hangmanGame){
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
        InputListener inputGuesser = new InputListener(hangmanGame);
        hangmanGame.textLabel = new JLabel();

        drawablePanel.add(hangmanGame.textLabel,BorderLayout.NORTH);
        this.add(inputGuesser);

        quitButton = new JButton("Quit");
        replayButton = new JButton("Replay");

        // Set their initial visibility to false
        quitButton.setVisible(false);
        replayButton.setVisible(false);
        add(quitButton);
        add(replayButton);


        JLabel labelTopRight = new JLabel("Top Right String");
        JLabel labelBottomRight = new JLabel("Bottom Right String");
        this.add(labelTopRight);
        this.add(labelBottomRight);




        layout.putConstraint(SpringLayout.NORTH, drawablePanel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, drawablePanel, 10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, inputGuesser, 10, SpringLayout.SOUTH, drawablePanel);
        layout.putConstraint(SpringLayout.WEST, inputGuesser, 10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, labelTopRight, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelTopRight, 10, SpringLayout.EAST, drawablePanel);

        layout.putConstraint(SpringLayout.NORTH, labelBottomRight, 10, SpringLayout.SOUTH, labelTopRight);
        layout.putConstraint(SpringLayout.WEST, labelBottomRight, 0, SpringLayout.WEST, labelTopRight);

        layout.putConstraint(SpringLayout.SOUTH, quitButton, -10, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, quitButton, -10, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.SOUTH, replayButton, -10, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, replayButton, 10, SpringLayout.WEST, this);


        quitButton.addActionListener(e -> System.exit(0));
        replayButton.addActionListener(e -> {
            hangmanGame.resetGame();
            quitButton.setVisible(false);
            replayButton.setVisible(false);
        });

    }
    public void showGameOverButtons() {
        quitButton.setVisible(true);
        replayButton.setVisible(true);
    }
}
