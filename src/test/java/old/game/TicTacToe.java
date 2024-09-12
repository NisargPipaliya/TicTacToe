package old.game;

import java.security.SecureRandom;
import java.util.*;

import static java.lang.System.exit;

public class TicTacToe implements Game2Player {
    String userName;
    char userSym;
    char computerSym;
    private int currentUserSocre;
    private int currentComputerScore;
    private int numberOfMatches;
    private final int boardSize = 3;
    private List<List<Character>> board;
    private int X;
    private int O;
    private char winningSym;
    private Scanner sc;
    private Set<Integer> emptyCells;

    private boolean playGame;

    public TicTacToe() {
        this.currentUserSocre = 0;
        this.currentComputerScore = 0;
        this.numberOfMatches = 1;
        this.board = new ArrayList<List<Character>>(boardSize);

        for (int row = 0; row < boardSize; row++) {
            this.board.add(new ArrayList<>(boardSize));
            for (int col = 0; col < boardSize; col++) {
                board.get(row).add(' ');
            }
        }

        emptyCells = new LinkedHashSet<>(9);
        initEmptyCells();

        // Need to Open scanner here as if we open it locally and then close it in that method, then it will close system.in and will create problem for other scanners in the program.
        sc = new Scanner(System.in);

        playGame = true;
    }

    private void initEmptyCells() {
        SecureRandom randomNumGen = new SecureRandom();
        while (emptyCells.size() != 9) {
            int i = randomNumGen.nextInt(10);
            if (i == 0)
                i++;
            emptyCells.add(i);
        }
        winningSym = ' ';

        for (int row = 0; row < boardSize; row++) {
            this.board.add(new ArrayList<>(boardSize));
            for (int col = 0; col < boardSize; col++) {
                board.get(row).set(col, ' ');
            }
        }
    }

