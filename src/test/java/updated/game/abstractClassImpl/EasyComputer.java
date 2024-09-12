package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Game;
import updated.game.abstractClasses.Gamer;

import java.util.List;

public class EasyComputer extends Gamer {

    @Override
    public void getSymbolFromUser() {
    }

    @Override
    public List<Integer> getInputFromUser(Game game) {
        int index = game.getBoard().emptyCells.iterator().next();
        game.getBoard().emptyCells.iterator().remove();
        return getRowAndColFromIdx(index);
    }

    @Override
    public void incrementScore() {
        this.currentScore++;
    }

}
