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
    public boolean isGameOver(int gamer1Moves, int gamer2Moves){
        if (gamer1Moves >= 3 || gamer2Moves >= 3) {
            return (this.board.checkHorizontal() || this.board.checkVertical() || this.board.checkDiagonal());
        }
        return false;
    }
}
