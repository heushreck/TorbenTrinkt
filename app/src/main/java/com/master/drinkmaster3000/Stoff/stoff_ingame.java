package com.master.drinkmaster3000.Stoff;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.master.drinkmaster3000.Databank.DatabankHandler;
import com.master.drinkmaster3000.Databank.getDatabank;
import com.master.drinkmaster3000.Main.Main_Main;
import com.master.drinkmaster3000.R;
import com.master.drinkmaster3000.stoff;

public class stoff_ingame extends AppCompatActivity {

    int version;
    DatabankHandler db;
    List<stoff> s;


    Button next;
    Button prev;
    TextView frage;


    stoff st;

    int wo = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoff_ingame);

        version = getIntent().getIntExtra("version",0);

        next = findViewById(R.id.stoff_next);
        prev = findViewById(R.id.stoff_prev);
        frage = findViewById(R.id.stoff_frage);

        View someView = findViewById(R.id.stoff_frage);
        View root = someView.getRootView();


        Random r = new Random();



        if (version == 0 || version == 2){
            root.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
            Typeface mytypeface = Typeface.createFromAsset(getAssets(),"fonts/bookmark.ttf");

            frage.setTypeface(mytypeface);
        }else if(version == 1){
            root.setBackgroundColor(getResources().getColor(R.color.Liebe));
            Typeface mytypeface = Typeface.createFromAsset(getAssets(),"fonts/Chasing Hearts - TTF.ttf");

            frage.setTypeface(mytypeface);
            frage.setAllCaps(false);
        }

        frage.setTextColor(Color.WHITE);


        try {
            getDatabank gh = new getDatabank(getApplicationContext());
            db = gh.getDb();
            s = db.getAllStoff(version);
        }catch (Exception e){
            next.setText(e.getMessage());
        }

        Collections.shuffle(s);

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
