package com.master.drinkmaster3000.Trinkolo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.master.drinkmaster3000.Databank.DatabankHandler;
import com.master.drinkmaster3000.Databank.getDatabank;
import com.master.drinkmaster3000.R;
import com.master.drinkmaster3000.r2;
import com.master.drinkmaster3000.rule;

public class Trinkolo_InGame extends AppCompatActivity {

    Button next;
    String[] spieler;
    DatabankHandler db;
    List<rule> r;
    TextView titel;
    Snackbar mySnackbar;
    List<rule> runde2;
    int timer = 0;
    int hard;
    List<r2> r2List;

    int schluck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinkolo__in_game);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        next = findViewById(R.id.next);
        titel = findViewById(R.id.title);

        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"fonts/Dirty Headline.ttf");
        Typeface mytypeface2 = Typeface.createFromAsset(getAssets(),"fonts/bookmark.ttf");

        titel.setTypeface(mytypeface);
        next.setTypeface(mytypeface2);
        next.setTextSize(24);

        r2List = new ArrayList<r2>();

        spieler = getIntent().getStringArrayExtra("names");
        hard = getIntent().getIntExtra("hard",3);
        schluck = hard+2;


        int a = getIntent().getIntExtra("modus",0);

        runde2 = new ArrayList<rule>();
        runde2.add(new rule("","Soll es wirklich los gehen?",0,"","","",""));


        mySnackbar = Snackbar.make(findViewById(android.R.id.content),"Ihr kennt dieses Spiel nicht?", Snackbar.LENGTH_INDEFINITE);

        try {
            getDatabank gh = new getDatabank(getApplicationContext());
            db = gh.getDb();
            r = db.getAllRules(a, spieler.length);
        }catch (Exception e){
            next.setText(e.getMessage());
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                int color = Color.argb(200, rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200));
                v.setBackgroundColor(color);
                Random y = new Random();
                r2 ru = null;
                try{
                    ru = r2List.get(0);
                    r2List.remove(0);
                }catch (Exception e){
                    ru = null;
                }
                try{
                    if (ru != null){
                        String s = ru.getRule().getText();
                        s = s.replace('$',Integer.toString(rnd.nextInt(schluck)+1).charAt(0));
                        int e = ru.getRule().getAnzahl_Spieler();
                        String result = "";
                        if (e == 0){
                            result = s;
                        }else if (e == 1){
                            result = String.format(s,ru.getSpieler1());
                        }else if (e == 2){
                            result = String.format(s,ru.getSpieler1(),ru.getSpieler2());
                        }else if(e == 3){
                            result = String.format(s,ru.getSpieler1(),ru.getSpieler2(),ru.getSpieler3());
                        }else if(e == 4){
                            result = String.format(s,ru.getSpieler1(),ru.getSpieler2(),ru.getSpieler3(),ru.getSpieler4());
                        }
                        titel.setText(ru.getRule().getKategorie());
                        next.setText(result);
                    }else{
                        int i = y.nextInt(r.size());
                        String s = r.get(i).getText();
                        s = s.replace('$',Integer.toString(rnd.nextInt(schluck)+1).charAt(0));
                        int e = r.get(i).getAnzahl_Spieler();
                        String result = "";
                        String spieler1 = spieler[y.nextInt(spieler.length)];
                        String spieler2 = spieler1;
                        String spieler3 = spieler1;
                        String spieler4 = spieler1;
                        while(spieler1.equals(spieler2)){
                            spieler2 = spieler[y.nextInt(spieler.length)];
                            if(spieler.length>2){
                                while(spieler1.equals(spieler3)&& spieler2.equals(spieler3)){
                                    spieler3 = spieler[y.nextInt(spieler.length)];
                                    if(spieler.length>3){
                                        while(spieler1.equals(spieler4)&& spieler2.equals(spieler4) && spieler3.equals(spieler4)){
                                            spieler4 = spieler[y.nextInt(spieler.length)];
                                        }
                                    }
                                }

                            }
                        }

                        if (e == 0){
                            result = s;
                        }else if (e == 1){
                            result = String.format(s,spieler1);
                        }else if (e == 2){
                            result = String.format(s,spieler1,spieler2);
                        }else if(e == 3){
                            result = String.format(s,spieler1,spieler2,spieler3);
                        }else if(e == 4){
                            result = String.format(s,spieler1,spieler2,spieler3,spieler4);
                        }

                        next.setText(result);

                        titel.setText(r.get(i).getKategorie());

                        String runde = r.get(i).getRunde1();
                        r.remove(i);

                        runde2 = db.get2runde(runde);

                        if (runde.length() > 1){
                            timer = y.nextInt(15)+5;
                            while (r2List.size()<timer){
                                r2List.add(null);
                            }
                            for (int j = 0;j<runde2.size();j++){
                                r2List.add((timer+j),new r2(runde2.get(j),spieler1,spieler2,spieler3,spieler4));
                            }


                        }
                    }
                }catch(Exception e){
                    next.setText(e.getMessage());
                }


            }
        });
        next.callOnClick();
    }
}
