package com.example.tez.Activitys.Helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;



public class BoSQLiteHelper extends SQLiteOpenHelper {
    private static final String Database_path = "/data/data/com.example.tez/databases/boegitim";
    private static final String Database_name = "boegitim.db";//database ismi
    private static final String Table_name = "konu";//tablonun adı
    private static final String uid = "konuId";//colom 1
    private static final String sid = "sayfa";//colom 2
    private static final String Icerik = "bo";//colom 3
    private static final String SecenekA = "boA";//colom 4
    private static final String SecenekB = "boB";//colom 5
    private static final String SecenekC = "boC";//colom 6
    private static final String SecenekD = "boD";//colom 7
    private static final String CEVAP = "cevap";//colom 8
    private static final int version = 1;//versiyon
    public SQLiteDatabase sqlite;
    private Context context;//Context --- Icerik Activity

    public BoSQLiteHelper(Context context) {//constructor
        super(context, Database_name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Database imiz zaten var
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Database imiz zaten var
    }

    public void createDatabase() {
        createDB();
    }

    private void createDB() {

        boolean dbexist = DBexists();
        if (!dbexist)
        {

            this.getReadableDatabase();
            copyDBfromResource();
        }
    }

    private void copyDBfromResource() {

        InputStream is;
        OutputStream os;
        String filePath = Database_path + Database_name;
        try {
            is = context.getAssets().open(Database_name);
            os = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            is.close();
            os.close();

        } catch (IOException e) {
            throw new Error("Problem copying database file:");
        }
    }

    public void openDatabase() throws SQLException
    {

        String myPath = Database_path + Database_name;
        sqlite = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private boolean DBexists()//Database varmı yokmu kontrol
    {
        SQLiteDatabase db = null;
        try {
            String databasePath = Database_path + Database_name;
            db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);
            db.setLockingEnabled(true);
        } catch (SQLException e) {
            Log.e("Sqlite", "Database bulunamadı!");
        }
        if (db != null)
            db.close();///close the opened file
        return db != null ? true : false;

    }

    public String readQuestion(int i,int b)//Database deki içerik alanındaki bilgiyi okumak(almak) için
    {
        String Ans = "";
        Cursor c = sqlite.rawQuery("SELECT " + Icerik + " FROM " + Table_name + " WHERE "+ sid + " = " + i + " AND " + uid + " = " + b + "", null);//cursor to that query
        if (c.moveToFirst())
            Ans = c.getString(0);
        else
            Ans = "";


        return Ans;
    }

    public String readOptionA(int i,int b)//Database deki seçenekA alanındaki bilgiyi okumak(almak) için
    {
        String Ans = "";
        Cursor c = sqlite.rawQuery("SELECT " + SecenekA + " FROM " + Table_name + " WHERE "+ sid + " = " + i + " AND " + uid + " = " + b + "", null);//cursor to that query
        if (c.moveToFirst())
            Ans = c.getString(0);
        else
            Ans = "";
        return Ans;
    }

    public String readOptionB(int i,int b)//Database deki seçenekB alanındaki bilgiyi okumak(almak) için
    {
        String Ans = "";
        Cursor c = sqlite.rawQuery("SELECT " + SecenekB + " FROM " + Table_name + " WHERE "+ sid + " = " + i + " AND " + uid + " = " + b + "", null);//cursor to that query
        if (c.moveToFirst())
            Ans = c.getString(0);
        else
            Ans = "";
        return Ans;
    }

    public String readOptionC(int i,int b)//Database deki secenekC alanındaki bilgiyi okumak(almak) için
    {
        String Ans = "";
        Cursor c = sqlite.rawQuery("SELECT " + SecenekC + " FROM " + Table_name + " WHERE "+ sid + " = " + i + " AND " + uid + " = " + b + "", null);//cursor to that query
        if (c.moveToFirst())
            Ans = c.getString(0);
        else
            Ans = "";
        return Ans;
    }

    public String readOptionD(int i,int b)//Database deki SecenekD alanındaki bilgiyi okumak(almak) için
    {
        String Ans = "";
        Cursor c = sqlite.rawQuery("SELECT " + SecenekD + " FROM " + Table_name + " WHERE "+ sid + " = " + i + " AND " + uid + " = " + b + "", null);//cursor to that query
        if (c.moveToFirst())
            Ans = c.getString(0);
        else
            Ans = "";
        return Ans;
    }



    public String readAnswer(int i,int b)//Database deki Cevap alanındaki bilgiyi okumak(almak) için
    {

        String Ans = "";
        Cursor c = sqlite.rawQuery("SELECT " + CEVAP + " FROM " + Table_name + " WHERE "+ sid + " = " + i + " AND " + uid + " = " + b + "", null);//cursor to that query
        if (c.moveToFirst())
            Ans = c.getString(0);
        else
            Ans = "";
        return Ans;
    }
}


