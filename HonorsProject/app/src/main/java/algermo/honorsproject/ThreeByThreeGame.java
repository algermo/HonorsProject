package algermo.honorsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.MotionEvent;
import android.graphics.Color;
import android.view.LayoutInflater;

public class ThreeByThreeGame extends AppCompatActivity {

    GameLogic game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_board);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_three_board, null);
        setContentView(root);

        int[][] board = new int[3][3];
        board[1][0] = -1;
        board[2][1] = -1;
        board[1][2] = -1;
        board[2][2] = -1;

        game = new GameLogic(board);

        Button[][] buttons = new Button[3][3];

        final Button btn0 = (Button) findViewById(R.id.button0);
        btn0.setTag("0");
        final Button btn1 = (Button) findViewById(R.id.button1);
        btn0.setTag("1");
        final Button btn2 = (Button) findViewById(R.id.button2);
        btn0.setTag("2");
        final Button btn3 = (Button) findViewById(R.id.button3);
        btn0.setTag("3");
        final Button btn4 = (Button) findViewById(R.id.button4);
        btn0.setTag("4");
        final Button btn5 = (Button) findViewById(R.id.button5);
        btn0.setTag("5");
        final Button btn6 = (Button) findViewById(R.id.button6);
        btn0.setTag("6");
        final Button btn7 = (Button) findViewById(R.id.button7);
        btn0.setTag("7");
        final Button btn8 = (Button) findViewById(R.id.button8);
        btn0.setTag("8");

        int count = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j] = (Button) root.findViewWithTag(Integer.toString(count));

                /*if(board[i][j] == -1) {
                    buttons[i][j].setBackgroundColor(Color.RED);
                    buttons[i][j].setEnabled(false);
                }*/
                count++;
            }
        }

        btn0.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    btn0.setBackgroundColor(Color.RED);
                }
                return false;
            }

        });
    }
}
