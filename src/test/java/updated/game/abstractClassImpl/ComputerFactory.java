package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Gamer;
import updated.game.constants.Level;

public class ComputerFactory {

    public static Gamer getComputer(Level level){
        if(level == Level.HARD){
            return new HardLevel();
        }else if(level == Level.MEDIUM){
            return new MediumLevel();
        }
        return new EasyLevel();
    }
}
