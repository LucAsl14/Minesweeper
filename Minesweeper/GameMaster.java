package Minesweeper;

public class GameMaster {
    public static int makeMove(Field b, int row, int col, boolean isLeft){
        int result;
        if(isLeft){
            result = b.revealClick(row, col);
        } else {
            b.flagClick(row, col);
            result = -2;
        }
        return result;
    }
}
