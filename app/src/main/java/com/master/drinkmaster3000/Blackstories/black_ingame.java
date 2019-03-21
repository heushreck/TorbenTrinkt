package com.master.drinkmaster3000.Blackstories;

import android.content.Intent;
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
import com.master.drinkmaster3000.black_story_karte;

public class black_ingame extends AppCompatActivity {

    int version;
    DatabankHandler db;
    List<black_story_karte> b;


    Button next;
    Button prev;
    TextView title;
    TextView frage;

    boolean antwort = false;

    black_story_karte bl;

    int wo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_ingame);

        version = getIntent().getIntExtra("version",0);

        next = findViewById(R.id.black_next);
        prev = findViewById(R.id.black_prev);
        title = findViewById(R.id.black_titel);
        frage = findViewById(R.id.black_frage);

        if (version == 0){
            title.setText("Test Version");
        }else if (version == 2){
            title.setText("Black Stories 2");
        }else if (version == 3){
            title.setText("Black Stories 3");
        }else if (version == 4){
            title.setText("Black Stories Mysterie");
        }

        Typeface mytypeface1 = Typeface.createFromAsset(getAssets(),"fonts/Moms_typewriter.ttf");
        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"fonts/uwch.ttf");
        frage.setTypeface(mytypeface);
        frage.setAllCaps(false);
        title.setTypeface(mytypeface1);
        title.setAllCaps(false);




        try {
            getDatabank gh = new getDatabank(getApplicationContext());
            db = gh.getDb();
            b = db.getAllBlackStorys(version);
        }catch (Exception e){
            next.setText(e.getMessage());
        }

        Collections.shuffle(b);

        Random r = new Random();

        wo = r.nextInt(b.size());

        bl = b.get(wo);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (antwort){
                    title.setText(bl.getTitel());
                    frage.setText(bl.getFrage());
                    antwort = false;
                }else{
                    wo++;
                    wo = mod(wo,b.size());
                    bl = b.get(wo);
                    title.setText(bl.getTitel());
                    frage.setText(bl.getFrage());
                }

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (antwort){
                    title.setText(bl.getTitel());
                    frage.setText(bl.getFrage());
                    antwort = false;
                }else{
                    wo--;
                    wo = mod(wo,b.size());
                    bl = b.get(wo);
                    title.setText(bl.getTitel());
                    frage.setText(bl.getFrage());
                }
            }
        });

        prev.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!antwort){
                    antwort = true;
                    title.setText(title.getText()+"\n(Auflösung)");
                    frage.setText(bl.getAntwort());
                }
                return true;
            }
        });

        next.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!antwort){
                    antwort = true;
                    title.setText(title.getText()+"\n(Auflösung)");
                    frage.setText(bl.getAntwort());
                }
                return true;
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
