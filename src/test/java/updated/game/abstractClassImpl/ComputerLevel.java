package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Game;
import updated.game.abstractClasses.Gamer;

import java.util.List;

/**
 Using Template Design Pattern for Selecting Computer Level as:
 Easy: with iterative search
 Medium: with Random Index
 Hard: search index such that it blocks opponents winning chances

 ComputerLevel is base abstract class
    * It has all the methods implemented from gamer class
    * Add it's own abstract method getEmptyIndex
    * getEmptyIndex  is our template method which will be defined by sub-classes.

 Alternate Design Patterns:
    * Strategy
    * Sub-class Sandbox
    * Recursive Template
 @author NisargPipaliya
 @deprecated
 */
public abstract class ComputerLevel extends Gamer {
    ComputerLevel() {
        super();
        gamerName = "Computer";
    }

    public abstract int getEmptyIndex(Game game);
    @Override
    public void getSymbolFromUser() {
    }

    @Override
    public List<Integer> getInputFromUser(Game game) {
        int index = getEmptyIndex(game);
        game.getBoard().emptyCells.remove(index);
        return getRowAndColFromIdx(index);
    }

    @Override
    public void incrementScore() {
        this.currentScore++;
    }


}
