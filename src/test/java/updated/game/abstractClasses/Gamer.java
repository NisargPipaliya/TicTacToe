package updated.game.abstractClasses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Gamer {
    protected String gamerName;
    @Setter
    public char symbol;
    public int currentScore;
    public int numberOfMoves;

    public abstract void getSymbolFromUser();
    public abstract List<Integer> getInputFromUser(Game game);
    public void reset(){
        numberOfMoves = 0;
        symbol = ' ';
    }
    public void printDetails(){
        System.out.println("******************************");
        System.out.printf("%25s", "Details Received are as: \n");
        System.out.printf("Username: %s\n", this.gamerName);
        System.out.printf("Symbol Selected by %s: %c\n", this.gamerName, this.symbol);
        System.out.println("******************************");

    }

    public abstract void incrementScore();
    public void incrementNumberOfMoves(){
        this.numberOfMoves++;
    }
    public List<Integer>getRowAndColFromIdx(int index){
        List<Integer> rowAndCol = new ArrayList<>(2);
        rowAndCol.add((index - 1) / Board.BOARD_SIZE);
        rowAndCol.add((index - 1) % Board.BOARD_SIZE);
        return rowAndCol;
    }

}
