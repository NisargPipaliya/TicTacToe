package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Game;
import updated.game.abstractClasses.Gamer;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Human extends Gamer {
    Scanner sc;
    Human(){
        super();
        sc = new Scanner(System.in);
    }

    public void getUserNameFromCli(){
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
    @Override
    public void getSymbolFromUser() {
        while (true) {
            System.out.println("\nSelect your symbol (O or X):");
            System.out.println("Press 1 for 'O', Press 2 for X.");
            String inputSym = sc.nextLine();
            if ("1".equals(inputSym)) {
                this.symbol = 'O';
                break;
            } else if ("2".equals(inputSym)) {
                this.symbol = 'X';
                break;
            } else {
                System.out.println("Kya kar raha hai bhai tu \uD83D\uDE21 ? 1 ya 2 daal ");
            }
        }
    }

    @Override
    public List<Integer> getInputFromUser(Game game) {
        int idx = 0;
        System.out.println("Where to place your symbol?  ");
        List<Integer> rowAndCol;

        while (true) {
            String s = sc.nextLine().trim();
            if ("Q".equalsIgnoreCase(s)) {
                game.quit();
            }
            if(this.validateInput(s)){
                int index = Integer.parseInt(s);
                if (!game.getBoard().checkCollision(index)) {
                    game.getBoard().emptyCells.remove(index);
                  return getRowAndColFromIdx(index);
                }
                else {
                    System.out.println("\n⚠️⚠️ The Selected Cell is already filled ⚠️⚠️");
                }
            }
        }
    }

    @Override
    public void incrementScore() {
        this.currentScore++;

    }
    public boolean validateInput(String s){
        if (s.matches("\\d{1}")) {
            int index = Integer.parseInt(s);
          if (!(index >= 1 && index <= 9)) {
            System.out.println("\nBhai 1 To 9 mai number daal \uD83D\uDE21");
            return false;
          }
        }
        else {
          System.out.println("Please Enter Numbers Only!!!");
        }
        return true;
    }
}

