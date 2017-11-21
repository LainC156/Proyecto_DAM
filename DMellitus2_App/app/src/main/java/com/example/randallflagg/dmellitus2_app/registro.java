package com.example.randallflagg.dmellitus2_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void registrar_paciente(View v) {
        Intent intent = new Intent(this, registro_paciente.class);
        startActivity(intent);
    }

    public void registrar_doctor(View v) {
        Intent intent = new Intent(this, registro_doctor.class);
        startActivity(intent);
    }
}
