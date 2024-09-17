package updated.game.abstractClassImpl;

import updated.game.abstractClasses.Board;

public class TicTacToeBoard extends Board {
//    public static int BOARD_SIZE=3;
    TicTacToeBoard(){
        super();
    }

    public static void setBoardSize(int boardSize){
       Board.setBoardSize(boardSize);
       TicTacToeBoard.BOARD_SIZE = boardSize;
    }
}
