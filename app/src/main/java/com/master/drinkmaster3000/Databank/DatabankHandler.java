package com.master.drinkmaster3000.Databank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.master.drinkmaster3000.black_story_karte;
import com.master.drinkmaster3000.nochnie;
import com.master.drinkmaster3000.privacy_frage;
import com.master.drinkmaster3000.rule;
import com.master.drinkmaster3000.stoff;
import com.master.drinkmaster3000.wahrheit;


public class DatabankHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "Database";

    // Contacts table name
    private static final String TABLE_RULES = "rules";
    private static final String TABLE_BLACKSTORY = "blackstory";
    private static final String TABLE_PRIVACY = "privacy";
    private static final String TABLE_STOFF = "stoff";
    private static final String TABLE_WAHRHEIT = "wahrheit";
    private static final String TABLE_NOCHNIE = "nochnie";



    // Rules Table Columns names
    private static final String KEY_RULES_MODUS = "modus";
    private static final String KEY_RULES_TEXT = "text";
    private static final String KEY_RULES_ANZAHL = "anzahl";
    private static final String KEY_RULES_KATEGORIE = "kategorie";
    private static final String KEY_RULES_RUNDE1 = "runde1";
    private static final String KEY_RULES_RUNDE2 = "runde2";
    private static final String KEY_RULES_SPIEL = "spiel";


    // Blackstory Table Columns names
    private static final String KEY_BLACKSTORY_FRAGE = "frage";
    private static final String KEY_BLACKSTORY_ANTWORT = "antwort";
    private static final String KEY_BLACKSTORY_TITEL = "titel";
    private static final String KEY_BLACKSTORY_EDITION = "edition";

    // Privacy Table Columns names
    private static final String KEY_PRIVACY_FRAGE = "frage";


    // Stoff Table Columns names
    private static final String KEY_STOFF_FRAGE = "frage";
    private static final String KEY_STOFF_EDITION = "edition";

    //Wahrheit Table Columns names

    private static final String KEY_WAHRHEIT_FRAGE = "frage";
    private static final String KEY_WAHRHEIT_PFLICHT = "pflicht";

    //Nochnie Table Columns names

    private static final String KEY_NOCHNIE_FRAGE = "frage";
    private static final String KEY_NOCHNIE_KAT = "katergorie";


    public DatabankHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "";

        CREATE_TABLE = "CREATE TABLE " + TABLE_RULES + "("
                + KEY_RULES_MODUS + " TEXT,"
                + KEY_RULES_TEXT + " TEXT,"
                + KEY_RULES_ANZAHL + " integer,"
                + KEY_RULES_KATEGORIE + " TEXT,"
                + KEY_RULES_RUNDE1 + " TEXT,"
                + KEY_RULES_RUNDE2 + " TEXT,"
                + KEY_RULES_SPIEL + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TABLE_BLACKSTORY + "("
                + KEY_BLACKSTORY_FRAGE + " TEXT,"
                + KEY_BLACKSTORY_ANTWORT + " TEXT,"
                + KEY_BLACKSTORY_TITEL + " TEXT,"
                + KEY_BLACKSTORY_EDITION + " integer"
                + ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TABLE_PRIVACY + "("
                + KEY_PRIVACY_FRAGE + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);


        CREATE_TABLE = "CREATE TABLE " + TABLE_STOFF + "("
                + KEY_STOFF_FRAGE + " TEXT,"
                + KEY_STOFF_EDITION + " integer"
                + ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TABLE_WAHRHEIT + "("
                + KEY_WAHRHEIT_FRAGE + " TEXT,"
                + KEY_WAHRHEIT_PFLICHT + " integer"
                + ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TABLE_NOCHNIE + "("
                + KEY_NOCHNIE_FRAGE + " TEXT,"
                + KEY_NOCHNIE_KAT + " integer"
                + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RULES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOCHNIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLACKSTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIVACY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOFF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WAHRHEIT);

        onCreate(db);
    }

    //ADDING ROWS

    public void addRule(rule r) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RULES_MODUS, r.getModus());
        values.put(KEY_RULES_TEXT, r.getText());
        values.put(KEY_RULES_ANZAHL, r.getAnzahl_Spieler());
        values.put(KEY_RULES_KATEGORIE, r.getKategorie());
        values.put(KEY_RULES_RUNDE1, r.getRunde1());
        values.put(KEY_RULES_RUNDE2, r.getRunde2());
        values.put(KEY_RULES_SPIEL, r.getSpiel());

        // Inserting Row
        db.insert(TABLE_RULES, null, values);
        db.close(); // Closing database connection
    }


    public void addBlackStory(black_story_karte b) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BLACKSTORY_FRAGE, b.getFrage());
        values.put(KEY_BLACKSTORY_ANTWORT, b.getAntwort());
        values.put(KEY_BLACKSTORY_TITEL, b.getTitel());
        values.put(KEY_BLACKSTORY_EDITION, b.getEdition());


        // Inserting Row
        db.insert(TABLE_BLACKSTORY, null, values);
        db.close(); // Closing database connection
    }

    public void addPrivacy(privacy_frage p) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRIVACY_FRAGE, p.getFrage());

        // Inserting Row
        db.insert(TABLE_PRIVACY, null, values);
        db.close(); // Closing database connection
    }


    public void addStoff(stoff s) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STOFF_FRAGE, s.getFrage());
        values.put(KEY_STOFF_EDITION, s.getEdition());


        // Inserting Row
        db.insert(TABLE_STOFF, null, values);
        db.close(); // Closing database connection
    }

    public void addWahrheit(wahrheit s) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WAHRHEIT_FRAGE, s.getFrage());
        values.put(KEY_WAHRHEIT_PFLICHT, s.getPflicht());


        // Inserting Row
        db.insert(TABLE_WAHRHEIT, null, values);
        db.close(); // Closing database connection
    }

    public void addNochnie(nochnie s) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOCHNIE_FRAGE, s.getFrage());
        values.put(KEY_NOCHNIE_KAT, s.getKategorie());


        // Inserting Row
        db.insert(TABLE_NOCHNIE, null, values);
        db.close(); // Closing database connection
    }



    //TRUNKATE

    public void trunkateRules(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RULES,null,null);
        db.close();
    }

    public void trunkateBlackStorys(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BLACKSTORY,null,null);
        db.close();
    }

    public void trunkatePrivacy(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRIVACY,null,null);
        db.close();
    }

    public void trunkateStoff(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STOFF,null,null);
        db.close();
    }

    public void trunkateWahrheit(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WAHRHEIT,null,null);
        db.close();
    }

    public void trunkateNochnie(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NOCHNIE,null,null);
            db.close();
        }catch (Exception e){

        }

    }


    //GETTING


    public List<rule> getAllRules(int teil, int anzahl) {
        List<rule> list = new ArrayList<rule>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '' and modus = 'default'";
        if (teil == 0){//default
            selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '' and modus = 'default' and anzahl < "+anzahl;
        }else if(teil == 1){//default and bar
            selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '' and modus != 'hot' and anzahl < "+anzahl;
        }else if(teil == 2){//default and sexy
            selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '' and modus != 'bar' and anzahl < "+anzahl;
        }else if(teil == 3){//all of theme
            selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '' and anzahl < "+anzahl;
        }else if(teil == 4){//bar
            selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '' and modus = 'bar' and anzahl < "+anzahl;
        }else if(teil == 5){//bar and sexy
            selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '' and modus != 'default' and anzahl < "+anzahl;
        }else{//hot
            selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '' and modus = 'hot' and anzahl < "+anzahl;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                rule r = new rule(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                // Adding contact to list
                list.add(r);
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }

    public List<rule> get2runde(String s){
        List<rule> r = new ArrayList<rule>();
        String selectQuery = "SELECT * FROM " + TABLE_RULES + " WHERE runde2 = '"+s+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                rule rule1 = new rule(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                r.add(rule1);
            } while (cursor.moveToNext());


        }

        // return contact list
        return r;
    }

    public List<black_story_karte> getAllBlackStorys(int verion) {
        List<black_story_karte> list = new ArrayList<black_story_karte>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BLACKSTORY + " WHERE edition = "+verion;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                black_story_karte b = new black_story_karte(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                );
                // Adding contact to list
                list.add(b);
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }

    public List<privacy_frage> getAllPrivacy() {
        List<privacy_frage> list = new ArrayList<privacy_frage>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PRIVACY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                privacy_frage p = new privacy_frage(
                        cursor.getString(0)
                );
                // Adding contact to list
                list.add(p);
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }

    public List<stoff> getAllStoff(int version) {
        List<stoff> list = new ArrayList<stoff>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_STOFF + " WHERE edition = "+version;;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                stoff s = new stoff(
                        cursor.getString(0),
                        cursor.getInt(1)
                );
                // Adding contact to list
                list.add(s);
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }

    public List<wahrheit> getWahrheit(int pflicht) {
        List<wahrheit> list = new ArrayList<wahrheit>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_WAHRHEIT + " WHERE pflicht = "+pflicht;;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                wahrheit s = new wahrheit(
                        cursor.getString(0),
                        cursor.getInt(1)
                );
                // Adding contact to list
                list.add(s);
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }

    public List<nochnie> getNochnie(int kategorie) {
        List<nochnie> list = new ArrayList<nochnie>();
        // Select All Query
        String selectQuery;

        if (kategorie == 0){//normal
            selectQuery = "SELECT * FROM " + TABLE_NOCHNIE + " WHERE katergorie = 0";
        }else if(kategorie == 1){//normal and sexy
            selectQuery = "SELECT * FROM " + TABLE_NOCHNIE + " WHERE katergorie != 2";
        }else if(kategorie == 2){//nochmal and sehr sexy
            selectQuery = "SELECT * FROM " + TABLE_NOCHNIE + " WHERE katergorie != 1";
        }else if(kategorie == 3){//all of theme
            selectQuery = "SELECT * FROM " + TABLE_NOCHNIE;
        }else if(kategorie == 4){//sexy
            selectQuery = "SELECT * FROM " + TABLE_NOCHNIE + " WHERE katergorie = 1";
        }else if(kategorie == 5){//sexy and sehr sexy
            selectQuery = "SELECT * FROM " + TABLE_NOCHNIE + " WHERE katergorie != 0";
        }else{//sehr sexy
            selectQuery = "SELECT * FROM " + TABLE_NOCHNIE + " WHERE katergorie = 2";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                nochnie s = new nochnie(
                        cursor.getString(0),
                        cursor.getInt(1)
                );
                // Adding contact to list
                list.add(s);
            } while (cursor.moveToNext());
        }
        // return contact list
        return list;
    }



    //COUNTS

    public int getRulesCount() {
        String countQuery = "SELECT * FROM " + TABLE_RULES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    public int getBlackStoryCount() {
        String countQuery = "SELECT * FROM " + TABLE_BLACKSTORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    public int getPrivacyCount() {
        String countQuery = "SELECT * FROM " + TABLE_PRIVACY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    public int getStoffCount() {
        String countQuery = "SELECT * FROM " + TABLE_STOFF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    public int getWahrheitCount() {
        String countQuery = "SELECT * FROM " + TABLE_WAHRHEIT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

    public int getNochnieCount() {
        String countQuery = "SELECT * FROM " + TABLE_NOCHNIE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

}
