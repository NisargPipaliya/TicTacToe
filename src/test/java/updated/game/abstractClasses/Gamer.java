package updated.game.abstractClasses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public abstract class Gamer  {
    protected String gamerName;
    @Setter
    public char symbol;
    public int currentScore;

    public abstract void getSymbolFromUser();
    public abstract List<Integer> getInputFromUser(Game game);
    public abstract void incrementScore();

    public void reset(){
        symbol = ' ';
    }
    public void getUserNameFromCli(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your UserName: ");
        String s;
        while (true) {
            s = sc.nextLine();
            if (s.isEmpty()) {
                System.out.println("!! Please enter your name !!");
            }
            else {
                this.gamerName = s.trim();
                break;
            }
        }
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

    public List<Integer>getRowAndColFromIdx(int index){
        List<Integer> rowAndCol = new ArrayList<>(2);
        int row = (index - 1) / Board.BOARD_SIZE, col = (index - 1) % Board.BOARD_SIZE;
        rowAndCol.add(Math.max(row, 0)) ;
        rowAndCol.add(Math.max(col,0));
        return rowAndCol;
    }

}
