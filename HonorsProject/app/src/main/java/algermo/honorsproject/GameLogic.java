package algermo.honorsproject;
import java.util.ArrayList;

/***************************************************************************************************
 * Jennifer Moon & Molly Alger
 *
 * HON499 Senior Project
 * Rookie Firefighters
 **************************************************************************************************/

public class GameLogic {

    /* Number of ROWS in the board*/
    private int rows;

    /* Number of COLUMNS in the board*/
    private int columns;

    /* The BOARD */
    private int[][] board;

    /* The rook POLYNOMIAL for the corresponding board*/
    private int[] poly;

    /* The REMAINING number of ways to place 'x' rooks*/
    private int[] remaining;

    /* All ANSWERS the user has submitted*/
    private ArrayList</*int[][]*/ String> ans;


    /***********************************************************************************************
     * Constructor used to create a blank board with c columns and r rows
     *
     * @param c the number of columns
     * @param r the number of rows
     **********************************************************************************************/
    public GameLogic (int c, int r) {
        rows = r;
        columns = c;
        board = new int[c][r];
    }

    /***********************************************************************************************
     * Constructor used to create a given board with its corresponding rook polynomial
     *
     * @param brd
     * @param polynomial
     **********************************************************************************************/
    public GameLogic (int[][] brd, int[] polynomial){
        board = brd;
        columns = brd.length;
        rows = brd[0].length;

        poly = polynomial;
        remaining = polynomial;

        ans = new ArrayList</*int[][]*/ String>();
    }

    /***********************************************************************************************
     * Returns the number of ways remaining to place each number of rooks
     *
     * @return a string with the number of ways to place 'x' rooks remaining
     **********************************************************************************************/
    public String getRemaining(){
        String msg = "";

        if (poly[0] == 0) {
            msg = "You did it!";
        } else if (poly[0] == 1){
            msg = "There is " + poly[0] + " more way to place firefighters";
        } else {
            msg = "There are " + poly[0] + " more ways to place firefighters";
        }

//        for (int i = 1; i < remaining.length; i++){
//            msg = msg + "\n" + remaining[i] + " ways to place " + i;
//        }

        return msg;
    }

    /***********************************************************************************************
     * Checks to see if the answer is a distinct answer, and records the given answer if it is
     * distinct. Removes one from the respective rook number of remaining rooks to place.
     *
     * @param brd
     * @return whether or not the user has already submitted given answer
     **********************************************************************************************/
    public boolean answer(int[][] brd){

        String answer = "";

        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board.length; j ++){
                answer += board[i][j] + "";
            }
            //System.out.println("");
        }

        if (!ans.contains(/*brd*/ answer)){
            ans.add(/*brd*/ answer);

            int rooks = 0;

            for (int i = 0; i < brd.length; i++){
                for (int j = 0; j < brd.length; j++){
                    if (brd[i][j] == 1){
                        rooks++;
                    }
                }
            }


            remaining[/*rooks*/ 0]--;
            return true;
        }
        return false;
    }

    /***********************************************************************************************
     * Returns the rook polynomial of the given starting board
     *
     * @return the rook polynomial of the board
     **********************************************************************************************/
    public String getPolynomial(){
        String p = "" + poly[0];

        for (int i = 1; i < poly.length; i++){
            p = p + " + " + poly[i] + "x^" + i;
        }

        return p;
    }

    /***********************************************************************************************
     * Places (or removes) a rook in the given position
     *
     * @param c the column the rook is to be placed in
     * @param r the row the rook is to be placed in
     **********************************************************************************************/
    public void place(int c, int r){
        if (board[c][r] == 0){
            board[c][r] = 1;
        } else if (board[c][r] == 1){
            board[c][r] = 0;
            //} if (board[r][c] = -1) {
            //do nothing since you can't place here
        }
    }

    /***********************************************************************************************
     * Checks to see if the given rook configuration is nonattacking or attacking
     *
     * @return whether or not the rook configuration is nonattacking
     **********************************************************************************************/
    public boolean check(int[][] brd){
        for (int i = 0; i < columns; i++){
            int sum = 0;
            for (int j = 0; j < rows; j++){
                if (brd[i][j] > -1){
                    sum += brd[i][j];
                }
            }
            if (sum > 1) {
                return false;
            }
        }

        for (int i = 0; i < rows; i++){
            int sum = 0;
            for (int j = 0; j < columns; j++){
                if (brd[j][i] > -1){
                    sum += brd[j][i];
                }
            }
            if (sum > 1) {
                return false;
            }
        }

        int rooks = 0;

        for (int i = 0; i < brd.length; i++){
            for (int j = 0; j < brd.length; j++){
                if (brd[i][j] == 1){
                    rooks++;
                }
            }
        }

        if (rooks != rows){
            return false;
        }

        return true;
    }
}