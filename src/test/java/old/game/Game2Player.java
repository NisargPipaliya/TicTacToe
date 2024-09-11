package old.game;

public interface Game2Player {
    public void printInstructions();
    public void play();
    public void quit();
    public void userMove(String userName, char userSym, int row, int col);
    public boolean askForRematch();
    public void printStatus();
    public void printStats();
}
