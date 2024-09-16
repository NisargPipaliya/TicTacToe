package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Game;
import updated.game.abstractClasses.Gamer;

import java.util.List;

public class EasyLevel extends Computer {
    @Override
    public int getEmptyIndex(Game game) {
        int index = 0;
        for(var row : game.getBoard().getBoard()){
            for(var col:row){
                index++;
                if(col == game.getBoard().EMPTY_SPACE){
                    return index;
                }
            }
        }
        return 0;
    }
}
