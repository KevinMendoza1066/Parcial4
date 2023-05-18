package com.example.parcial4.Vehiculos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial4.BaseDatos.BaseDatosHelper;
import com.example.parcial4.R;

public class InsertarVehiculoActivity extends AppCompatActivity {


    private SQLiteDatabase mDatabase;
    private EditText edMarca;
    private EditText edModelo;

    private Button btnInsertarVehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_vehiculo);
        SQLiteOpenHelper dbHelper = new BaseDatosHelper(getApplicationContext(), "tallerParcial4.db", null, 2);
        mDatabase = dbHelper.getWritableDatabase();
        edMarca = findViewById(R.id.edMarca);
        edModelo = findViewById(R.id.edModelo);

        btnInsertarVehiculo = findViewById(R.id.btnInsertarVehiculo);
        btnInsertarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarVehiculo();
            }
        });
    }
    private void agregarVehiculo() {
        ContentValues values = new ContentValues();
        values.put("sMarca", edMarca.getText().toString());
        values.put("sModelo", edModelo.getText().toString());
        mDatabase.insert("MD_Vehiculos", null, values);
        Toast.makeText(this, "Se ha agregado un registro con éxito", Toast.LENGTH_SHORT).show();
        BorrarTodo();


    }
    private void BorrarTodo() {
        edMarca.setText("");
        edModelo.setText("");

    }
}