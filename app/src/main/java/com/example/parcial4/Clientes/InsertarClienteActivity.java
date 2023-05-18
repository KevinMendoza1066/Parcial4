package com.example.parcial4.Clientes;

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
import com.example.parcial4.ClienteVehiculos.VerClientesAsociadosActivity;
import com.example.parcial4.MainActivity;
import com.example.parcial4.R;

public class InsertarClienteActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private EditText edNombre;
    private EditText edApellido;
    private EditText edDireccion;
    private EditText edCiudad;
    private Button btnInsertarCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_cliente);
        SQLiteOpenHelper dbHelper = new BaseDatosHelper(getApplicationContext(), "tallerParcial4.db", null, 2);
        mDatabase = dbHelper.getWritableDatabase();
        edNombre = findViewById(R.id.edNombre);
        edApellido = findViewById(R.id.edApellido);
        edDireccion = findViewById(R.id.edDireccion);
        edCiudad = findViewById(R.id.edCiudad);
        btnInsertarCliente = findViewById(R.id.btnInsertarCliente);
        btnInsertarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCliente();
            }
        });
    }
    private void agregarCliente() {
        ContentValues values = new ContentValues();
        values.put("sNombreCliente", edNombre.getText().toString());
        values.put("sApellidoCliente", edApellido.getText().toString());
        values.put("sDireccionCliente",edDireccion.getText().toString());
        values.put("sCiudadCliente", edCiudad.getText().toString());
        mDatabase.insert("MD_Clientes", null, values);
        Toast.makeText(this, "Se ha agregado un registro con éxito", Toast.LENGTH_SHORT).show();
        BorrarTodo();


    }
    private void BorrarTodo() {
        edNombre.setText("");
        edApellido.setText("");
        edDireccion.setText("");
        edCiudad.setText("");

    }
}