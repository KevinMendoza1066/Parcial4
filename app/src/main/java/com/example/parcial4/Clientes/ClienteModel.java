package com.example.parcial4.Clientes;

public class ClienteModel {
        private int id;
        private String nombre;
        private String apellido;
        private String Direccion;
        private String Ciudad;
        public ClienteModel(int id, String nombre, String apellido,String Direccion,String Ciudad) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.Direccion = Direccion;
            this.Ciudad = Ciudad;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }
        public String getDireccion() {
            return Direccion;
        }
        public String getCiudad() {
            return Ciudad;
        }
        public String getApellido() {
            return apellido;
        }


}
