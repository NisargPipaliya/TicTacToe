package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Game;
import updated.game.abstractClasses.Gamer;

import java.util.List;

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
