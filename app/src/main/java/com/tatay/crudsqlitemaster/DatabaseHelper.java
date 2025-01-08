package com.tatay.crudsqlitemaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "data.db";
    public static final String DBLOCATION = "/data/data/com.tatay.crudsqlitemaster/";
    public static final int DBVERSION = 1;
    private static final String TABLE_SISWA = "siswa";
    private static final String KEY_NIS = "nis";
    private static final String KEY_NAMA = "nama_siswa";
    private static final String KEY_JENIS_KELAMIN = "jenis_kelamin";
    private static final String KEY_KONSENTRASI_KEAHLIAN = "konsentrasi_keahlian";

    private Context context;
    private final List<Siswa> listSiswa = new ArrayList<Siswa>();


    public DatabaseHelper(Context mContext) {
        super(mContext, DBNAME, null, DBVERSION);
        this.context = mContext;
    }

    /**
     * DDL
     */
    public static final String CREATE_TABLE_SISWA = "CREATE TABLE "+ TABLE_SISWA+"("+
            KEY_NIS+" INT NOT NULL, "+
            KEY_NAMA+" TEXT, "+
            KEY_JENIS_KELAMIN+" TEXT, "+
            KEY_KONSENTRASI_KEAHLIAN+" TEXT, "+
            "PRIMARY KEY (" + KEY_NIS + ")" +
            ")";

    /**
     * DML C.R.U.D
     */
    public void createSiswa(Siswa entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIS, entity.getNis());
        values.put(KEY_NAMA, entity.getNamaSiwa());
        values.put(KEY_JENIS_KELAMIN, entity.getJenisKelamin());
        values.put(KEY_KONSENTRASI_KEAHLIAN, entity.getKonsentrasiKeahlian());
        // Inserting Row
        db.insert(TABLE_SISWA, null, values);
        db.close();
    }

    public List<Siswa> readAllSiswa() {
        try {
            listSiswa.clear();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SISWA+" ORDER BY "+KEY_NAMA+" ASC", null);

            if (cursor.moveToFirst()) {
                do {
                    listSiswa.add(new Siswa(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)
                    ));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.e("list siswa", "" + e);
        }
        return listSiswa;
    }

    public Siswa readSiswa(String nis) {
        Siswa siswa;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SISWA, new String[] {
                        KEY_NIS,
                        KEY_NAMA,
                        KEY_JENIS_KELAMIN,
                        KEY_KONSENTRASI_KEAHLIAN }, KEY_NIS + "=?",
                new String[] { String.valueOf(nis) }, null, null, null, null);
        if (cursor.moveToFirst()) {
            siswa = new Siswa(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } else {
            siswa=null;
        }

        cursor.close();
        db.close();

        return siswa;
    }

    public int updateSiswa(Siswa siswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, siswa.getNamaSiwa());
        values.put(KEY_JENIS_KELAMIN, siswa.getJenisKelamin());
        values.put(KEY_KONSENTRASI_KEAHLIAN, siswa.getKonsentrasiKeahlian());

        return db.update(TABLE_SISWA, values, KEY_NIS + " = ?",
                new String[] { String.valueOf(siswa.getNis()) });
    }

    public void deleteSiswa(int nis) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SISWA, KEY_NIS + " = ?",
                new String[] { String.valueOf(nis) });
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_SISWA);
        onCreate(db);
    }

    public static int getDatabaseVersion() {
        return DBVERSION;
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();

        if (db != null && db.isOpen())
            db.close();
    }
}
