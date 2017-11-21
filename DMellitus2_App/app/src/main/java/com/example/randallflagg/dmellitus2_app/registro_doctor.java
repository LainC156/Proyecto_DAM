package com.example.randallflagg.dmellitus2_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

public class registro_doctor extends AppCompatActivity {

    String archivo = "doctor.txt";
    String nombre_d, direccion_d, telefono_d, correo_d;
    EditText nombred, correod, direcciond, telefonod;
    Button aceptar, vaciar, rellenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_doctor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombred = (EditText) findViewById(R.id.et_nombre);
        correod = (EditText) findViewById(R.id.et_correo);
        direcciond = (EditText) findViewById(R.id.et_direccion);
        telefonod = (EditText) findViewById(R.id.et_telefono);
        aceptar = (Button) findViewById(R.id.btn_registrar);
        vaciar = (Button) findViewById(R.id.btn_vaciar);
        rellenar = (Button) findViewById(R.id.btn_rellenar);
    }

    public void btn_aceptar(View v) throws NoSuchAlgorithmException {

        final AlertDialog.Builder wrong = new AlertDialog.Builder(this);
        wrong.setMessage("Ningún campo debe estar vacío.")
                .setTitle("AVISO").setCancelable(false)
                .setNeutralButton("Aceptar.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        final AlertDialog.Builder welcome = new AlertDialog.Builder(this);
        welcome.setMessage("Doctor " + nombre_d + " registrado con éxito.")
                .setTitle("Doctor Registrado").setCancelable(false)
                .setNeutralButton("Aceptar.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        base_de_datos db = new base_de_datos(this, "database", null, 1);
        final SQLiteDatabase data_base = db.getWritableDatabase();

        nombre_d = ((EditText) findViewById(R.id.et_nombre)).getText().toString();
        direccion_d = ((EditText) findViewById(R.id.et_direccion)).getText().toString();
        telefono_d = ((EditText) findViewById(R.id.et_telefono)).getText().toString();
        correo_d = ((EditText) findViewById(R.id.et_correo)).getText().toString();

        if ((direccion_d.length() * telefono_d.length() * correo_d.length() * nombre_d.length()) == 0) {
            AlertDialog alert = wrong.create();
            alert.show();
        } else {
            AlertDialog alert = welcome.create();
            alert.show();
            String sql = "INSERT INTO doctor_db (nombre_d,correo_d,direccion_d,telefono_d) VALUES ('" + nombre_d + "','" + correo_d + "','" + direccion_d + "','" + telefono_d + "') ";
            data_base.execSQL(sql);
            Toast.makeText(this, "exito", Toast.LENGTH_LONG).show();
        }
    }
    //////////fin botón aceptar
    public void btn_vaciar_campos(View v)
    {
        nombred.setText(null);
        correod.setText(null);
        direcciond.setText(null);
        telefonod.setText(null);
    }

    public void btn_rellenar(View v)
    {
        base_de_datos db = new base_de_datos(this, "database", null, 1);
        SQLiteDatabase data_base = db.getReadableDatabase();
        //final ContentValues registro = new ContentValues();
        String c = "SELECT nombre_d, correo_d, direccion_d, telefono_d  FROM doctor_db ORDER BY doctor_id DESC LIMIT 1";
        Cursor consulta = data_base.rawQuery(c, null);
        if (consulta.moveToNext())
        {
            nombred.setText(consulta.getString(0));
            correod.setText(consulta.getString(1));
            direcciond.setText(consulta.getString(2));
            telefonod.setText(consulta.getString(3));
        }
        else{
            Toast.makeText(this, "Error",Toast.LENGTH_LONG).show();
        }
        data_base.close();
    }
}
