package algermo.honorsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button btn1 = (Button) findViewById(R.id.button3by3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tres = new Intent(HomePage.this, ThreeByThreeGame.class);
                startActivity(tres);
            }
        });

        Button btn2 = (Button) findViewById(R.id.button4by4);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quatro = new Intent(HomePage.this, FourByFourGame.class);
                startActivity(quatro);
            }
        });

        Button btn3 = (Button) findViewById(R.id.btnhowtoplay);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructions = new Intent(HomePage.this, HowToPlay.class);
                startActivity(instructions);
            }
        });
    }
}
