package com.moviles.practica.marvel.adaptador;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviles.practica.marvel.R;
import com.moviles.practica.marvel.modelos.eventos.Event;
import com.moviles.practica.marvel.modelos.series.Serie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterSerie extends BaseAdapter {

    ArrayList<Serie> myList = new ArrayList<Serie>();
    LayoutInflater inflater;
    Context context;

    public BaseAdapterSerie(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void setSerieList(List<Serie> listaNuevos) {

        //myList.clear();
        myList.addAll(listaNuevos);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Serie getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_series, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        Serie currentListSerie = getItem(position);

        String rutaImagen = currentListSerie.getThumbnail().getPath() + "." + currentListSerie.getThumbnail().getExtension();

        mViewHolder.tvTitle.setText(currentListSerie.getTitle());
        try {
            mViewHolder.txFecha.setText("Año: " + currentListSerie.getStartYear());
        } catch (Resources.NotFoundException e) {
            mViewHolder.txFecha.setText("Año: ");
        }

        Picasso.with(context).load(rutaImagen).resize(120, 180).into(mViewHolder.ivIcon);


        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle, txDescri, txFecha;
        ImageView ivIcon;

        public MyViewHolder(View item) {
            txFecha = (TextView) item.findViewById(R.id.tx_fechaSerie);
            tvTitle = (TextView) item.findViewById(R.id.tx_nomSerie);
            ivIcon = (ImageView) item.findViewById(R.id.ivSerie);
        }
    }
}