package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Game;
import updated.game.abstractClasses.Gamer;

import java.util.List;
import java.util.Scanner;

public class Human extends Gamer {
    private final char SYMBOL_X = 'X';
    private final char SYMBOL_O = 'O';
    private final String QUIT_SYMBOL = "Q";
    Scanner sc;
    Human(){
        super();
        sc = new Scanner(System.in);
    }


    @Override
    public void getSymbolFromUser() {
        String initInst ="\nSelect your symbol (%c or %c):\n".formatted(SYMBOL_O,SYMBOL_X)
                        +"Press 1 for %c, Press 2 for %c.\n".formatted(SYMBOL_O,SYMBOL_X);
        String inputError = "Kya kar raha hai bhai tu \uD83D\uDE21 ? 1 ya 2 daal";
        while (true) {
            System.out.println(initInst);
            String inputSym = sc.nextLine();
            if ("1".equals(inputSym)) {
                this.symbol = SYMBOL_O;
                break;
            } else if ("2".equals(inputSym)) {
                this.symbol = SYMBOL_X;
                break;
            } else {
                System.out.println(inputError);
            }
        }
    }

    @Override
    public List<Integer> getInputFromUser(Game game) {
        String inputError = "\n⚠️⚠️ The Selected Cell is already filled ⚠️⚠️";
        System.out.println("Where to place your symbol?  ");
        while (true) {
            String s = sc.nextLine().trim();
            if (QUIT_SYMBOL.equalsIgnoreCase(s)) {
                game.quit();
            }
            if(this.validateInput(s)){
                int index = Integer.parseInt(s);
                if (!game.getBoard().checkCollision(index)) {
                    game.getBoard().emptyCells.remove(index);
                  return getRowAndColFromIdx(index);
                }
                else {
                    System.out.println(inputError);
                }
            }
        }
    }

    @Override
    public void incrementScore() {
        this.currentScore++;

    }

    private int getInputSize(){
        int size = 0;
        int temp = TicTacToeBoard.BOARD_SIZE * TicTacToeBoard.BOARD_SIZE;
        while(temp != 0){
            temp /= 10;
            size++;
        }
        return size;
    }

    public boolean validateInput(String s){
        if (s.matches("\\d{%d}".formatted(getInputSize()))) {
            int index = Integer.parseInt(s);
            int size = TicTacToeBoard.BOARD_SIZE * TicTacToeBoard.BOARD_SIZE;
          if (!(index >= 1 && index <= size)) {
            System.out.printf("\nBhai 1 To %d mai number daal \uD83D\uDE21\n",size);
            return false;
          }
        }
        else {
          System.out.println("Please Enter Numbers Only!!!");
        }
        return true;
    }
}

