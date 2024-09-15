package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Game;

public class EasyLevel extends ComputerLevel{
    @Override
    public int getEmptyIndex(Game game) {
        return 0;
    }
}
