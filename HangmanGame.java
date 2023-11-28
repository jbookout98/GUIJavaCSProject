import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the logic and state of a Hangman game.
 */
public class HangmanGame extends JComponent{
    private String secretWord;
    private StringBuilder guessedWord;
    private int maxAttempts;
    private int currentAttempts;
    private Set<Character> guessedLetters;
    private MySwingFrame baseFrame;
    public JLabel textLabel;
    public JLabel topAttemptsLabel;
    public WordBank wordBank;
    protected boolean wonGame = false;

    /**
     * Constructor for HangmanGame.

     * @param maxAttempts The maximum number of attempts allowed.
     */
    public HangmanGame(int maxAttempts){

        wordBank=new WordBank();

        this.setPreferredSize(new Dimension(164,164));
        this.secretWord=wordBank.getRandomWord();
        this.maxAttempts=maxAttempts;
        guessedWord = new StringBuilder("_".repeat(secretWord.length()));
        guessedLetters = new HashSet<>();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphicsObj = (Graphics2D) g;
        printGameStatus(graphicsObj);

        Color binColor1 = new Color(128, 128, 0);
        graphicsObj.setColor(binColor1);

    }
    public void setBaseFrame(MySwingFrame fr){
        baseFrame=fr;
    }
    /**
     * Processes the player's guess of a letter.
     * @param letter The letter guessed by the player.
     */
    public void guessedLetter(char letter){
        if(isLetterAlreadyGuessed(letter)){
            System.out.println("You have already guseed the letter "+letter);
            return;
        }
        guessedLetters.add(letter);
        if(secretWord.indexOf(letter)>=0){
            for(int i =0; i <secretWord.length();i++) {
                if (secretWord.charAt(i) == letter) {
                    guessedWord.setCharAt(i,letter);
                }
            }
            System.out.println("Correct guess!");
        }else{
            currentAttempts++;
            System.out.println("Incorrect Guess!");
            topAttemptsLabel.setText("Current Wrong Attemps Left: " + Integer.toString(maxAttempts-currentAttempts));
        }
        this.repaint();
    }
    public int getCurrentAttempts(){
        return currentAttempts;
    }
    public boolean isGameOver() {
        if (guessedWord.toString().equals(secretWord)) {
            wonGame=true;
            baseFrame.AddToScore(((maxAttempts-currentAttempts)*10));
            baseFrame.showGameOverButtons();
            return true;
        }
        if (currentAttempts >= maxAttempts) {
            wonGame=false;
            baseFrame.showGameOverButtons();
            System.out.println("Game Over! You've reached the maximum number of attempts.");
            return true;
        }

        return false;
    }
    public void resetGame() {

        currentAttempts = 0;
        this.secretWord=wordBank.getRandomWord();
        guessedLetters.clear();
        guessedWord = new StringBuilder("_".repeat(secretWord.length()));
        wonGame = false;
        repaint();
    }
    public boolean isWordGuessed(){
        return guessedWord.toString().equals(secretWord);
    }
    public boolean isLetterAlreadyGuessed(char letter) {
        // Check if the letter is in the set of guessed letters
        return guessedLetters.contains(letter);
    }
    public String returnActualString(){

        return secretWord;

    }
    private void printGameStatus(Graphics2D g2d){

        if (currentAttempts >= 0) {
            g2d.drawLine(10, 200, 150, 200); // Bottom line
            g2d.drawLine(30, 200, 30, 50);  // Vertical line
            g2d.drawLine(30, 50, 100, 50);  // Top line
            g2d.drawLine(100, 50, 100, 70); // Rope
        }
        if (currentAttempts >= 1) {
            g2d.drawOval(90, 70, 20, 20); // Head
        }
        if (currentAttempts >= 2) {
            g2d.drawLine(100, 90, 100, 150); // Body
        }
        if (currentAttempts >= 3) {
            g2d.drawLine(100, 100, 120, 110); // Right arm
            g2d.drawLine(100, 100, 80, 110);  // Left arm
        }
        if (currentAttempts >= 4) {
            g2d.drawLine(100, 150, 120, 160); // Right leg
            g2d.drawLine(100, 150, 80, 160);  // Left leg
        }
        this.textLabel.setText(guessedWord.toString());
    }



}
