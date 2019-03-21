package com.master.drinkmaster3000.Trinkolo;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import com.master.drinkmaster3000.R;

public class Trinkolo_Main extends AppCompatActivity {

    public static int id = 1;

    List<EditText> editTextList;

    RelativeLayout relativeLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinkolo__main);

        relativeLayout = findViewById(R.id.rl);

        editTextList = new ArrayList<EditText>();

        Button start = findViewById(R.id.start);

        addEditT();
        addEditT();
        addEditT();



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] names = getNames();
                if (names==null){

                }else{
                    Intent i = new Intent(getApplicationContext(),Trinkolo_Wahl.class);
                    i.putExtra("names",names);
                    startActivity(i);
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trinkolo_main_action,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.plus){
            addEditT();
        }
        return super.onOptionsItemSelected(item);
    }

    public String[] getNames(){
        int size = editTextList.size();
        String[] result = null;
        String[] player = new String[size];
        int[] in = new int[size];
        for (int i = 0; i<in.length;i++){
            in[i] = -1;
        }
        int stelle = 0;


        for (int i = 0; i< editTextList.size();i++){
            player[i] = editTextList.get(i).getText().toString();
        }

        for (int i = 0;i<player.length;i++){
            if (player[i].length()>2){
                in[stelle] = i;
                stelle++;
            }
        }
        if (stelle>1){
            result = new String[stelle];
            for (int i = 0;i<result.length;i++){
                result[i] = player[in[i]];
            }
        }else{
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Hast du keine Freunde?", Snackbar.LENGTH_LONG);

            snackbar.show();
            return null;
        }

        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void addEditT(){
        EditText ed;
        ed = new EditText(this);
        ed.setHint("Player "+(editTextList.size()+1));
        ed.setId(findId());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        if (editTextList.size()>0){
            lp.addRule(RelativeLayout.BELOW, editTextList.get(editTextList.size()-1).getId());
        }
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        ed.setSingleLine(true);
        ed.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ed.setTextSize(24);
        relativeLayout.addView(ed, lp);
        editTextList.add(ed);
    }

    public int findId(){
        View v = findViewById(id);
        while (v != null){
            v = findViewById(++id);
        }
        return id++;
    }


}
