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
    protected char winningSym;

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
        this.winningSym = ' ';

        if (this.board != null) {
            this.board.clear();
        }
        this.board = new ArrayList<List<Character>>(BOARD_SIZE);
        for (int row = 0; row < BOARD_SIZE; row++) {
            this.board.add(new ArrayList<>(BOARD_SIZE));
            for (int col = 0; col < BOARD_SIZE; col++) {
                this.board.get(row).add(' ');
            }
        }
    }
    public boolean checkCollision(int index){
        return !this.emptyCells.contains(index);
    }
  
    public void userMove(Gamer gamer, int row, int col){
        gamer.incrementNumberOfMoves();
        this.board.get(row).set(col, gamer.getSymbol());
    }

    boolean checkDiagonal() {
        boolean primaryDiagonalResult = true;
        boolean secondaryDiagonalResult = true;
        char primaryDiagonalSymbol = this.board.getFirst().getFirst();
        char secondaryDiagonalSymbol = this.board.getFirst().getLast();

        for(int row = 1; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                if(row == col){
                    primaryDiagonalResult = primaryDiagonalResult & (primaryDiagonalSymbol == this.board.get(row).get(col));
                }
                else if (col == (BOARD_SIZE-1-row)){
                    secondaryDiagonalResult = secondaryDiagonalResult & (secondaryDiagonalSymbol == this.board.get(row).get(col));
                }
            }
            if(primaryDiagonalResult){
                this.winningSym = primaryDiagonalSymbol;
                break;
            }
            if(secondaryDiagonalResult){
                this.winningSym = secondaryDiagonalSymbol;
                break;
            }
        }
        return primaryDiagonalResult | secondaryDiagonalResult;
    }

    boolean checkVertical() {
        boolean result = true;
        char currentSymbol = ' ';
        for(int col = 0; col < BOARD_SIZE; col++){
            currentSymbol = this.board.getFirst().get(col);
            result = true;
            for(int row = 0; row < BOARD_SIZE; row++){
                result = result & (this.board.get(row).get(col) == currentSymbol);
            }
            if(result){
                this.winningSym = currentSymbol;
                break;
            }
        }
        return result;
    }

    boolean checkHorizontal() {
        boolean result = true;
        char currentSymbol = ' ';
        for(int row = 0; row < BOARD_SIZE; row++){
            currentSymbol = this.board.get(row).getFirst();
            result = true;
            for(int col = 0 ; col < BOARD_SIZE; col++){
                result = result & (this.board.get(row).get(col) == currentSymbol);
            }
            if(result){
                this.winningSym = currentSymbol;
                break;
            }
        }
        return result;
    }

    public void printBoard() {
        StringBuilder cliResult = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (col != (BOARD_SIZE - 1)) {
                  cliResult.append(board.get(row).get(col)).append(" | ");
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
        System.out.println(cliResult.toString());
    }
}
