package updated.game.abstractClassImpl;

import updated.game.abstractClasses.*;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import updated.game.abstractClasses.*;

public class TicTacToe extends Game {
    Human human;
    Gamer computer;
    public TicTacToe(){
        super();
        human = new Human();
        computer = new EasyComputer();
        this.board = new TicTacToeBoard();

    }

    @Override
    public void printInstructions() {
        System.out.println("******************************");
        System.out.printf("%20s", "Welcome \uD83D\uDC4B \uD83D\uDC4B \n");
        //
        System.out.println("⚠️Press Q to Quit on going old.game.⚠️ \n\n");
        System.out.println("The board will be as");
        StringBuilder cliResult = new StringBuilder();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (j != (Board.BOARD_SIZE - 1))
                    cliResult.append("X").append("|");
                else
                    cliResult.append('X').append("\n");
            }
            if (i != (Board.BOARD_SIZE - 1))
                cliResult.append("-".repeat(TicTacToeBoard.BOARD_SIZE*TicTacToeBoard.BOARD_SIZE)).append("\n");
        }

        cliResult.append("\nYou are suppose to provide input as number of box you want to place your symbol\n");
        int k = 1;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (j != (Board.BOARD_SIZE - 1))
                    cliResult.append(k).append(" | ");
                else
                    cliResult.append(k).append("\n");
                k++;
            }
            if (i != (Board.BOARD_SIZE - 1))
                cliResult.append("-".repeat(TicTacToeBoard.BOARD_SIZE*TicTacToeBoard.BOARD_SIZE)).append("\n");
        }
        cliResult.append("******************************\n");
        cliResult.append("\n******************************\n");
        System.out.println(cliResult.toString());

        human.getUserNameFromCli();
        human.getSymbolFromUser();
        computer.setSymbol((human.getSymbol() == 'O') ? 'X' : 'O');
        human.printDetails();
        System.out.println("******************************");
    }

    @Override
    public void play() {
        this.printInstructions();
        do{
            System.out.println("\n\nLet's Begin \uD83C\uDFB2");
            System.out.println("\n\nCurrent Status");
            this.board.printBoard();

            while (!isGameOver(human.getNumberOfMoves(),computer.getNumberOfMoves())) {
                System.out.printf("========== %s's Turn ==========\n", human.getGamerName());
                List<Integer> rowAndCol = human.getInputFromUser(this);
                board.userMove(human, rowAndCol.get(0), rowAndCol.get(1));
                System.out.println("Board");
                this.board.printBoard();
                if (isGameOver(human.getNumberOfMoves(),computer.getNumberOfMoves()) || ((human.getNumberOfMoves() + computer.getNumberOfMoves()) == (TicTacToeBoard.BOARD_SIZE * TicTacToeBoard.BOARD_SIZE)) ) {
                    System.out.println("IN IF");
                    break;
                }
                System.out.println("========== Computer's Turn ==========");
                rowAndCol = computer.getInputFromUser(this);
                this.board.userMove(computer, rowAndCol.getFirst(), rowAndCol.get(1));
                System.out.println("Board");
                this.board.printBoard();
                System.out.println("\n\n");
            }

            char winningSym = board.getWinningSym();
            if (winningSym == human.getSymbol() ) {
                System.out.printf("You Won %s, \uD83C\uDF89\uD83D\uDCAB\n", human.getGamerName());
                human.incrementScore();
            }
            else if (winningSym == computer.getSymbol()) {
                System.out.println("Winner is Computer.");
                computer.incrementScore();
            }
            else {
                System.out.println("It's A Draw");
            }
            this.printStats();
            this.playGame = this.askForRematch();
        }while (this.playGame);

    }

    @Override
    public boolean askForRematch() {
        System.out.println("Ek aur match ? (Y for Yes, N for No): ");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String rematchOpt = sc.nextLine();
            if ("Y".equalsIgnoreCase(rematchOpt)) {
                this.printStats();
                human.reset();
                computer.reset();
//                System.out.println("Human: "+human.getNumberOfMoves());
                human.getSymbolFromUser();
                computer.setSymbol((human.getSymbol() == 'O') ? 'X' : 'O');
                this.board.initEmptyCells();
                this.numberOfMatches++;
                return true;
            } else if ("N".equalsIgnoreCase(rematchOpt)) {
                return false;
            } else {
                System.out.println("Bhai Valid Input daal na.");
            }
        }
    }

    @Override
    public void printStats() {
        String cliResult = "******************************\n" +
                "Let's Look at stats till now.\n" +
                "Number of Matches: %d\n".formatted(numberOfMatches) +
                "%s's Score: %d\n".formatted(human.getGamerName(), human.getCurrentScore()) +
                "Computer's Score: %d\n".formatted(computer.getCurrentScore()) +
                "******************************\n";
        System.out.println(cliResult);
    }

    @Override
    public void quit() {
        System.out.println("The old.game Has Been Terminated!!!!");
        exit(0);

    }

    @Override
    public boolean isGameOver(int gamer1Moves, int gamer2Moves) {
        if (gamer1Moves >= 3 || gamer2Moves >= 3) {
            return (this.board.checkHorizontal() || this.board.checkVertical() || this.board.checkDiagonal());
        }
        return false;
    }
}
