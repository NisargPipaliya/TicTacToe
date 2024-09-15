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
                if(col == game.getBoard().EMPTY_SPACE){
                    return index;
                }
                index++;
            }
        }
        return 0;
    }
}
