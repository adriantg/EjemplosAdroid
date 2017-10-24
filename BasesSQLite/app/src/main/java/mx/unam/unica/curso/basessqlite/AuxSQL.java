package mx.unam.unica.curso.basessqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 28/01/2016.
 */
public class AuxSQL extends SQLiteOpenHelper {

    String sqlTabla = "CREATE TABLE Articulos(codigo int primary key, descripcion text, precio real)";

    public AuxSQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Articulos");
        db.execSQL(sqlTabla);
    }
}
