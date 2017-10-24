package mx.unam.unica.curso.basessqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_descripcion, et_codigo, et_precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_descripcion = (EditText)findViewById(R.id.et_descripcion);
        et_codigo = (EditText)findViewById(R.id.et_codigo);
        et_precio = (EditText)findViewById(R.id.et_preciio);


    }

    public void alta(View v)
    {
        AuxSQL auxSQL = new AuxSQL(this,"Inventario",null,1);
        SQLiteDatabase bd = auxSQL.getWritableDatabase();
        String cod = et_codigo.getText().toString();
        String des = et_descripcion.getText().toString();
        String pre = et_precio.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("codigo",cod);
        registro.put("descripcion",des);
        registro.put("precio",pre);
        bd.insert("Articulos", null, registro);
        bd.close();
        et_precio.setText("");
        et_codigo.setText("");
        et_descripcion.setText("");
        Toast.makeText(this,"Se han ingresado los datos",Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v)
    {
        AuxSQL auxSQL = new AuxSQL(this,"Inventario",null,1);
        SQLiteDatabase db = auxSQL.getReadableDatabase();
        String cod = et_codigo.getText().toString();

        Cursor fila = db.rawQuery("SELECT descripcion,precio FROM Articulos WHERE codigo=" + cod, null);
        if(fila.moveToFirst())
        {
            et_descripcion.setText(fila.getString(0));
            et_precio.setText(fila.getString(1));
        }else
            Toast.makeText(this,"No existe el artículo con ese código",Toast.LENGTH_SHORT).show();
        db.close();

    }

    public void eliminarporcodigo(View v)
    {
        AuxSQL auxSQL = new AuxSQL(this,"Inventario",null,1);
        SQLiteDatabase db = auxSQL.getWritableDatabase();
        String cod = et_codigo.getText().toString();
        int cant = db.delete("Articulos", "codigo=" + cod,null);
        db.close();
        et_precio.setText("");
        et_descripcion.setText("");
        et_codigo.setText("");
        if (cant == 1)
            Toast.makeText(this,"Se ha eliminado el elemento ingresado",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No se encontró el elemento",Toast.LENGTH_SHORT).show();
    }


}
