package com.moviles.practica.marvel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviles.practica.marvel.R;
import com.moviles.practica.marvel.modelos.personajes.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonajeDescripcion extends Activity {

    private final String ASD = "PersonajeDescripcion";
    @Bind(R.id.tv_nombreDes)
    TextView tx_nombre;
    @Bind(R.id.tv_descPer)
    TextView tx_descr;
    @Bind(R.id.iv_desPer)
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje_descripcion);
        ButterKnife.bind(this);

        ArrayList<Character> personajes=  getIntent().getParcelableArrayListExtra("personajes");
        int posicion = getIntent().getExtras().getInt("posicion");
        tx_nombre.setText(personajes.get(posicion).getName());
        tx_descr.setText(personajes.get(posicion).getDescription());
        String imagenUrl = personajes.get(posicion).getThumbnail().getPath() + "." + personajes.get(posicion).getThumbnail().getExtension();
        Picasso.with(this).load(imagenUrl).into(imagen);

    }
}
