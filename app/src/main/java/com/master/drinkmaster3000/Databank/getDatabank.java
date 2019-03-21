package com.master.drinkmaster3000.Databank;

import android.content.Context;
import android.content.res.AssetManager;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.master.drinkmaster3000.black_story_karte;
import com.master.drinkmaster3000.privacy_frage;
import com.master.drinkmaster3000.rule;
import com.master.drinkmaster3000.stoff;
import com.master.drinkmaster3000.wahrheit;
import com.master.drinkmaster3000.nochnie;

public class getDatabank {
    private DatabankHandler db;
    private Context context;

    public getDatabank(Context context) {
        this.context = context;
        db = new DatabankHandler(context);
        if(db.getBlackStoryCount() < 2){
            invoke();
        }
    }



    public void invoke(){
        db.trunkateBlackStorys();
        db.trunkatePrivacy();
        db.trunkateRules();
        db.trunkateStoff();
        db.trunkateWahrheit();
        db.trunkateNochnie();
        List<String[]> blackstorys = readCsv(context, "blackstory.csv");
        List<String[]> privacy = readCsv(context, "privacy.csv");
        List<String[]> rules = readCsv(context, "rules.csv");
        List<String[]> stoff = readCsv(context, "stoff.csv");
        List<String[]> wahrheit = readCsv(context, "wahrheit.csv");
        List<String[]> nochnie = readCsv(context, "nochnie.csv");


        for (String[] s : blackstorys){
            db.addBlackStory(new black_story_karte(s[0],s[1],s[2],Integer.parseInt(s[3])));
        }


        for (String[] s : privacy){
            db.addPrivacy(new privacy_frage(s[0]));
        }

        for (String[] s : rules){
            db.addRule(new rule(s[0],s[1],Integer.parseInt(s[2]),s[3],s[4],s[5],s[6]));
        }


        for (String[] s : stoff){
            db.addStoff(new stoff(s[0],Integer.parseInt(s[1])));
        }

        for (String[] s : wahrheit){
            db.addWahrheit(new wahrheit(s[0],Integer.parseInt(s[1])));
        }

        for (String[] s : nochnie){
            db.addNochnie(new nochnie(s[0],Integer.parseInt(s[1])));
        }

    }





    public final List<String[]> readCsv(Context context, String filename) {
        List<String[]> questionList = new ArrayList<String[]>();
        AssetManager assetManager = context.getAssets();

        try {
            InputStream csvStream = assetManager.open(filename);
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader,';');
            String[] line;


            while ((line = csvReader.readNext()) != null) {
                questionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public DatabankHandler getDb(){
        return db;
    }
}

