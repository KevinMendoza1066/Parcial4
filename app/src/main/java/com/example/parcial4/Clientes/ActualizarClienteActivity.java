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
import com.example.parcial4.R;

public class ActualizarClienteActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private EditText edNombre;
    private EditText edApellido;
    private EditText edDireccion;
    private EditText edCiudad;
    private Button btnActualizarCliente;

    private int IdCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cliente);
        SQLiteOpenHelper dbHelper = new BaseDatosHelper(getApplicationContext(), "tallerParcial4.db", null, 2);
        mDatabase = dbHelper.getWritableDatabase();
        edNombre = findViewById(R.id.edNombre);
        edApellido = findViewById(R.id.edApellido);
        edDireccion = findViewById(R.id.edDireccion);
        edCiudad = findViewById(R.id.edCiudad);
        btnActualizarCliente = findViewById(R.id.btnActualizarCliente);
        Intent intent = getIntent();
        IdCliente = intent.getIntExtra("id_registro", -1); // Valor predeterminado en caso de que no se encuentre el dato
        edNombre.setText(intent.getStringExtra("Nombre"));
        edApellido.setText(intent.getStringExtra("Apellido"));
        edDireccion.setText(intent.getStringExtra("Direccion"));
        edCiudad.setText(intent.getStringExtra("Ciudad"));

        btnActualizarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRegistro();
            }
        });
    }
    private void actualizarRegistro() {
            ContentValues values = new ContentValues();
            values.put("sNombreCliente", edNombre.getText().toString());
            values.put("sApellidoCliente", edApellido.getText().toString());
            values.put("sDireccionCliente",edDireccion.getText().toString());
            values.put("sCiudadCliente", edCiudad.getText().toString());
            mDatabase.update("MD_Clientes", values, "ID_cliente=?", new String[]{String.valueOf(IdCliente)});
            Toast.makeText(this, "Se ha actualizado el registro con exito", Toast.LENGTH_SHORT).show();
    BorrarTodo();


    }
    private void BorrarTodo() {
        edNombre.setText("");
        edApellido.setText("");
        edDireccion.setText("");
        edCiudad.setText("");

    }

}