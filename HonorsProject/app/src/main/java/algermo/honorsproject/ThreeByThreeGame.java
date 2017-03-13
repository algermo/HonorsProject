package algermo.honorsproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.MotionEvent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class ThreeByThreeGame extends AppCompatActivity {

    GameLogic game;
    public ArrayList<int[][]> allBoards;
    public ArrayList<int[]> rookPolys;
    final Button[][] buttons = new Button[3][3];
    public int[][] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_board);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_three_board, null);
        setContentView(root);

        allBoards = new ArrayList<int[][]>();
        rookPolys = new ArrayList<int[]>();

        readFile();

        Random rand = new Random();
        int boardNum = rand.nextInt(allBoards.size());
        board = new int[3][3];
        board = allBoards.get(boardNum);

        game = new GameLogic(board, rookPolys.get(boardNum));

        final Button btn0 = (Button) findViewById(R.id.button0);
        buttons[0][0] = btn0;
        final Button btn1 = (Button) findViewById(R.id.button1);
        buttons[0][1] = btn1;
        final Button btn2 = (Button) findViewById(R.id.button2);
        buttons[0][2] = btn2;
        final Button btn3 = (Button) findViewById(R.id.button3);
        buttons[1][0] = btn3;
        final Button btn4 = (Button) findViewById(R.id.button4);
        buttons[1][1] = btn4;
        final Button btn5 = (Button) findViewById(R.id.button5);
        buttons[1][2] = btn5;
        final Button btn6 = (Button) findViewById(R.id.button6);
        buttons[2][0] = btn6;
        final Button btn7 = (Button) findViewById(R.id.button7);
        buttons[2][1] = btn7;
        final Button btn8 = (Button) findViewById(R.id.button8);
        buttons[2][2] = btn8;

        final TextView numLeft = (TextView) findViewById(R.id.spotsLeft);
        numLeft.setText(game.getRemaining());

        final int[][] tempBoard1 = board;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == -1) {
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setBackgroundResource(R.drawable.fire);
                }
                final int x = i;
                final int y = j;
                buttons[i][j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            //buttons[x][y].setEnabled(false);
                            SharedPreferences pref = getSharedPreferences("btnClk"+
                                    Integer.toString(buttons[x][y].getId()), MODE_PRIVATE);
                            boolean activated = pref.getBoolean("activated", false);
                            if(activated == false) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putBoolean("activated", true);
                            }
                            buttons[x][y].setBackgroundColor(Color.BLUE);
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
                    //Toast toast;

                    if(game.check(tempBoard)) {
                        if(game.answer(tempBoard)) {
                            text = "Correct.";
                            /*toast = Toast.makeText(context, text, duration);
                            toast.show();*/
                        } else {
                            text = "You've already tried that.";
                           /* toast = Toast.makeText(context, text, duration);*/
                        }
                    } else {
                        text = "Incorrect.";
                       /* toast = Toast.makeText(context, text, duration);*/
                    }
                    numLeft.setText(text);
                }
                return false;
            }
        });


    }

    public void checkButtons() {

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!buttons[i][j].isEnabled() && board[i][j] == 0) {
                    board[i][j] = 1;
                }
            }
        }

    }

    public void readFile() {

        //read the file
        InputStream inStream = getApplicationContext().getResources().openRawResource(R.raw.x3);
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

                    for (int i = 1; i < (boardVals.length); i++) {
                        boardVals[i - 1] = Integer.parseInt(part[i]);
                    }

                    int[] poly = new int[size + 1];

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

        System.out.print(allBoards);

    }

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
