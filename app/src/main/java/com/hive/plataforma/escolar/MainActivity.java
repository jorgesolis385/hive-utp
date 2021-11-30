package com.hive.plataforma.escolar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button inscribirse;
    TextView acceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inscribirse = findViewById(R.id.inscribirse);
        acceso = findViewById(R.id.acceso);
        inscribirse.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
        });
        acceso.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(intent);
        });
    }
}
