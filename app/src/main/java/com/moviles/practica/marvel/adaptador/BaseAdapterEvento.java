package com.moviles.practica.marvel.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviles.practica.marvel.R;
import com.moviles.practica.marvel.modelos.eventos.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterEvento extends BaseAdapter {

    ArrayList<Event> myList = new ArrayList<Event>();
    LayoutInflater inflater;
    Context context;

    public BaseAdapterEvento(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void setEventList(List<Event> listaNuevos) {

        //myList.clear();
        myList.addAll(listaNuevos);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Event getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_eventos, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        Event currentListData = getItem(position);

        mViewHolder.tvTitle.setText(currentListData.getTitle());
        mViewHolder.txDescri.setText(currentListData.getDescription());
        Picasso.with(context).load(currentListData.getThumbnail().getPath() + "." + currentListData.getThumbnail().getExtension()).into(mViewHolder.ivIcon);


        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle,txDescri;
        ImageView ivIcon;

        public MyViewHolder(View item) {
            txDescri = (TextView) item.findViewById(R.id.tx_desEven);
            tvTitle = (TextView) item.findViewById(R.id.tx_nomEven);
            ivIcon = (ImageView) item.findViewById(R.id.ivEvento);
        }
    }
}