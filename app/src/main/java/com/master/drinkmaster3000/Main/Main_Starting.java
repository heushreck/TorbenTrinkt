package com.master.drinkmaster3000.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.master.drinkmaster3000.Databank.DatabankHandler;
import com.master.drinkmaster3000.Databank.getDatabank;
import com.master.drinkmaster3000.R;
import com.master.drinkmaster3000.Trinkolo.Trinkolo_Main;

public class Main_Starting extends AppCompatActivity {


    DatabankHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__starting);

        getDatabank gH = new getDatabank(this);

        db = gH.getDb();

        Intent i = new Intent(getApplicationContext(),Main_Main.class);
        startActivity(i);
    }
}
