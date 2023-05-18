package com.example.parcial4.Vehiculos;

public class VehiculosModel {
    private int id;
    private String Marca;
    private String Modelo;

    public VehiculosModel(int id, String Marca, String Modelo) {
        this.id = id;
        this.Marca = Marca;
        this.Modelo = Modelo;

    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return Marca;
    }
    public String getModelo() {
        return Modelo;
    }


}
