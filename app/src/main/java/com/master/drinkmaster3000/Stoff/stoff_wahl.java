package com.master.drinkmaster3000.Stoff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.master.drinkmaster3000.R;

public class stoff_wahl extends AppCompatActivity {

    Button r1;
    Button r2;
    Button r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoff_wahl);

        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);




        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),stoff_ingame.class);
                i.putExtra("version",0);
                startActivity(i);

            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),stoff_ingame.class);
                i.putExtra("version",1);
                startActivity(i);

            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),stoff_ingame.class);
                i.putExtra("version",2);
                startActivity(i);

            }
        });
    }
}
