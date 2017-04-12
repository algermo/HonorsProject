package algermo.honorsproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/***************************************************************************************************
 * Jennifer Moon & Molly Alger
 *
 * HON499 Senior Project
 * Rookie Firefighters
 **************************************************************************************************/

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // add music to app
        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.theme);
        mPlayer.setLooping(true);
        mPlayer.start();

        // button for small field
        Button btn1 = (Button) findViewById(R.id.button3by3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tres = new Intent(HomePage.this, ThreeByThreeGame.class);
                startActivity(tres);
            }
        });

        // button for medium field
        Button btn2 = (Button) findViewById(R.id.button4by4);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quatro = new Intent(HomePage.this, FourByFourGame.class);
                startActivity(quatro);
            }
        });

        // button for how to
        Button btn3 = (Button) findViewById(R.id.btnhowtoplay);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructions = new Intent(HomePage.this, HowToPlay.class);
                startActivity(instructions);
            }
        });
    }
}
