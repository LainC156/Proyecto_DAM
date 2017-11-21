package com.example.randallflagg.dmellitus2_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;

public class ingresar extends AppCompatActivity {

    EditText userlogin,passlogin;
    String user, pass, hash256pass, consulta;
    Button btn_borrarcampos, btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userlogin = (EditText)findViewById(R.id.et_userlogin);
        passlogin = (EditText)findViewById(R.id.et_passlogin);
        btn_borrarcampos = (Button)findViewById(R.id.btn_borrarcampos);
        btn_ok = (Button)findViewById(R.id.btn_ok);

    }
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

    public void ingresar_login(View v){

        String user1= " ", pass1=" ";
        user = userlogin.getText().toString();
        pass = passlogin.getText().toString();
        hash256pass = sha256(pass);

        base_de_datos db = new base_de_datos(this, "database", null, 1);
        SQLiteDatabase data_base = db.getReadableDatabase();
        String c = "SELECT usuario,contraseña  FROM paciente_db ORDER BY paciente_id DESC LIMIT 1";
        Cursor consulta = data_base.rawQuery(c, null);
        if (consulta.moveToNext())
        {
            user1 = consulta.getString(0);
            pass1 = consulta.getString(1);
        }
        else{
            Toast.makeText(this, "Error",Toast.LENGTH_LONG).show();}

        if( user.equals(user1)  && hash256pass.equals(pass1) ){
        Toast.makeText(this,"Bienvenido",Toast.LENGTH_LONG);
        Intent intent = new Intent(this,inicio_u.class);
            startActivity(intent);
        }
        data_base.close();
    }
}
