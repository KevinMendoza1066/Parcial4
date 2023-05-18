package com.example.parcial4.Clientes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial4.R;

import java.util.List;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.TablaViewHolder> {
    private List<ClienteModel> listaRegistros;
    private SQLiteDatabase database;
    public ClientesAdapter(List<ClienteModel> listaRegistros,SQLiteDatabase database) {
        this.listaRegistros = listaRegistros;
        this.database = database;
    }

    @NonNull
    @Override
    public TablaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro, parent, false);
        return new TablaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TablaViewHolder holder, int position) {
        ClienteModel registro = listaRegistros.get(position);
        holder.bind(registro);
    }

    @Override
    public int getItemCount() {
        return listaRegistros.size();
    }

    public class TablaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombre;
        private TextView txtApellido;
        private ImageButton btnEliminar;
        private ImageButton btnActualizar;
        private TextView txtId;
        public TablaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId =itemView.findViewById(R.id.txt_id);
            txtNombre = itemView.findViewById(R.id.txt_nombre);
            txtApellido = itemView.findViewById(R.id.txt_apellido);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
            btnActualizar = itemView.findViewById(R.id.btn_actualizar);
        }

        public void bind(ClienteModel registro) {
            txtNombre.setText(registro.getNombre());
            txtApellido.setText(registro.getApellido());

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
                    Intent intent = new Intent(itemView.getContext(), ActualizarClienteActivity.class);
                    intent.putExtra("id_registro", registro.getId());
                    intent.putExtra("Nombre", registro.getNombre());
                    intent.putExtra("Apellido", registro.getApellido());
                    intent.putExtra("Direccion", registro.getDireccion());
                    intent.putExtra("Ciudad", registro.getCiudad());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    private void eliminarRegistro(ClienteModel registro,View v) {
        database.delete("MD_Clientes", "ID_cliente=?", new String[]{String.valueOf(registro.getId())});
        Toast.makeText(v.getContext(), "Se elimino el registro correctamente", Toast.LENGTH_SHORT).show();
        listaRegistros.remove(registro);
        notifyDataSetChanged();
    }

}
