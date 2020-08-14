package com.paidinfull.misgo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SocHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "misgo.db";
    private static final String TABLE_NAME = "societé";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SIRET = "siret";
    private static final String COLUMN_TEL = "tel";

    public SocHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT, " + COLUMN_SIRET + " TEXT, " + COLUMN_TEL +" TEXT ) ;";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insertData(String nom, String siret, String tel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, nom);
        values.put(COLUMN_SIRET, siret);
        values.put(COLUMN_TEL, tel);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Cursor getData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT*FROM "+TABLE_NAME+ " WHERE ID='"+id+"'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public void udapteData(String id, String nom, String siret, String tel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, nom);
        contentValues.put(COLUMN_SIRET, siret);
        contentValues.put(COLUMN_TEL, tel);


        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{id});

    }
    // Udapte au lieu de delete
}