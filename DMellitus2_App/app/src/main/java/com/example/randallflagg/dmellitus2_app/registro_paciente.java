package com.example.randallflagg.dmellitus2_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.R.attr.name;

public class registro_paciente extends AppCompatActivity {

    private String nombre_p, direccion_p, telefono_p, edad_p, peso_p, altura_p, fdn_p, medicamento, correo_p, usuario, contraseña;
    Button aceptar, limpiar, rellenar;
    public EditText nombrep,direccionp, telefonop, edadp, pesop, alturap, fdnp, medicamentop, correop, usuariop, contraseñap, prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aceptar = (Button) findViewById(R.id.btn_registrar);
        rellenar = (Button)findViewById(R.id.btn_rellenar);
        limpiar = (Button)findViewById(R.id.btn_vaciar);

        nombrep=(EditText)findViewById(R.id.et_nombre);
        direccionp = (EditText)findViewById(R.id.et_direccion);
        telefonop = (EditText)findViewById(R.id.et_telefono);
        edadp = (EditText)findViewById(R.id.et_edad);
        pesop = (EditText)findViewById(R.id.et_peso);
        alturap = (EditText)findViewById(R.id.et_altura);
        fdnp= (EditText)findViewById(R.id.et_fdn);
        medicamentop = (EditText)findViewById(R.id.et_medicamento);
        correop = (EditText)findViewById(R.id.et_correo);
        usuariop = (EditText)findViewById(R.id.et_usuario);
        contraseñap = (EditText)findViewById(R.id.et_contraseña);
    } ////////ONCREATE()

///////// SHA256
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void aceptar_DB(View v) throws NoSuchAlgorithmException {
        final AlertDialog.Builder wrong = new AlertDialog.Builder(this);
        wrong.setMessage("Ningún campo debe estar vacío.")
                .setTitle("AVISO").setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        final AlertDialog.Builder welcome = new AlertDialog.Builder(this);
        welcome.setMessage("Usuario registrado con éxito.")
                .setTitle("Bienvenido").setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        base_de_datos db = new base_de_datos(this, "database", null, 1);
        final SQLiteDatabase data_base = db.getWritableDatabase();


        nombre_p = ((EditText) findViewById(R.id.et_nombre)).getText().toString();
        direccion_p = ((EditText) findViewById(R.id.et_direccion)).getText().toString();
        telefono_p = ((EditText) findViewById(R.id.et_telefono)).getText().toString();
        edad_p = ((EditText) findViewById(R.id.et_edad)).getText().toString();
        peso_p = ((EditText) findViewById(R.id.et_peso)).getText().toString();
        fdn_p = ((EditText) findViewById(R.id.et_fdn)).getText().toString();
        altura_p = ((EditText) findViewById(R.id.et_altura)).getText().toString();
        medicamento = ((EditText) findViewById(R.id.et_medicamento)).getText().toString();
        correo_p = ((EditText) findViewById(R.id.et_correo)).getText().toString();
        usuario = ((EditText) findViewById(R.id.et_usuario)).getText().toString();
        contraseña = ((EditText) findViewById(R.id.et_contraseña)).getText().toString();


        if ((nombre_p.length() * direccion_p.length() * telefono_p.length() * edad_p.length() * peso_p.length() * altura_p.length() * medicamento.length() * correo_p.length() * usuario.length() * contraseña.length()) == 0) {
            AlertDialog alert = wrong.create();
            alert.show();
        } else {
            String pass = sha256(contraseña);
            AlertDialog alert = welcome.create();
            alert.show();
            String sql = " INSERT INTO paciente_db (nombre_p,direccion_p,telefono_p, edad, peso, altura, fdn, medicamento, correo_p, usuario, contraseña)" +
                    " VALUES ('" + nombre_p + "','" + direccion_p + "','" + telefono_p + "','" + edad_p + "','" + peso_p + "','" + altura_p + "','" + fdn_p + "','" + medicamento + "','" + correo_p + "','" + usuario + "','" + pass + "') ";

            Toast.makeText(this,pass,Toast.LENGTH_LONG).show();
            data_base.execSQL(sql);
        }
    }


    ///////7botón rellenar
    public void rellenar_DB(View v){

        base_de_datos db = new base_de_datos(this, "database", null, 1);
        SQLiteDatabase data_base = db.getReadableDatabase();
        String c = "SELECT nombre_p, direccion_p, telefono_p, edad, peso, altura, fdn, medicamento, correo_p, usuario  FROM paciente_db ORDER BY paciente_id DESC LIMIT 1";
        Cursor consulta = data_base.rawQuery(c, null);
        if (consulta.moveToNext())
        {
            nombrep.setText(consulta.getString(0));
            direccionp.setText(consulta.getString(1));
            telefonop.setText(consulta.getString(2));
            edadp.setText(consulta.getString(3));
            pesop.setText(consulta.getString(4));
            alturap.setText(consulta.getString(5));
            fdnp.setText(consulta.getString(6));
            medicamentop.setText(consulta.getString(7));
            correop.setText(consulta.getString(8));
            usuariop.setText(consulta.getString(9));
        }
        else{
            Toast.makeText(this, "Error",Toast.LENGTH_LONG).show();}
        data_base.close();

    }

    public void limpiar_campos(View v) {

        nombrep.setText(null);
        direccionp.setText(null);
        telefonop.setText(null);
        edadp.setText(null);
        pesop.setText(null);
        alturap.setText(null);
        fdnp.setText(null);
        medicamentop.setText(null);
        correop.setText(null);
        usuariop.setText(null);
        contraseñap.setText(null);
    }
}
