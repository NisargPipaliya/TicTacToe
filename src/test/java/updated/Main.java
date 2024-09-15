package updated;

import updated.game.abstractClassImpl.TicTacToe;
import updated.game.abstractClasses.Game;

public class Main {
    public static void main(String[] args){
        Game game = new TicTacToe();
        game.play();
    }
}
