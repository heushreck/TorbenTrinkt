package com.master.drinkmaster3000.Privacy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.master.drinkmaster3000.R;

public class privacy_explained extends AppCompatActivity {

    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_explained);

        b = findViewById(R.id.privacy_to_ingame);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),privacy_ingame.class);
                startActivity(i);
            }
        });

    }
}
