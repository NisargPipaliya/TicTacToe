package Game;

import java.security.SecureRandom;
import java.util.*;

import static java.lang.System.exit;

public class TicTacToe implements Game2Player {
    String userName;
    char userSym;
    char computerSym;
    private int currentSocre;
    private int numberOfRematch;
    private final int boardSize = 3;
    private List<List<Character>> board;
    private int X;
    private int O;
    private char winningSym;
    private Scanner sc;
    private Set<Integer> emptyCells;

    public TicTacToe() {
        this.currentSocre = 0;
        this.numberOfRematch = 0;
        this.board = new ArrayList<List<Character>>(boardSize);
        for (int i = 0; i < boardSize; i++) {
            this.board.add(new ArrayList<>(boardSize));
            for (int j = 0; j < boardSize; j++) {
                board.get(i).add(' ');
            }
        }

        emptyCells = new LinkedHashSet<>(9);
        initEmptyCells();
        winningSym = ' ';
        sc = new Scanner(System.in);
    }

    private void initEmptyCells() {
        SecureRandom randomNumGen = new SecureRandom();
        while (emptyCells.size() != 9) {
            int i = randomNumGen.nextInt(10);
            if(i == 0)
                i++;
            emptyCells.add(i);
        }

    }

    @Override
    public void printInstructions() {
        System.out.println("******************************");
        System.out.printf("%20s", "Welcome\n");
        System.out.println("Press Q to Quit on going game.\n\n");
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
        }

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
            int inputSym = sc.nextInt();
            sc.nextLine();
            if (inputSym == 1) {
                userSym = 'O';
                computerSym = 'X';
                break;
            } else if (inputSym == 2) {
                userSym = 'X';
                computerSym = 'O';
                break;
            } else {
                System.out.println("Please Enter Valid Symbol!!!");
            }
        }

    }

    public List<Integer> getInputFromUser() {
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
                idx = Integer.parseInt(s.trim());
                emptyCells.remove(idx);
                if (idx >= 1 && idx <= 9) {
                    rowAndCol = getRowAndColFromIdx(idx);
                    if (!checkCollision(rowAndCol.get(0), rowAndCol.get(1))) {
                        break;
                    } else {
                        System.out.println("\n!!! The Selected Cell is already filled !!!");
                    }
                } else {
                    System.out.println("\n!!! Please Select Valid Index");
                }
            } else {
                System.out.println("\n!!! Please Select Valid Index2");
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
            return !(checkHorizontal() || checkVertical() || checkDiagonal());
        }
        return true;
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
    public void start() {
        System.out.println("\n\nLet's Begin :)");
        System.out.println("\n\nCurrent Status");
        printStatus();
        while (isGameOver()) {

            List<Integer> rowAndCol = getInputFromUser();
            userMove(userName, userSym, rowAndCol.get(0), rowAndCol.get(1));
            System.out.println("Board");
            printStatus();
            if((X+O) == 9 || !isGameOver()){
                isGameOver();
                break;
            }
            System.out.println("Computer's Turn");
            rowAndCol = getComputerInput();
            userMove("Computer", computerSym, rowAndCol.getFirst(), rowAndCol.get(1));
            System.out.println("Board");
            printStatus();
            System.out.println("\n\n");
        }

        if (winningSym == userSym)
            System.out.printf("Winner is  %s\n", userName);
        else if(winningSym == computerSym)
            System.out.println("Winner is Computer.");
        else
            System.out.println("It's A Draw");

    }

    private List<Integer> getComputerInput() {
        int idx = emptyCells.iterator().next();
        emptyCells.remove(idx);
        List<Integer> resultIdx = new ArrayList<>(2);
        resultIdx.add((idx-1)/3);
        resultIdx.add((idx-1)%3);
        return resultIdx;
    }

    @Override
    public void quit() {
        sc.close();
        System.out.println("The Game Has Been Terminated!!!!");
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
    public boolean rematch() {
        return false;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCurrentSocre() {
        return currentSocre;
    }

    public int getNumberOfRematch() {
        return numberOfRematch;
    }

}


