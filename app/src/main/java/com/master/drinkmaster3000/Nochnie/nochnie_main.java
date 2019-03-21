package com.master.drinkmaster3000.Nochnie;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.master.drinkmaster3000.R;

import java.util.concurrent.ExecutionException;

public class nochnie_main extends AppCompatActivity {

    CheckBox c1;
    CheckBox c2;
    CheckBox c3;

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nochnie_main);

        c1 = findViewById(R.id.nochnie_normal);
        c2 = findViewById(R.id.nochnie_sexy);
        c3 = findViewById(R.id.nochnie_sehr_sexy);

        start = findViewById(R.id.nochnie_los);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = getNumber();
                if (n>=0){
                    Intent i = new Intent(getApplicationContext(),nochnie_ingame.class);
                    i.putExtra("version",n);
                    startActivity(i);
                }else{
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Ja du musst schon was anklicken...", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
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
