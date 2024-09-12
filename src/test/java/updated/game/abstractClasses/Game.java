package updated.game.abstractClasses;
import updated.game.abstractClasses.*;
import lombok.Getter;

@Getter
public abstract class Game {
    protected int numberOfMatches;
    protected boolean playGame;
    protected Board board;

    public Game(){
        numberOfMatches = 1;
        playGame = true;
    }

    public abstract void printInstructions();
    public abstract void play();
    public abstract boolean askForRematch();
    public abstract void printStats();
    public abstract void quit();
    public abstract boolean isGameOver(int gamer1Moves, int gamer2Moves);
}
