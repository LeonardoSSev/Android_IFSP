package com.example.leonardo.projetofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 15/12/2016.
 */

public class DBHelper {
    private static final String DATABASE_NAME = "bancodedados.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contato";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStnt;
    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, email, cpf, idade, telefone) values (?,?,?,?,?)";

    public DBHelper (Context context){
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStnt = this.db.compileStatement(INSERT);
    }


    public long insert (String nome, String email, String cpf, String idade, String telefone){
        this.insertStnt.bindString(1, nome);
        this.insertStnt.bindString(2, email);
        this.insertStnt.bindString(3, cpf);
        this.insertStnt.bindString(4, idade);
        this.insertStnt.bindString(5, telefone);

        return this.insertStnt.executeInsert();
    }

    public void deleteAll(){
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<Contato> queryGetAll() {
        List<Contato> list = new ArrayList<Contato>();
        try {
            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"nome", "email", "cpf", "idade", "telefone"}, null, null, null, null, null, null);

            int nregistros = cursor.getCount();

            if (nregistros != 0) {
                cursor.moveToFirst();

                do {
                    Contato contato = new Contato(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    list.add(contato);
                } while (cursor.moveToNext());

                if (cursor != null && ! cursor.isClosed()) {
                    cursor.close();
                }
                return list;
            } else {
                return null;
            }

        } catch (Exception err) {
            return null;
        }

    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context){
            super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db){
            String sql = "CREATE A TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT, cpf TEXT, idade TEXT, telefone TEXT);";
            db.execSQL(sql);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }
    }
}
