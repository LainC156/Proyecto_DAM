package com.example.randallflagg.dmellitus2_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

        private Button btn_ingresar, btn_registrar, btn_acercade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        //btn_registrar = (Button) findViewById(R.id.btn_registrar);
        //btn_acercade = (Button) findViewById(R.id.btn_acercade);
        ///////botón atrás
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void registrar(View v) {
        Intent intent = new Intent(this, registro.class);
        startActivity(intent);
    }

    public void ingresar(View v) {
        Intent intent = new Intent(this, ingresar.class);
        startActivity(intent);
    }

    public void acercade(View v) {
        Intent intent = new Intent(this, acerca_de.class);
        startActivity(intent);
    }
}
