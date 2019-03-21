package com.master.drinkmaster3000.Wahrheit;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import com.master.drinkmaster3000.Databank.DatabankHandler;
import com.master.drinkmaster3000.Databank.getDatabank;
import com.master.drinkmaster3000.R;
import com.master.drinkmaster3000.wahrheit;

public class wahrheit_ingame extends AppCompatActivity {

    TextView textView;
    TextView w_title;
    Button pflicht;
    Button wahrheit;

    DatabankHandler db;
    List<wahrheit> p;
    List<wahrheit> w;

    boolean but = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wahrheit_ingame);

        textView = findViewById(R.id.wahrheit_frage);
        pflicht = findViewById(R.id.wahrheit_p);
        wahrheit = findViewById(R.id.wahrheit_w);
        w_title = findViewById(R.id.w_title);

        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"fonts/bookmark.ttf");

        textView.setTypeface(mytypeface);
        textView.setAllCaps(false);
        pflicht.setTypeface(mytypeface);
        pflicht.setAllCaps(false);
        wahrheit.setTypeface(mytypeface);
        wahrheit.setAllCaps(false);
        w_title.setTypeface(mytypeface);
        w_title.setAllCaps(false);


        try {
            getDatabank gh = new getDatabank(getApplicationContext());
            db = gh.getDb();
            w = db.getWahrheit(0);
            p = db.getWahrheit(1);
        }catch (Exception e){
            textView.setText(e.getMessage());
        }

        pflicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                if(but){
                    pflicht.setText("");
                    wahrheit.setText("");
                    try{
                        textView.setText(p.get(r.nextInt(p.size())).getFrage());
                    }catch(Exception e){
                        textView.setText("Keine weiteren Pflichten...");
                    }
                    w_title.setText("Pflicht");
                    but = false;
                }else{
                    textView.setText("oder");
                    pflicht.setText("Pflicht");
                    wahrheit.setText("Wahrheit");
                    w_title.setText("");
                    but = true;
                }
            }
        });

        wahrheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                if(but){
                    pflicht.setText("");
                    wahrheit.setText("");
                    try{
                        textView.setText(w.get(r.nextInt(w.size())).getFrage());
                    }catch(Exception e){
                        textView.setText("Keine weiteren Wahrheiten...");
                    }
                    w_title.setText("Wahrheit");
                    but = false;
                }else{
                    textView.setText("oder");
                    pflicht.setText("Pflicht");
                    wahrheit.setText("Wahrheit");
                    w_title.setText("");
                    but = true;
                }
            }
        });

    }
}
