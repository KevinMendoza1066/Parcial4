package com.example.parcial4.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatosHelper extends SQLiteOpenHelper {

    public BaseDatosHelper(@Nullable Context contexto, @Nullable String Nombre, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, Nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DataBase) {
        // Crear la tabla MD_Clientes
        DataBase.execSQL("CREATE TABLE MD_Clientes (ID_Cliente INTEGER PRIMARY KEY, " +
                "sNombreCliente TEXT, sApellidoCliente TEXT,"+
                " sDireccionCliente TEXT, sCiudadCliente TEXT)");

        // Crear la tabla MD_Vehiculos
        DataBase.execSQL("CREATE TABLE MD_Vehiculos (ID_Vehiculo INTEGER PRIMARY KEY,sMarca TEXT, sModelo TEXT)");

        // Crear la tabla MD_ClienteVehiculo
        DataBase.execSQL("CREATE TABLE MD_ClienteVehiculo (ID_VC INTEGER PRIMARY KEY,ID_Cliente INTEGER, " +
                "ID_Vehiculo INTEGER, sMatricula TEXT, iKilometros INTEGER, " +
                "FOREIGN KEY (ID_Cliente) REFERENCES MD_Clientes(ID_Cliente), " +
                "FOREIGN KEY (ID_Vehiculo) REFERENCES MD_Vehiculos(ID_Vehiculo))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
