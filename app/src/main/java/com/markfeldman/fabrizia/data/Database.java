package com.markfeldman.fabrizia.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
    private final String DATABASE_NAME = "cocktail_database";
    private final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDb;
    private DatabaseHelper databaseHelper;

    public Database(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public Database openReadable(){
        mDb = databaseHelper.getReadableDatabase();
        return this;
    }

    public Database openWritable(){
        mDb = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){ mDb.close(); }


    public Cursor getAllCocktailRows(){
        return mDb.query(DataContract.CocktailData.TABLE_NAME,null,null,null,null,null,null);
    };

    public Cursor getSpecificCocktailRow(String[] projection,String[] rowID){
        String selection = DataContract.CocktailData._ID + "=?";
        Cursor c = mDb.query(DataContract.CocktailData.TABLE_NAME,projection,selection,rowID,null,null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public long insertRowToCocktail(ContentValues cv){
        return mDb.insert(DataContract.CocktailData.TABLE_NAME, null,cv);
    }


    private class DatabaseHelper extends SQLiteOpenHelper{

        private final static String CREATE_COCKTAILS_TABLE = "CREATE TABLE " + DataContract.CocktailData.TABLE_NAME +
                " ("+ DataContract.CocktailData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataContract.CocktailData.COLUMN_COCKTAIL_NAME + " TEXT NOT NULL, " +
                DataContract.CocktailData.COLUMN_INGREDIENTS + " TEXT NOT NULL " +
                ");";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_COCKTAILS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DataContract.CocktailData.TABLE_NAME);
            onCreate(db);

        }
    }
}
