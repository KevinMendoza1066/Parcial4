package com.example.parcial4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parcial4.ClienteVehiculos.VerClientesAsociadosActivity;
import com.example.parcial4.Clientes.VerClientesActivity;
import com.example.parcial4.Vehiculos.VerVehiculosActivity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button verVehiculosBtn = findViewById(R.id.ver_vehiculos_button);
        Button verClientesBtn = findViewById(R.id.ver_clientes_button);
        Button verClientesAsociadosBtn = findViewById(R.id.ver_clientes_asociados_button);

        verVehiculosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VerVehiculosActivity.class);
                startActivity(intent);
            }
        });

        verClientesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VerClientesActivity.class);
                startActivity(intent);
            }
        });

        verClientesAsociadosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VerClientesAsociadosActivity.class);
                startActivity(intent);
            }
        });
    }
}