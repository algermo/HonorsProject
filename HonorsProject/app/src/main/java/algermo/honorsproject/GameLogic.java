package algermo.honorsproject;
import java.util.Arrays;

/**
 * Created by Owner on 2/19/2017.
 */

public class GameLogic {

    private int rows;
    private int columns;
    private int[][] board;

//    public static void main(String args[]){
//        GameLogic example = new GameLogic();
//        example.place(0, 0);
//        example.printBoard();
//
//        if (example.check() == true){
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//
//        example.place(2,0);
//        example.printBoard();
//        if (example.check() == true){
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//    }

    public GameLogic (int c, int r) {
        rows = r;
        columns = c;
        board = new int[c][r];
    }

    public GameLogic (int[][] brd){
        board = brd;
        columns = brd.length;
        rows = brd[0].length;
    }

//    public GameLogic (){
//        //used only for testing thus far
//        rows = 4;
//        columns = 4;
//        board = new int[columns][rows];
//        board[1][0] = -1;
//        board[2][1] = -1;
//        board[1][2] = -1;
//        board[2][2] = -1;
//        board[3][2] = -1;
//        board[1][3] = -1;
//
//        System.out.println(Arrays.deepToString(board));
//    }

//    public void printBoard(){
//        System.out.println(Arrays.deepToString(board));
//    }

//    public void restrict(int[][] restrictions){
//        for (int i = 0; i < restrictions[0].length; i++){
//            board[restrictions[0][i]][restrictions[1][i]] = -1;
//        }
//    }

    public void place(int c, int r){
        if (board[c][r] == 0){
            board[c][r] = 1;
        } else if (board[c][r] == 1){
            board[c][r] = 0;
            //} if (board[r][c] = -1) {
            //do nothing since you can't place here
        }

    }

    public boolean check(){
        for (int i = 0; i < columns; i++){
            int sum = 0;
            for (int j = 0; j < rows; j++){
                if (board[i][j] > -1){
                    sum += board[i][j];
                }
            }
            if (sum > 1) {
                return false;
            }
        }

        for (int i = 0; i < rows; i++){
            int sum = 0;
            for (int j = 0; j < columns; j++){
                if (board[j][i] > -1){
                    sum += board[j][i];
                }
            }
            if (sum > 1) {
                return false;
            }
        }

        return true;
    }

}
