package Game;

import java.sql.SQLOutput;
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
    @Override
    public void start() {
        System.out.println("\n\nLet's Begin :)");
        System.out.println("Current Status of Board");
        printStatus();
    }

    @Override
    public void quit() {

    }

    @Override
    public boolean userMove(String userName, String userSym, int index) {
        return false;
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


