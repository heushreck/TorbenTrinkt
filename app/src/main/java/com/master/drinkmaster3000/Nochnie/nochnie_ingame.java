package com.master.drinkmaster3000.Nochnie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.master.drinkmaster3000.Databank.DatabankHandler;
import com.master.drinkmaster3000.Databank.getDatabank;
import com.master.drinkmaster3000.Main.Main_Main;
import com.master.drinkmaster3000.R;
import com.master.drinkmaster3000.nochnie;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class nochnie_ingame extends AppCompatActivity {

    int version;
    DatabankHandler db;
    List<nochnie> s;


    Button next;
    Button prev;
    TextView frage;


    nochnie st;

    int wo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nochnie_ingame);

        next = findViewById(R.id.nochnie_next);
        prev = findViewById(R.id.nochnie_prev);
        frage = findViewById(R.id.nochnie_frage);

        version = getIntent().getIntExtra("version", 0);


        Typeface mytypeface = Typeface.createFromAsset(getAssets(), "fonts/bookmark.ttf");

        frage.setTypeface(mytypeface);
        frage.setTextColor(Color.WHITE);

        try {
            getDatabank gh = new getDatabank(getApplicationContext());
            db = gh.getDb();
            s = db.getNochnie(version);
        } catch (Exception e) {
            next.setText(e.getMessage());
        }

        try {

            Collections.shuffle(s);

            Random r = new Random();

            wo = r.nextInt(s.size());

            st = s.get(wo);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wo++;
                    wo = mod(wo,s.size());
                    st = s.get(wo);
                    frage.setText(st.getFrage());

                }
            });

            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wo--;
                    wo = mod(wo,s.size());
                    st = s.get(wo);
                    frage.setText(st.getFrage());
                }
            });
        }catch (Exception e){
            frage.setText("");

            prev.setText("");
        }




    }

    private int mod(int x, int y)
    {
        int result = x % y;
        if (result < 0)
            result += y;
        return result;
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(getApplicationContext(),Main_Main.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
    }
}
