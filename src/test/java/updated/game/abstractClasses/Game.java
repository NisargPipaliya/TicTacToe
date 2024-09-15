package updated.game.abstractClasses;
import lombok.Getter;
import updated.game.constants.Level;

import java.util.Scanner;

@Getter
public abstract class Game {
    protected int numberOfMatches;
    protected boolean playGame;
    protected Board board;

    public Game(){
        numberOfMatches = 1;
        playGame = true;
    }

    public abstract void printInstructions();
    public abstract void play();
    public abstract boolean askForRematch();
    public abstract void printStats();
    public abstract void quit();
    public abstract boolean isGameOver();
    public Level askForLevel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("*".repeat(30));
        System.out.println("Enter 1 for Easy, 2 for Medium, 3 for Hard");
        while (true){
            String s = sc.nextLine().trim();
            switch (s) {
                case "1" -> {
                    return Level.EASY;
                }
                case "2" -> {
                    return Level.MEDIUM;
                }
                case "3" -> {
                    return Level.HARD;
                }
                default -> System.out.println("!! Please Enter Valid Input !!");
            }
        }
    }
}
