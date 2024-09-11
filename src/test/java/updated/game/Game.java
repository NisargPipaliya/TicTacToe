package updated.game;

import lombok.Getter;

@Getter
public abstract class Game {
    private int numberOfMatches;
    private boolean playGame;

    public abstract void printInstructions();
    public abstract void play();
    public abstract boolean askForRematch();
    public abstract void printStats();
    public abstract void quit();
}
