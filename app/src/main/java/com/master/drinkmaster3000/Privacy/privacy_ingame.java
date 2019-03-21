package com.master.drinkmaster3000.Privacy;

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
import com.master.drinkmaster3000.privacy_frage;

public class privacy_ingame extends AppCompatActivity {

    DatabankHandler db;
    List<privacy_frage> p;


    Button next;
    Button prev;
    TextView frage;


    privacy_frage pf;

    int wo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_ingame);

        next = findViewById(R.id.privacy_next);
        prev = findViewById(R.id.privacy_prev);
        frage = findViewById(R.id.privacy_text);

        View someView = findViewById(R.id.privacy_text);
        View root = someView.getRootView();


        Random r = new Random();

        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"fonts/bookmark.ttf");

        frage.setTypeface(mytypeface);
        frage.setAllCaps(false);


        root.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));


        frage.setTextColor(Color.WHITE);


        try {
            getDatabank gh = new getDatabank(getApplicationContext());
            db = gh.getDb();
            p = db.getAllPrivacy();
        }catch (Exception e){
            next.setText(e.getMessage());
        }

        Collections.shuffle(p);

        wo = r.nextInt(p.size());

        pf = p.get(wo);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wo++;
                wo = mod(wo,p.size());
                pf = p.get(wo);
                frage.setText(pf.getFrage());

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wo--;
                wo = mod(wo,p.size());
                pf = p.get(wo);
                frage.setText(pf.getFrage());
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
