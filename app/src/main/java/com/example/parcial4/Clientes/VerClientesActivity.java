package com.example.parcial4.Clientes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.example.parcial4.BaseDatos.BaseDatosHelper;
import com.example.parcial4.MainActivity;
import com.example.parcial4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VerClientesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClientesAdapter tablaAdapter;
    private List<ClienteModel> listaRegistros;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clientes);
        SQLiteOpenHelper dbHelper = new BaseDatosHelper(getApplicationContext(), "tallerParcial4.db", null, 2);
        // Inicializar la base de datos
        database = dbHelper.getWritableDatabase();

        // Configurar el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaRegistros = new ArrayList<>();
        tablaAdapter = new ClientesAdapter(listaRegistros,database);
        recyclerView.setAdapter(tablaAdapter);

        // Cargar los registros de la tabla
        cargarRegistros();
        tablaAdapter.notifyDataSetChanged();
        // Configurar el botón flotante para insertar registros
        FloatingActionButton fabInsertar = findViewById(R.id.fab_insertar);
        fabInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InsertarClienteActivity.class);

                startActivity(intent);
            }
        });
    }

    private void cargarRegistros() {
        // Consultar los registros de la tabla y agregarlos a la lista
        Cursor cursor = database.rawQuery("SELECT * FROM MD_Clientes", null);
        if (cursor.moveToFirst() && cursor.getCount()>0) {
            do {
                // Obtener los datos de cada registro
                int id = Integer.parseInt(cursor.getString(0));
                String nombre = cursor.getString(1);
                String apellido = cursor.getString(2);
                String Direccion = cursor.getString(3);
                String Ciudad = cursor.getString(4);
                // Agregar el registro a la lista
                listaRegistros.add(new ClienteModel(id, nombre, apellido,Direccion,Ciudad));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Notificar al adaptador que los datos han cambiado
        tablaAdapter.notifyDataSetChanged();
    }


}