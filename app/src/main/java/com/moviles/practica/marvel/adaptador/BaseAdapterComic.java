package com.moviles.practica.marvel.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviles.practica.marvel.R;
import com.moviles.practica.marvel.modelos.comics.Comic;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseAdapterComic extends BaseAdapter {

    ArrayList<Comic> myList = new ArrayList<Comic>();
    LayoutInflater inflater;
    Context context;

    public BaseAdapterComic(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void setComicList(List<Comic> listaNuevos) {

        // myList.clear();
        myList.addAll(listaNuevos);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Comic getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_comics, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        Comic currentListComics = getItem(position);
        String imagen = currentListComics.getThumbnail().getPath() + "." + currentListComics.getThumbnail().getExtension();

        mViewHolder.tvTitle.setText(currentListComics.getTitle());
        try {
            mViewHolder.tvFecha.setText("Fecha: " + recortaFecha(currentListComics.getDates().get(0).getDate()));
            mViewHolder.tvPrecio.setText("Precio: " + currentListComics.getPrices().get(0).getPrice());
        } catch (NullPointerException e) {
            mViewHolder.tvFecha.setText("Fecha: ");
            mViewHolder.tvPrecio.setText("Precio: ");
        }

        Picasso.with(context).load(imagen).resize(120, 180).into(mViewHolder.ivIcon);

        return convertView;
    }

    private String recortaFecha(Date fecha) {
        String local = fecha.toLocaleString();
        String[] split = local.split("00:00");
        return split[0];
    }

    private class MyViewHolder {
        TextView tvTitle;
        TextView tvPrecio;
        TextView tvFecha;
        ImageView ivIcon;

        public MyViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.tx_ComicTitle);
            tvPrecio = (TextView) item.findViewById(R.id.tx_ComicPrecio);
            tvFecha = (TextView) item.findViewById(R.id.tx_ComicFecha);
            ivIcon = (ImageView) item.findViewById(R.id.ivComic);
        }
    }
}