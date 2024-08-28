package Game;

public interface Game2Player {
    public void printInstructions();
    public void start();
    public void quit();
    public void userMove(String userName, char userSym, int row, int col);
    public boolean rematch();
    public void printStatus();
}
