package updated.game.abstractClassImpl;

import updated.game.abstractClasses .Game;
import updated.game.abstractClasses.Gamer;

import java.util.List;

public class MediumLevel extends Computer {

    MediumLevel(){
        super();
    }

    @Override
    public int getEmptyIndex(Game game) {
        return game.getBoard().emptyCells.iterator().next();
    }

}
