package updated.game;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class Gamer {
    private String gamerName;
    private char symbol;
    private int currentScore;
    private int numberOfMoves;

    public abstract char getSymbolFromUser();
    public abstract List<Integer> getInputFromUser();

    public void printDetails(){
        System.out.println("******************************");
        System.out.printf("%25s", "Details Received are as: \n");
        System.out.printf("Username: %s\n", this.gamerName);
        System.out.printf("Symbol Selected by %s: %c\n", this.gamerName, this.symbol);
        System.out.println("******************************");

    }
}
