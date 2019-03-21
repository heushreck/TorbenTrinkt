package com.master.drinkmaster3000.Main;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import java.util.Arrays;
import java.util.Random;

import com.master.drinkmaster3000.Privacy.privacy_explained;
import com.master.drinkmaster3000.R;
import com.master.drinkmaster3000.Stoff.stoff_wahl;
import com.master.drinkmaster3000.Trinkolo.Trinkolo_Main;
import com.master.drinkmaster3000.Blackstories.black_wahl;
import com.master.drinkmaster3000.Wahrheit.wahrheit_ingame;
import com.master.drinkmaster3000.Nochnie.nochnie_main;

public class Main_Main extends AppCompatActivity {

    private DrawerLayout dl1;
    private ActionBarDrawerToggle at1;
    public NavigationView main_menu;

    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button title;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__main);

        title = findViewById(R.id.main_title);

        title.setText("Torben spielt:");

        dl1 = findViewById(R.id.main_drawer);

        at1 = new ActionBarDrawerToggle(this,dl1,R.string.open,R.string.close);//der Pfeil oben links, der die Navigation View rauszieht

        dl1.addDrawerListener(at1);

        at1.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        main_menu = findViewById(R.id.main_menu);//die Navigation View

        //die 4 buttons werden gefunden
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);


        //der Font auf alles setten, könnte man auch extern regeln
        Typeface mytypeface = Typeface.createFromAsset(getAssets(),"fonts/Vonique 64.ttf");

        set_typeface(mytypeface);

        //welche Activitier aufgerufen werden
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Trinkolo_Main.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),nochnie_main.class);
                startActivity(i);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),wahrheit_ingame.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),privacy_explained.class);
                startActivity(i);
            }
        });


        //was alles im Navigation passiert
        main_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.Spieleliste){
                    Intent i = new Intent(getApplicationContext(),main_andere.class);
                    startActivity(i);
                }else if (item.getItemId()==R.id.Autofahrt){
                    Intent i = new Intent(getApplicationContext(),main_langeweile.class);
                    startActivity(i);
                }else if (item.getItemId()==R.id.Credits){
                    Intent i = new Intent(getApplicationContext(),main_credits.class);
                    startActivity(i);
                }else if(item.getItemId()==R.id.Teilen){
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    String shareBody = "Beste deutsche Trinkspiel App: https://play.google.com/store/apps/details?id=com.master.drinkmaster3000";
                    String sharesub = "Sehr zu empfehlen";
                    share.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                    share.putExtra(Intent.EXTRA_TEXT,shareBody);
                    startActivity(Intent.createChooser(share,"Teile über..."));
                }else if(item.getItemId()==R.id.Bewerten){
                    Uri uri = Uri.parse("market://details?id=" + Main_Main.this.getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    // To count with Play market backstack, After pressing back button,
                    // to taken back to our application, we need to add following flags to intent.
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=" + Main_Main.this.getPackageName())));
                    }
                }

                //falls etwas ausgewählt wird, soll dich der Drawer schließen
                DrawerLayout drawer = findViewById(R.id.main_drawer);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    //wird am Anfang aufgerufen, damit die Menu items in den Drawer kommen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_main_action,menu);
        return true;
    }

    //Falls irgendetwas in der Actionbar passiert
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.plus){
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Trink", Snackbar.LENGTH_LONG);

            snackbar.show();
        }else if (at1.onOptionsItemSelected(item)){
            View view = getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void set_typeface(Typeface mytypeface){
        b0.setTypeface(mytypeface);
        b0.setAllCaps(false);
        b1.setTypeface(mytypeface);
        b1.setAllCaps(false);
        b2.setTypeface(mytypeface);
        b2.setAllCaps(false);
        b3.setTypeface(mytypeface);
        b3.setAllCaps(false);
    }


    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
