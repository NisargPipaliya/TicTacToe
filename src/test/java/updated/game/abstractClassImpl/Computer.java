package updated.game.abstractClassImpl;


import updated.game.abstractClasses.Game;
import updated.game.abstractClasses.Gamer;

import java.util.List;

public abstract class Computer extends Gamer {
    Computer() {
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
        System.out.println(index);
        game.getBoard().emptyCells.remove(index);
        return getRowAndColFromIdx(index);
    }

    @Override
    public void incrementScore() {
        this.currentScore++;
    }
}