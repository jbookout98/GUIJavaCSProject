/**
 * Main class to run the Hangman game.
 * This class handles user input and game flow.
 */
public class Main {
    public static void main(String[] args) {




        Player player = new Player("Blake");
;
        HangmanGame h;

        h= new HangmanGame(4);

        MySwingFrame frame = new MySwingFrame(h,player);

        frame.setVisible(true);
        //player.incrementScores(10-hangmanGame.getCurrentAttempts());
    }

    /**
     * Prompts the player to choose a letter and processes the guess.
     * @param h HangmanGame instance.
     * @param scnr Scanner for reading user input.
     * @param player Current player.
     */

}