    @Override
    public void printInstructions() {
        System.out.println("******************************");
        System.out.printf("%20s", "Welcome \uD83D\uDC4B \uD83D\uDC4B \n");
        //
        System.out.println("⚠️Press Q to Quit on going old.game.⚠️ \n\n");
        System.out.println("The board will be as");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (j != (boardSize - 1))
                    System.out.print('X' + " | ");
                else
                    System.out.println('X');
            }
            if (i != (boardSize - 1))
                System.out.println("---------");
        }//:air

        System.out.println("\nYou are suppose to provide input as number of box you want to place your symbol");
        int k = 1;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (j != (boardSize - 1))
                    System.out.print(k + " | ");
                else
                    System.out.println(k);
                k++;
            }
            if (i != (boardSize - 1))
                System.out.println("---------");
        }
        System.out.print("******************************\n");
        System.out.println("******************************");
        System.out.println("Enter Your UserName: ");
        userName = sc.nextLine();
        getSymFromUser();
        printDetails();
        System.out.println("******************************");
    }

    public void printDetails() {
        System.out.println("******************************");
        System.out.printf("%25s", "Details Received are as: \n");
        System.out.printf("Username: %s\n", this.userName);
        System.out.printf("Symbol Selected by %s: %c\n", this.userName, this.userSym);
        System.out.println("******************************");

    }

    public void getSymFromUser() {

        while (true) {
            System.out.println("\nSelect your symbol (O or X):");
            System.out.println("Press 1 for 'O', Press 2 for X.");
            String inputSym = sc.nextLine();
            if ("1".equals(inputSym)) {
                userSym = 'O';
                computerSym = 'X';
                break;
            } else if ("2".equals(inputSym)) {
                userSym = 'X';
                computerSym = 'O';
                break;
            } else {
                System.out.println("Kya kar raha hai bhai tu \uD83D\uDE21 ? 1 ya 2 daal ");
            }
        }

    }

    public List<Integer> getInputFromUser() {
        // have two function,
        // one for input and one for validation.
        int idx = 0;
        System.out.println("Where to place your symbol?  ");
        List<Integer> rowAndCol;

        while (true) {
            String s = sc.nextLine().trim();

            if (Objects.equals(s, "q") || Objects.equals(s, "Q")) {
                quit();
            }
//            System.out.println("\n\n"+s+"\n\n");
            if (s.matches("\\d{1}")) {
                idx = Integer.parseInt(s);
                emptyCells.remove(idx);
                if (idx >= 1 && idx <= 9) {
                    rowAndCol = getRowAndColFromIdx(idx);
                    if (!checkCollision(rowAndCol.get(0), rowAndCol.get(1))) {
                        break;
                    } else {
                        System.out.println("\n⚠️⚠️ The Selected Cell is already filled ⚠️⚠️");
                    }
                } else {
                    System.out.println("\nKya kar raha hai bhai tu \uD83D\uDE21 ?");
                }
            } else {
                System.out.println("\nBhai 1 To 9 mai number daal \uD83D\uDE21");
            }
        }

        return rowAndCol;
    }

    public List<Integer> getRowAndColFromIdx(int idx) {
        List<Integer> rowAndCol = new ArrayList<>(2);
        rowAndCol.add((idx - 1) / 3);
        rowAndCol.add((idx - 1) % 3);
        return rowAndCol;
    }

    public boolean checkCollision(int row, int col) {
        return this.board.get(row).get(col) != ' ';
    }

    public boolean isGameOver() {
        if (X >= 3 || O >= 3) {
            return (checkHorizontal() || checkVertical() || checkDiagonal());
        }
        return false;
    }

    private boolean checkDiagonal() {
        boolean result = board.getFirst().getFirst() == board.get(1).get(1) && board.get(1).get(1) == board.get(2).get(2);
        result = result || board.get(0).get(2) == board.get(1).get(1) && board.get(1).get(1) == board.get(2).getFirst();
        if (result) {
            winningSym = board.get(1).get(1);
        }
        return result && winningSym != ' ';
    }

    private boolean checkVertical() {
        boolean result = board.getFirst().getFirst() == board.get(1).get(0) && board.get(1).get(0) == board.get(2).get(0);
        if (result) {
            winningSym = board.getFirst().getFirst();
            return winningSym != ' ';
        }
        result = board.getFirst().get(1) == board.get(1).get(1) && board.get(1).get(1) == board.get(2).get(1);
        if (result) {
            winningSym = board.getFirst().get(1);
            return winningSym != ' ';
        }
        result = board.getFirst().get(2) == board.get(1).get(2) && board.get(1).get(2) == board.get(2).get(2);
        if (result) {
            winningSym = board.getFirst().get(2);
            return winningSym != ' ';
        }
        return false;
    }

    public boolean checkHorizontal() {
        boolean result = board.getFirst().get(0) == board.getFirst().get(1) && board.getFirst().get(1) == board.getFirst().get(2);
        if (result) {
            winningSym = board.getFirst().getFirst();
            return winningSym != ' ';
        }
        result = board.get(1).get(0) == board.get(1).get(1) && board.get(1).get(1) == board.get(1).get(2);
        if (result) {
            winningSym = board.get(1).getFirst();
            return winningSym != ' ';


        }
        result = board.get(2).get(0) == board.get(2).get(1) && board.get(2).get(1) == board.get(2).get(2);
        if (result) {
            winningSym = board.get(2).getFirst();
            return winningSym != ' ';

        }
        return false;
    }

    @Override
    public void play() {
        // Make it do While
        this.printInstructions();
        while (playGame) {
            playGame = false;
            System.out.println("\n\nLet's Begin \uD83C\uDFB2");
            System.out.println("\n\nCurrent Status");
            printStatus();
            while (!isGameOver()) {
                System.out.printf("========== %s's Turn ==========\n", userName);
                List<Integer> rowAndCol = getInputFromUser();
                userMove(userName, userSym, rowAndCol.get(0), rowAndCol.get(1));
                System.out.println("Board");
                printStatus();
                if (isGameOver() || (X + O) == 9 ) {
                    break;
                }
                System.out.println("========== Computer's Turn ==========");
                rowAndCol = getComputerInput();
                userMove("Computer", computerSym, rowAndCol.getFirst(), rowAndCol.get(1));
                System.out.println("Board");
                printStatus();
                System.out.println("\n\n");
            }

            if (winningSym == userSym) {
                System.out.printf("You Won %s, \uD83C\uDF89\uD83D\uDCAB\n", userName);
                currentUserSocre++;
            }
            else if (winningSym == computerSym) {
                System.out.println("Winner is Computer.");
                currentComputerScore++;
            }
            else
                System.out.println("It's A Draw");
            printStats();
            playGame = askForRematch();
        }
    }

    private List<Integer> getComputerInput() {
        int idx = emptyCells.iterator().next();
        emptyCells.remove(idx);
        return getRowAndColFromIdx(idx);
    }

    @Override
    public void quit() {
        sc.close();
        System.out.println("The old.game Has Been Terminated!!!!");
        exit(0);
    }

    @Override
    public void userMove(String userName, char userSym, int row, int col) {
        if (userSym == 'X')
            X++;
        else
            O++;
        board.get(row).set(col, userSym);
    }

    @Override
    public boolean askForRematch() {
        System.out.println("Ek aur match ? (Y for Yes, N for No): ");
        while (true) {
            String rematchOpt = sc.nextLine();
            if (rematchOpt.equals("Y") || rematchOpt.equals("y")) {
                printStats();
                getSymFromUser();
                initEmptyCells();
                numberOfMatches++;
                return true;
            } else if (rematchOpt.equals("N") || rematchOpt.equals("n")) {
                return false;
            } else {
                System.out.println("Bhai Valid Input daal na.");
            }
        }
    }

    @Override
    public void printStats() { // printBoardStats
        System.out.println("******************************");
        System.out.println("Let's Look at stats till now.");
        System.out.printf("Number of Matches: %d\n", numberOfMatches);
        System.out.printf("%s's Score: %d\n", this.userName, this.currentUserSocre);
        System.out.printf("Computer's Score: %d\n", this.currentComputerScore);
        System.out.println("******************************");
    }


    @Override
    public void printStatus() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (j != (boardSize - 1))
                    System.out.print(board.get(i).get(j) + " | ");
                else
                    System.out.println(board.get(i).get(j));
            }
            if (i != (boardSize - 1))
                System.out.println("---------");
        }
    }
}