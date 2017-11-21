package com.example.randallflagg.dmellitus2_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inicio_u extends AppCompatActivity {
    Button medicion, graficado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_u);
        medicion = (Button)findViewById(R.id.btn_nvamed);
        graficado = (Button)findViewById(R.id.btn_grafica);
    }

    public void btn_medicion (View v){
        Intent intent = new Intent(this, medicion.class);
        startActivity(intent);
    }

    public void btn_graficado (View v){
        Intent intent = new Intent(this, graficado.class);
        startActivity(intent);
    }
}
