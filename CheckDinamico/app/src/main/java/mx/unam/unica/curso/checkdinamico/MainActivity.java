package mx.unam.unica.curso.checkdinamico;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout contenedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contenedor = (LinearLayout)findViewById(R.id.contenedor);

        ArrayList<check> lista = new ArrayList<check>();
        for(int i =1; i <=10; i++) {
            String opc = "Opción " + Integer.toString(i);
            lista.add(new check(i,opc));
        }

        for(check c:lista)
        {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(c.nombre);
            cb.setId(c.cod);
            cb.setTextColor(Color.BLACK);
            contenedor.addView(cb);

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Toast.makeText(getApplicationContext(), buttonView.getText().toString(),Toast.LENGTH_SHORT ).show();
                }
            });
        }


    }

    class check
    {
        public check(int cod, String nombre) {
            this.cod = cod;
            this.nombre = nombre;
        }

        public int cod;
        public String nombre;
    }
}
