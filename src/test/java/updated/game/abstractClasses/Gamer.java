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

    public abstract void getSymbolFromUser();
    public abstract List<Integer> getInputFromUser(Game game);
    public void reset(){
        symbol = ' ';
    }
    public void printDetails(){
        String cliResult = "*".repeat(30) +
                "\n" +
                "%25s".formatted("Details Received are as: \n") +
                "Username: %s\n".formatted(this.gamerName) +
                "Symbol Selected by %s: %c\n".formatted(this.gamerName, this.symbol) +
                "*".repeat(30);
        System.out.println(cliResult);
    }

    public abstract void incrementScore();
    public List<Integer>getRowAndColFromIdx(int index){
        List<Integer> rowAndCol = new ArrayList<>(2);
        rowAndCol.add((index - 1) / Board.BOARD_SIZE);
        rowAndCol.add((index - 1) % Board.BOARD_SIZE);
        return rowAndCol;
    }

}
