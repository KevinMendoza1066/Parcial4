package com.example.parcial4.Vehiculos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.parcial4.R;

import java.util.List;

public class VehiculosAdapter extends RecyclerView.Adapter<VehiculosAdapter.TablaViewHolder> {

    private List<VehiculosModel> listaRegistros;
    private SQLiteDatabase database;
    public VehiculosAdapter(List<VehiculosModel> listaRegistros,SQLiteDatabase database) {
        this.listaRegistros = listaRegistros;
        this.database = database;
    }

    @NonNull
    @Override
    public VehiculosAdapter.TablaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro2, parent, false);
        return new VehiculosAdapter.TablaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculosAdapter.TablaViewHolder holder, int position) {
        VehiculosModel registro = listaRegistros.get(position);
        holder.bind(registro);
    }

    @Override
    public int getItemCount() {
        return listaRegistros.size();
    }

    public class TablaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtModelo;
        private TextView txtMarca;
        private ImageButton btnEliminar;
        private ImageButton btnActualizar;
        private TextView txtId;
        public TablaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId =itemView.findViewById(R.id.txt_id);
            txtMarca = itemView.findViewById(R.id.txt_Marca);
            txtModelo = itemView.findViewById(R.id.txt_Modelo);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
            btnActualizar = itemView.findViewById(R.id.btn_actualizar);
        }

        public void bind(VehiculosModel registro) {
            txtMarca.setText(registro.getMarca());
            txtModelo.setText(registro.getModelo());

            // Configurar el botón eliminar
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Eliminar el registro correspondiente
                    eliminarRegistro(registro,v);
                }
            });

            // Configurar el botón actualizar
            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Abrir una actividad para actualizar el registro
                    Intent intent = new Intent(itemView.getContext(), ActualizarVehiculoActivity.class);
                    intent.putExtra("id_registro", registro.getId());
                    intent.putExtra("Marca", registro.getMarca());
                    intent.putExtra("Modelo", registro.getModelo());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    private void eliminarRegistro(VehiculosModel registro,View v) {
        database.delete("MD_Vehiculos", "ID_Vehiculo=?", new String[]{String.valueOf(registro.getId())});
        Toast.makeText(v.getContext(), "Se elimino el registro correctamente", Toast.LENGTH_SHORT).show();
        listaRegistros.remove(registro);
        notifyDataSetChanged();
    }
}
