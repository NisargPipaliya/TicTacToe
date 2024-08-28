package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe implements Game2Player{
    String userName;
    char userSym;
    private int currentSocre;
    private int numberOfRematch;
    private final int boardSize = 3;
    private List<List<Character>> board;
    private int X;
    private int O;

    public TicTacToe(){
        this.currentSocre = 0;
        this.numberOfRematch = 0;
        this.board = new ArrayList<List<Character>>(boardSize);
        for(int i = 0; i < boardSize ; i++){
            this.board.add(new ArrayList<>(boardSize));
            for(int j = 0; j < boardSize; j++){
                board.get(i).add(' ');
            }
        }
    }
    @Override
    public void printInstructions() {
        System.out.println("******************************");
        System.out.printf("%20s","Welcome\n");
        System.out.println("Press Q to Quit on going game.\n\n");
        System.out.println("The board will be as");
        for(int i = 0;i < boardSize; i++){
            for(int j = 0; j <boardSize; j++) {
                if(j !=(boardSize-1))
                    System.out.print('X' + " | ");
                else
                    System.out.println('X');
            }
            if(i != (boardSize-1))
                System.out.println("---------");
        }

        System.out.println("\nYou are suppose to provide input as number of box you want to place your symbol");
        int k = 1;
        for(int i = 0;i < boardSize; i++){
            for(int j = 0; j <boardSize; j++) {
                if(j !=(boardSize-1))
                    System.out.print( k + " | ");
                else
                    System.out.println(k);
                k++;
            }
            if(i != (boardSize-1))
                System.out.println("---------");
        }
        System.out.print("******************************\n");
        System.out.println("******************************");
        System.out.println("Enter Your UserName: ");
        Scanner sc = new Scanner(System.in);
        userName = sc.nextLine();
        getSymFromUser();
        printDetails();
        System.out.println("******************************");
        sc.close();
    }
    public void printDetails(){
        System.out.println("******************************");
        System.out.printf("%25s","Details Received are as: \n");
        System.out.printf("Username: %s\n",this.userName);
        System.out.printf("Symbol Selected by %s: %c\n",this.userName,this.userSym);
        System.out.println("******************************");

    }
    public void getSymFromUser(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nSelect your symbol (O or X):");
            System.out.println("Press 1 for 'O', Press 2 for X.");
            int inputSym = sc.nextInt();
            if (inputSym == 1) {
                userSym = 'O';
                break;
            } else if (inputSym == 2) {
                userSym = 'X';
                break;
            } else {
                System.out.println("Please Enter Valid Symbol!!!");
            }
        }
        sc.close();
    }

    public List<Integer> getInputFromUser(){
        int idx = 0;
        System.out.println("Where to place your symbol?  ");
        List<Integer> rowAndCol;
        Scanner sc = new Scanner(System.in);
        while(true) {
            idx = sc.nextInt();
            if(idx >=1 && idx <=9){
                rowAndCol = getRowAndColFromIdx(idx);
                if(!checkCollision(rowAndCol.get(0), rowAndCol.get(1))){
                    break;
                }else{
                    System.out.println("\n!!! The Selected Cell is already filled !!!");
                }
            }else{
                System.out.println("\n!!! Please Select Valid Index");
            }
        }
        sc.close();
        return rowAndCol;
    }

    public List<Integer> getRowAndColFromIdx(int idx){
        List<Integer> rowAndCol =  new ArrayList<>(2);
        rowAndCol.add((idx-1)/3);
        rowAndCol.add((idx-1)%3);
        return rowAndCol;
    }
    public boolean checkCollision(int row, int col){
        return this.board.get(row).get(col) != ' ';
    }

    public boolean isGameOver(){
        if(X >=3 || O >= 3){
            return checkHorizontal() || checkVertical() || checkDiagonal(); 
        }
        return false;
    }

    private boolean checkDiagonal() {
    }

    private boolean checkVertical() {
    }

    public boolean checkHorizontal(){
        for(int row = 0; row < boardSize; row++){
            for(int col = 0; col < boardSize; col++){
                
            }
        }
    }
    @Override
    public void start() {
        System.out.println("\n\nLet's Begin :)");
        System.out.println("Current Status of Board");
        while(isGameOver()) {
            printStatus();
            List<Integer> rowAndCol = getInputFromUser();
            userMove(userName, userSym, rowAndCol.get(0), rowAndCol.get(1));
        }


    }

    @Override
    public void quit() {

    }

    @Override
    public void userMove(String userName, char userSym, int row, int col) {
        if(userSym == 'X')
            X++;
        else 
            O++;
        board.get(row).set(col,userSym);
    }

    @Override
    public boolean rematch() {
        return false;
    }

    @Override
    public void printStatus() {
        for(int i = 0;i < boardSize; i++){
            for(int j = 0; j <boardSize; j++) {
                if(j !=(boardSize-1))
                    System.out.print(board.get(i).get(j) + " | ");
                else
                    System.out.println(board.get(i).get(j));
            }
            if(i != (boardSize-1))
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


