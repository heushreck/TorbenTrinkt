package com.master.drinkmaster3000.Main;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import com.master.drinkmaster3000.R;

public class main_andere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_andere);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        TextView text = findViewById(R.id.Liste);
        text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/uwch.ttf"));
        String s = "";
        try {
            InputStream is = getAssets().open("andere.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            s = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        text.setText(s);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}