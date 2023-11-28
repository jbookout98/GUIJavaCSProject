import java.util.Random;
/**
 * Represents a repository of words for the Hangman game.
 */
public class WordBank {

    private String[] words={
            "hello","poop","minecraft","mercury","bob","burger","gum","words","chocolate"


    };

    /**
     * Default constructor for WordBank.
     */
    public WordBank(){

    }
    /**
     * Randomly selects and returns a word from the word bank.
     * @return A randomly selected word.
     */
    public String getRandomWord(){

        Random r = new Random();
        r.ints(0,words.length);
        int i = r.nextInt(words.length);
        return words[i];
    }

}
