package com.example.randallflagg.dmellitus2_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class acerca_de extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //REFERENCIA: https://www.diabetesmellitus.mx/rangos-los-niveles-azucar-en-la-sangre/
    }
}
