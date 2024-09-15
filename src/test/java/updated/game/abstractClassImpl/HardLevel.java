package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Game;
import updated.game.abstractClasses.Gamer;

public class HardLevel extends Computer {
    @Override
    public int getEmptyIndex(Game game) {
        return game.getBoard().emptyCells.iterator().next();
    }
}
