package com.example.randallflagg.dmellitus2_app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.R.attr.button;
import static android.widget.Toast.*;

public class medicion extends AppCompatActivity  {
    RadioGroup tiempo, tipo;
    EditText valorg;
    Button aceptar;
    String tiempo2,tipo2, tipo3, tiempo3, valorglucosa, c;
    Integer tipo1, tiempo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);
        tiempo = (RadioGroup)findViewById(R.id.tiempo);
        tipo = (RadioGroup)findViewById(R.id.tipo);
        valorg = (EditText)findViewById(R.id.et_valor);
        aceptar = (Button)findViewById(R.id.btn_guardavalores);
    }

    private void EnviarMensaje (String Numero, String Mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(Numero,null,Mensaje,null,null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public void btn_guardar_valores (View v){
        base_de_datos db = new base_de_datos(this, "database", null, 1);
        final SQLiteDatabase data_base = db.getWritableDatabase();
        Integer tamaño;

        tiempo1 = tiempo.getCheckedRadioButtonId();
        tiempo2 = Integer.toString(tiempo1);
        tipo1 = tipo.getCheckedRadioButtonId();
        tipo2 = Integer.toString(tipo1);
        valorglucosa = valorg.getText().toString();

        if( tiempo1!=-1 && tipo1!=-1 && valorglucosa.length()>0 ) {

            if (tipo1 == 2131492981){
                tipo3 = "desayuno";
        }
            else if (tipo1 == 2131492982) {
                tipo3 = "comida";
            }
            else if (tipo1 == 2131492983) {
                tipo3 = "cena";
            }

            if (tiempo1 == 2131492977) {
                tiempo3 = "antes de";
            }
            else if (tiempo1 == 2131492978) {
                tiempo3 = "despues de";
            }
            /////ALERTAS
            //if (12 < valor de glucosa >32)
            String c = "SELECT correo_d, telefono_d  FROM doctor_db ORDER BY doctor_id DESC LIMIT 1";
            Cursor consulta = data_base.rawQuery(c, null);
            if (consulta.moveToNext())
            {
                EnviarMensaje(consulta.getString(1), "alerta");
                //EnviarCorreo();
            }
            else{
                Toast.makeText(this, "Error",Toast.LENGTH_LONG).show();
            }
            //////FIN ALERTAS
            c = "INSERT INTO mediciones (tipo, tiempo, nivel) VALUES ('" + tipo3 + "','" + tiempo3 + "','" + valorglucosa + "')";
            data_base.execSQL(c);
            Toast.makeText(this, c, Toast.LENGTH_LONG).show();
        }
        else  {
            Toast.makeText(this, "Debe ingresar todos los campos",Toast.LENGTH_LONG);
            }
        data_base.close();
    }

}
