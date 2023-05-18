package com.example.parcial4.Vehiculos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

import com.example.parcial4.BaseDatos.BaseDatosHelper;
import com.example.parcial4.Clientes.ClienteModel;
import com.example.parcial4.Clientes.ClientesAdapter;
import com.example.parcial4.Clientes.InsertarClienteActivity;
import com.example.parcial4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VerVehiculosActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private VehiculosAdapter tablaAdapter;
    private List<VehiculosModel> listaRegistros;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_vehiculos);
        SQLiteOpenHelper dbHelper = new BaseDatosHelper(getApplicationContext(), "tallerParcial4.db", null, 2);
        // Inicializar la base de datos
        database = dbHelper.getWritableDatabase();

        // Configurar el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaRegistros = new ArrayList<>();
        tablaAdapter = new VehiculosAdapter(listaRegistros,database);
        recyclerView.setAdapter(tablaAdapter);

        // Cargar los registros de la tabla
        cargarRegistros();
        tablaAdapter.notifyDataSetChanged();
        // Configurar el botón flotante para insertar registros
        FloatingActionButton fabInsertar = findViewById(R.id.fab_insertar);
        fabInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InsertarVehiculoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargarRegistros() {
        // Consultar los registros de la tabla y agregarlos a la lista
        Cursor cursor = database.rawQuery("SELECT * FROM MD_Vehiculos", null);
        if (cursor.moveToFirst() && cursor.getCount()>0) {
            do {
                // Obtener los datos de cada registro
                int id = Integer.parseInt(cursor.getString(0));
                String Marca = cursor.getString(1);
                String Modelo = cursor.getString(2);

                // Agregar el registro a la lista
                listaRegistros.add(new VehiculosModel(id, Marca,Modelo));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Notificar al adaptador que los datos han cambiado
        tablaAdapter.notifyDataSetChanged();
    }



}