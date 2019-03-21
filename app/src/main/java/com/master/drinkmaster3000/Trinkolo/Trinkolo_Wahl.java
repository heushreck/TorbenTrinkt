package com.master.drinkmaster3000.Trinkolo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

import com.master.drinkmaster3000.R;

public class Trinkolo_Wahl extends AppCompatActivity {

    CheckBox c1;
    CheckBox c2;
    CheckBox c3;

    Button start;
    SeekBar h;

    String[] names;

    int hard = 3;

    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinkolo__wahl);

        names = getIntent().getStringArrayExtra("names");



        c1 = findViewById(R.id.nochnie_normal);
        c2 = findViewById(R.id.checkBox_bar);
        c3 = findViewById(R.id.nochnie_sexy);

        h = findViewById(R.id.hard);


        start = findViewById(R.id.trinkolo_wahl_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = getNumber();
                if (n>=0){
                    Intent i = new Intent(getApplicationContext(),Trinkolo_InGame.class);
                    i.putExtra("modus",n);
                    i.putExtra("names",names);
                    i.putExtra("hard",hard);
                    startActivity(i);
                }else{
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Ja du musst schon was anklicken...", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
            }
        });

        h.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hard = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public int getNumber(){
        if (c1.isChecked()){
            if (c2.isChecked()){
                if (c3.isChecked()){
                    return 3;
                }
                return 1;
            }else{
                if (c3.isChecked()){
                    return 2;
                }
                return 0;
            }
        }else if(c2.isChecked()){
            if (c3.isChecked()){
                return 5;
            }
            return 4;
        }else if(c3.isChecked()){
            return 6;
        }
        return -1;
    }
}
