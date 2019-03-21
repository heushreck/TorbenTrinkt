package com.master.drinkmaster3000.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.master.drinkmaster3000.Blackstories.black_wahl;
import com.master.drinkmaster3000.Stoff.stoff_wahl;
import com.master.drinkmaster3000.R;

public class main_langeweile extends AppCompatActivity {

    Button black;
    Button stoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_langeweile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        black = findViewById(R.id.lang_black);
        stoff = findViewById(R.id.lang_stoff);

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),black_wahl.class);
                startActivity(i);
            }
        });

        stoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),stoff_wahl.class);
                startActivity(i);
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
