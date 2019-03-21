package com.master.drinkmaster3000.Blackstories;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.master.drinkmaster3000.R;

public class black_wahl extends AppCompatActivity {

    TextView title;

    Button r1;
    Button r2;
    Button r3;
    Button r4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_wahl);

        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);

        title = findViewById(R.id.black_titel);

        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"fonts/Moms_typewriter.ttf");
        r1.setTypeface(mytypeface);
        r1.setAllCaps(false);
        r2.setTypeface(mytypeface);
        r2.setAllCaps(false);
        r3.setTypeface(mytypeface);
        r3.setAllCaps(false);
        r4.setTypeface(mytypeface);
        r4.setAllCaps(false);
        title.setTypeface(mytypeface);
        title.setAllCaps(false);


        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),black_ingame.class);
                i.putExtra("version",0);
                startActivity(i);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),black_ingame.class);
                i.putExtra("version",2);
                startActivity(i);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),black_ingame.class);
                i.putExtra("version",3);
                startActivity(i);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),black_ingame.class);
                i.putExtra("version",4);
                startActivity(i);
            }
        });
    }
}
