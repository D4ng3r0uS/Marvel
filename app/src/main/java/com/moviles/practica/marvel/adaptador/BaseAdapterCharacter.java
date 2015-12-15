package com.moviles.practica.marvel.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviles.practica.marvel.R;
import com.moviles.practica.marvel.modelos.personajes.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaseAdapterCharacter extends BaseAdapter {

    ArrayList<Character> myList = new ArrayList<Character>();
    LayoutInflater inflater;
    Context context;

    public BaseAdapterCharacter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void setCharacterList(ArrayList<Character> listaNuevos) {
        // myList.clear();
        myList.addAll(listaNuevos);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Character getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_personajes, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        Character currentListCharacter = getItem(position);
        String imagen = currentListCharacter.getThumbnail().getPath() + "." + currentListCharacter.getThumbnail().getExtension();

        mViewHolder.tvNombre.setText(currentListCharacter.getName());
        Picasso.with(context).load(imagen).resize(120, 120).into(mViewHolder.ivIcon);

        return convertView;
    }

    private class MyViewHolder {
        TextView tvNombre;
        ImageView ivIcon;

        public MyViewHolder(View item) {
            tvNombre = (TextView) item.findViewById(R.id.tvNombre);
            ivIcon = (ImageView) item.findViewById(R.id.ivPersonaje);
        }
    }
}