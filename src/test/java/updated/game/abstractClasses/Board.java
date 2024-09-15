package updated.game.abstractClasses;

import lombok.Getter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
public abstract class Board {
    public static  int BOARD_SIZE = 3;
    protected List<List<Character>> board;
    public Set<Integer> emptyCells;
    public char winningSym;
    public final char EMPTY_SPACE=' ';
    private final String SYMBOL_BAR = " | ";


    public Board(){
        emptyCells = new LinkedHashSet<>(BOARD_SIZE*BOARD_SIZE);
        this.initEmptyCells();

    }


    public void initEmptyCells(){
        SecureRandom randomNumGen = new SecureRandom();
        int size = BOARD_SIZE * BOARD_SIZE;
        while (this.emptyCells.size() != size) {
            int i = randomNumGen.nextInt(size+1);
            if (i == 0)
                i++;
            this.emptyCells.add(i);
        }
        this.winningSym = EMPTY_SPACE;

        if (this.board != null) {
            this.board.clear();
        }
        this.board = new ArrayList<>(BOARD_SIZE);
        for (int row = 0; row < BOARD_SIZE; row++) {
            this.board.add(new ArrayList<>(BOARD_SIZE));
            for (int col = 0; col < BOARD_SIZE; col++) {
                this.board.get(row).add(EMPTY_SPACE);
            }
        }
    }
    public boolean checkCollision(int index){
        return !this.emptyCells.contains(index);
    }
  
    public void userMove(Gamer gamer, int row, int col){
        this.board.get(row).set(col, gamer.getSymbol());
    }

    public boolean checkDiagonal() {
        boolean primaryDiagonalResult = true;
        boolean secondaryDiagonalResult = true;
        char primaryDiagonalSymbol = this.board.getFirst().getFirst();
        char secondaryDiagonalSymbol = this.board.getFirst().getLast();

        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                if(row == col){
                    primaryDiagonalResult = primaryDiagonalResult && (primaryDiagonalSymbol == this.board.get(row).get(col) && this.board.get(row).get(col) != EMPTY_SPACE);
                }
                if (col == (BOARD_SIZE-1-row)){
                    secondaryDiagonalResult = secondaryDiagonalResult && (secondaryDiagonalSymbol == this.board.get(row).get(col) && this.board.get(row).get(col) != EMPTY_SPACE);
                }
            }
        }
        if(primaryDiagonalResult){
            this.winningSym = primaryDiagonalSymbol;
        }
        if(secondaryDiagonalResult){
            this.winningSym = secondaryDiagonalSymbol;
        }

        return primaryDiagonalResult || secondaryDiagonalResult;
    }


    public boolean checkRowAndColumn(){
        boolean rowResult, colResult;
        char rowSymbol,colSymbol;
        for(int row = 0; row < BOARD_SIZE; row++){
            rowSymbol = this.board.get(row).getFirst();
            colSymbol = this.board.getFirst().get(row);
            rowResult = true;
            colResult = true;
            for(int col = 0 ; col < BOARD_SIZE; col++){
                rowResult = rowResult & (this.board.get(row).get(col) == rowSymbol && this.board.get(row).get(col) != EMPTY_SPACE);
                colResult = colResult & (this.board.get(col).get(row) == colSymbol && this.board.get(col).get(row) != EMPTY_SPACE);
            }
            if(rowResult){
                this.winningSym = rowSymbol;
                break;
            }
            else if(colResult){
                this.winningSym = colSymbol;
                break;
            }
        }
        return this.winningSym != EMPTY_SPACE;
    }
    public void printBoard() {
        StringBuilder cliResult = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (col != (BOARD_SIZE - 1)) {
                  cliResult.append(board.get(row).get(col)).append(SYMBOL_BAR);
                }
                else {
                  cliResult.append(board.get(row).get(col));
                  cliResult.append("\n");
                }
            }
            if (row != (BOARD_SIZE - 1)){
                cliResult.append("-".repeat(BOARD_SIZE * BOARD_SIZE));
                cliResult.append("\n");
            }
        }
        System.out.print(cliResult);
    }
}
