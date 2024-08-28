package Game;

public interface Game2Player {
    public void printInstructions();
    public void start();
    public void quit();
    public boolean userMove(String userName, String userSym, int index);
    public boolean rematch();
    public void printStatus();
}
