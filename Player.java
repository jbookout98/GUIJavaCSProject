/**
 * Represents a player in the Hangman game.
 */
public class Player {
    private String name;
    private int score;
    /**
     * Constructor for Player.
     * @param name Name of the player.
     */
    public void setName(String name){
        this.name=name;
    }
    public Player(String name){
        this.name =name;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
    public void incrementScores(int point){
        score+=point;
    }
}
