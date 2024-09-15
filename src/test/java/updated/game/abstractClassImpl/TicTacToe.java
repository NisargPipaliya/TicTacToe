package updated.game.abstractClassImpl;

import updated.game.abstractClasses.*;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class TicTacToe extends Game {
    Human human;
    Gamer computer;
    private final char SYMBOL_X = 'X';
    private final char SYMBOL_O = 'O';
    private final String SYMBOL_BAR = " | ";
    public TicTacToe(){
        super();
        human = new Human();
        computer = new MediumLevel();
        this.board = new TicTacToeBoard();

    }

    @Override
    public void printInstructions() {
        StringBuilder cliResult = new StringBuilder();
        cliResult.append("*".repeat(30));
        cliResult.append("%20s".formatted("Welcome \uD83D\uDC4B \uD83D\uDC4B \n"));
        cliResult.append("⚠️Press Q to Quit on going old.game.⚠️ \n\n");
        cliResult.append("The board will be as\n");
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (j != (Board.BOARD_SIZE - 1))
                    cliResult.append(SYMBOL_X).append(SYMBOL_BAR);
                else
                    cliResult.append(SYMBOL_X).append("\n");
            }
            if (i != (Board.BOARD_SIZE - 1))
                cliResult.append("-".repeat(TicTacToeBoard.BOARD_SIZE*TicTacToeBoard.BOARD_SIZE)).append("\n");
        }

        cliResult.append("\nYou are suppose to provide input as number of box you want to place your symbol\n");
        int k = 1;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (j != (Board.BOARD_SIZE - 1))
                    cliResult.append(k).append(SYMBOL_BAR);
                else
                    cliResult.append(k).append("\n");
                k++;
            }
            if (i != (Board.BOARD_SIZE - 1))
                cliResult.append("-".repeat(TicTacToeBoard.BOARD_SIZE*TicTacToeBoard.BOARD_SIZE)).append("\n");
        }
        cliResult.append("*".repeat(30)).append("\n");
        cliResult.append("*".repeat(30)).append("\n");

        System.out.println(cliResult);

        human.getUserNameFromCli();
        human.getSymbolFromUser();
        computer.setSymbol((human.getSymbol() == SYMBOL_O) ? SYMBOL_X : SYMBOL_O);
        human.printDetails();
        System.out.println("*".repeat(30));
    }

    @Override
    public void play() {
        this.printInstructions();
        do{
            System.out.println("\n\nLet's Begin \uD83C\uDFB2");
            System.out.println("\n\nCurrent Status");
            this.board.printBoard();

            while (!isGameOver()) {
                if(human.getSymbol() == SYMBOL_X){
                    this.gamerMove(human);
                }else{
                    this.gamerMove(computer);
                }
                if (isGameOver() || (this.board.getEmptyCells().isEmpty()) ) {
                    break;
                }
                if(human.getSymbol() == SYMBOL_O){
                    this.gamerMove(human);
                }else{
                    this.gamerMove(computer);
                }
                System.out.println("\n\n");
            }
            this.declareWinner();
            this.printStats();
            this.playGame = this.askForRematch();
        }while (this.playGame);

    }
    public void gamerMove(Gamer gamer){
        System.out.printf("========== %s's Turn ==========\n", gamer.getGamerName());
        List<Integer> rowAndCol = gamer.getInputFromUser(this);
        board.userMove(gamer, rowAndCol.get(0), rowAndCol.get(1));
        System.out.println("Board");
        this.board.printBoard();
    }

    public void declareWinner(){
        char winningSym = this.board.getWinningSym();
        if (winningSym == this.human.getSymbol() ) {
            System.out.printf("You Won %s, \uD83C\uDF89\uD83D\uDCAB\n", this.human.getGamerName());
            this.human.incrementScore();
        }
        else if (winningSym == this.computer.getSymbol()) {
            System.out.println("Winner is MediumLevel.");
            this.computer.incrementScore();
        }
        else {
            System.out.println("It's A Draw");
        }
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
                human.getSymbolFromUser();
                computer.setSymbol((human.getSymbol() == SYMBOL_O) ? SYMBOL_X : SYMBOL_O);
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
        String cliResult = "*".repeat(30)+"\n" +
                "Let's Look at stats till now.\n" +
                "Number of Matches: %d\n".formatted(numberOfMatches) +
                "%s's Score: %d\n".formatted(human.getGamerName(), human.getCurrentScore()) +
                "MediumLevel's Score: %d\n".formatted(computer.getCurrentScore()) +
                "*".repeat(30)+"\n";
        System.out.println(cliResult);
    }

    @Override
    public void quit() {
        System.out.println("The Game Has Been Terminated!!!!");
        exit(0);

    }

    @Override
    public boolean isGameOver() {
        if (this.board.getEmptyCells().size() <= (TicTacToeBoard.BOARD_SIZE * 2 -1)) {
            return (this.board.checkRowAndColumn()   || this.board.checkDiagonal());
        }
        return false;
    }
}
