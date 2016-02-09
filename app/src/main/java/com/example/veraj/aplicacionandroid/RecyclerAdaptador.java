package com.example.veraj.aplicacionandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerAdaptador  extends RecyclerView.Adapter<RecyclerAdaptador.ViewHolder>{

    List<Dato> datos;
    Context context;//para que es -->>

    RecyclerAdaptador(List<Dato> datos, Context context){

        this.datos = datos;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {//-- se crea los componentes donde se van a ver
        CardView cv;
        TextView fecha;
        TextView compra;
        TextView venta;

        //TextView actCompra;
        //TextView actVenta;


        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.recycler);

            fecha = (TextView) itemView.findViewById(R.id.fecha);
            compra = (TextView)itemView.findViewById(R.id.compra);
            venta = (TextView) itemView.findViewById(R.id.venta);
            //actCompra = (TextView) itemView.findViewById(R.id.preActualCompra);

           /* nameAuthor = (TextView)itemView.findViewById(R.id.nameAuthor);
            photo = (ImageView)itemView.findViewById(R.id.imagePhoto);*/
        }
    }

/*se crean los intens*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }
/*se da los valores a  la vista*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        //holder.actCompra.setText(datos.get(position).getCompra());
        holder.fecha.setText(datos.get(position).getFecha());
        holder.compra.setText(datos.get(position).getCompra());
        holder.venta.setText(datos.get(position).getVenta());



        //final TextView texto = (TextView) findViewById(R.id.editText);
        //Toast mensaje = Toast.makeText(getApplicationContext(),"Cargando Perfil de "+datos.get(0).getCompra().toString(), Toast.LENGTH_SHORT);
        //mensaje.show();

        /*Intent myIntent = new Intent.this, ContentActivity.class);
        myIntent.putExtra("key", texto.getText().toString()); //Optional parameters
        MainActivity.this.startActivity(myIntent);*/
        /*
        holder.nameAuthor.setText(datos.get(position).getFecha());
        //holder.photo.setImageResource(paisaje.get(position).photoId);
        if(paisaje.get(position).getPoster() != null){
            Picasso.with(context).load(paisaje.get(position).getPoster()).into(holder.photo);
        }*/
    }

    @Override
    public int getItemCount() {

        return datos.size();
    }
    /*public void otros(View v){
        TextView actCompra = (TextView)findViewById(R.id.preActualCompra);
        TextView actVenta = (TextView)findViewById(R.id.precioActualVenta);
        actCompra.setText(datos.get(0).compra);
        actVenta.setText(datos.get(0).venta);
    }*/
}