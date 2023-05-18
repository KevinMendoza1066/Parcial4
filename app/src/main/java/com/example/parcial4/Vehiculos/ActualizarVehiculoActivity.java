package com.example.parcial4.Vehiculos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial4.BaseDatos.BaseDatosHelper;
import com.example.parcial4.R;

public class ActualizarVehiculoActivity extends AppCompatActivity {


    private SQLiteDatabase mDatabase;
    private EditText edMarca;
    private EditText edModelo;

    private Button btnActualizarVehiculo;

    private int IdVehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_vehiculo);
        SQLiteOpenHelper dbHelper = new BaseDatosHelper(getApplicationContext(), "tallerParcial4.db", null, 2);
        mDatabase = dbHelper.getWritableDatabase();
        edMarca = findViewById(R.id.edMarca);
        edModelo = findViewById(R.id.edModelo);

        btnActualizarVehiculo = findViewById(R.id.btnActualizarVehiculo);
        Intent intent = getIntent();
        IdVehiculo = intent.getIntExtra("id_registro", -1); // Valor predeterminado en caso de que no se encuentre el dato
        edMarca.setText(intent.getStringExtra("Marca"));
        edModelo.setText(intent.getStringExtra("Modelo"));


        btnActualizarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRegistro();
            }
        });
    }
    private void actualizarRegistro() {
        ContentValues values = new ContentValues();
        values.put("sMarca", edMarca.getText().toString());
        values.put("sModelo", edModelo.getText().toString());

        mDatabase.update("MD_Vehiculos", values, "ID_Vehiculo=?", new String[]{String.valueOf(IdVehiculo)});
        Toast.makeText(this, "Se ha actualizado el registro con exito", Toast.LENGTH_SHORT).show();
        BorrarTodo();


    }
    private void BorrarTodo() {
        edMarca.setText("");
        edModelo.setText("");

    }
}