package algermo.honorsproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.MotionEvent;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/***************************************************************************************************
 * Jennifer Moon & Molly Alger
 *
 * HON499 Senior Project
 * Rookie Firefighters
 **************************************************************************************************/

public class FourByFourGame extends AppCompatActivity {

    /* instance of the game logic */
    GameLogic game;

    /* array containing all possible boards */
    private ArrayList<int[][]> allBoards;

    /* array containing the values of the rook polynomials */
    private ArrayList<int[]> rookPolys;

    /* array of buttons for the board */
    final Button[][] buttons = new Button[4][4];

    /* array representing the board */
    private int[][] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_board);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_four_board, null);
        setContentView(root);

        allBoards = new ArrayList<int[][]>();
        rookPolys = new ArrayList<int[]>();

        readFile();

        Random rand = new Random();
        int boardNum = rand.nextInt(allBoards.size());
        board = new int[4][4];
        board = allBoards.get(boardNum);

        game = new GameLogic(board, rookPolys.get(boardNum));

        final Button btn0 = (Button) findViewById(R.id.button0);
        buttons[0][0] = btn0;
        final Button btn1 = (Button) findViewById(R.id.button1);
        buttons[0][1] = btn1;
        final Button btn2 = (Button) findViewById(R.id.button2);
        buttons[0][2] = btn2;
        final Button btn3 = (Button) findViewById(R.id.button3);
        buttons[0][3] = btn3;
        final Button btn4 = (Button) findViewById(R.id.button4);
        buttons[1][0] = btn4;
        final Button btn5 = (Button) findViewById(R.id.button5);
        buttons[1][1] = btn5;
        final Button btn6 = (Button) findViewById(R.id.button6);
        buttons[1][2] = btn6;
        final Button btn7 = (Button) findViewById(R.id.button7);
        buttons[1][3] = btn7;
        final Button btn8 = (Button) findViewById(R.id.button8);
        buttons[2][0] = btn8;
        final Button btn9 = (Button) findViewById(R.id.button9);
        buttons[2][1] = btn9;
        final Button btn10 = (Button) findViewById(R.id.button10);
        buttons[2][2] = btn10;
        final Button btn11 = (Button) findViewById(R.id.button11);
        buttons[2][3] = btn11;
        final Button btn12 = (Button) findViewById(R.id.button12);
        buttons[3][0] = btn12;
        final Button btn13 = (Button) findViewById(R.id.button13);
        buttons[3][1] = btn13;
        final Button btn14 = (Button) findViewById(R.id.button14);
        buttons[3][2] = btn14;
        final Button btn15 = (Button) findViewById(R.id.button15);
        buttons[3][3] = btn15;

        final TextView numLeft = (TextView) findViewById(R.id.spotsLeft);
        numLeft.setText(game.getRemaining());

        final int[][] tempBoard1 = board;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] == -1) {
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setBackgroundResource(R.drawable.fire);
                } else {
                    buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
                }
                final int x = i;
                final int y = j;
                buttons[i][j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            buttons[x][y].setEnabled(false);
                            buttons[x][y].setBackgroundResource(R.drawable.firehat);
                            checkButtons();
                        }
                        return false;
                    }

                });
            }
        }

        final int[][] tempBoard = board;

        final Button checkBtn = (Button) findViewById(R.id.check);
        checkBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    int duration = Toast.LENGTH_SHORT;
                    Context context = getApplicationContext();
                    CharSequence text;
                    Toast toast;

                    checkButtons();
                    int[][] tempBoard = board;

                    for (int i = 0; i < board.length; i ++){
                        for (int j = 0; j < board.length; j ++){
                            System.out.print(board[i][j]);
                        }
                        System.out.println("");
                    }

                    for (int i = 0; i < tempBoard.length; i ++){
                        for (int j = 0; j < tempBoard.length; j ++){
                            System.out.print(tempBoard[i][j]);
                        }
                        System.out.println("");
                    }

                    if(game.check(tempBoard)) {
                        if(game.answer(tempBoard)) {
                            text = "Great!";
                            toast = Toast.makeText(context, text, duration);
                            toast.show();
                            numLeft.setText(game.getRemaining());
                        } else {
                            text = "You already placed them like that. Try placing them a different way!";
                            toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    } else {
                        text = "Oops! Try again!";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return false;
            }
        });

        final Button resetBtn = (Button) findViewById(R.id.reset);
        resetBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board.length; j++) {
                            if(board[i][j] != -1) {
                                board[i][j] = 0;
                                buttons[i][j].setEnabled(true);
                                buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
                            }
                        }
                    }
                }
                return false;
            }
        });


    }

    /***********************************************************************************************
     * Determines if a button has been selected. If it has, set the board array space for it
     * to one.
     **********************************************************************************************/
    public void checkButtons() {

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(!buttons[i][j].isEnabled() && board[i][j] == 0) {
                    board[i][j] = 1;
                }
            }
        }

    }

    /***********************************************************************************************
     * Reads in the file that contains all the possible board states
     **********************************************************************************************/
    public void readFile() {

        //read the file
        InputStream inStream = getApplicationContext().getResources().openRawResource(R.raw.x4);
        InputStreamReader inputreader = new InputStreamReader(inStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {

            try {
                while ((line = buffreader.readLine()) != null) {

                    String[] part = line.split(" ");
                    int size = Integer.parseInt(part[0]);
                    int[] boardVals = new int[size * size];

                    for (int i = 1; i < (boardVals.length + 1); i++) {
                        boardVals[i - 1] = Integer.parseInt(part[i]);
                    }

                    int[] poly = new int[/*size +*/ 1];

                    int count = 0;
                    for(int j = (size * size) + 1; j < part.length; j++) {
                        poly[count] = Integer.parseInt(part[j]);
                        count++;
                    }
                    rookPolys.add(poly);
                    createBoard(size, boardVals);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***********************************************************************************************
     * Create the board array based on contents of the file.
     *
     * @param size the size of the board from the file
     * @param values the locations of restrictions from the file
     **********************************************************************************************/
    public void createBoard(int size, int[] values){

        int count = 0;
        int[][] board = new int[size][size];

        for(int i = 0; i <= size-1; i++) {
            for(int j = 0; j <= size-1; j++) {
                board[i][j] = values[count];
                if(board[i][j] == 1) {
                    board[i][j] = -1;
                }
                count++;
            }
        }

        allBoards.add(board);

    }
}
