package algermo.honorsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import 	android.view.MotionEvent;
import 	android.graphics.Color;

public class ThreeByThreeGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_board);

        final Button btn0 = (Button) findViewById(R.id.button0);
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
