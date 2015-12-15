package com.moviles.practica.marvel.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.moviles.practica.marvel.PersonajeDescripcion;
import com.moviles.practica.marvel.R;
import com.moviles.practica.marvel.adaptador.*;
import com.moviles.practica.marvel.modelos.personajes.Character;
import com.moviles.practica.marvel.retrofit.*;

import java.util.ArrayList;
import java.util.List;

public class PersonajeFragment extends Fragment {

    private final String ASD = "PersonajeFragment";
    private List<Character> characterList;
    private ArrayList<Character> listaCompleta;

    private BaseAdapterCharacter adapt;
    private ListView lvCustomList;
    private GetDatos getDatos;
    private ProgressBar progressBar;
    private Activity actividad_main;
    private View view;
    private SharedPreferences pref;
    private int maximo;
    private boolean filtro;

    @Override
    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle) {
        view = layoutinflater.inflate(R.layout.activity_principal, viewgroup, false);
        actividad_main = getActivity();
        lvCustomList = (ListView) view.findViewById((R.id.lvCustomList));
        progressBar = (ProgressBar) view.findViewById((R.id.progressBar));
        adapt = new BaseAdapterCharacter(actividad_main);
        lvCustomList.setAdapter(adapt);
        actividad_main.setTitle("Personajes");

        if (listaCompleta == null) {
            listaCompleta = new ArrayList<>();
            pref = PreferenceManager.getDefaultSharedPreferences(actividad_main);
            maximo = Integer.parseInt(pref.getString("heroeMax", "1000"));
            filtro = pref.getBoolean("filtroHeroes", true);
            generaPersonajes();
        }
        return view;
    }


    private void stopLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void filtroHeroes() {
        Character heroe;
        for (int x = 0; x < characterList.size(); x++) {
            heroe = characterList.get(x);
            if (!heroe.getThumbnail().getPath().contains("image_not_available") && !heroe.getDescription().equals("")) {
                listaCompleta.add(heroe);
            } else if (!filtro) {
                listaCompleta.add(heroe);
            }
        }
    }

    public void generaPersonajes() {
        getDatos = new GetDatos();

        getDatos.getListaSuperheroes(maximo, new GetDatos.CallbackCharacter() {
            @Override
            public void onSuccess(List<Character> personajes) {
                characterList = personajes;
                filtroHeroes();
                stopLoading();
                adapt.setCharacterList(listaCompleta);
            }

            @Override
            public void onError() {

                Toast.makeText(actividad_main.getApplicationContext(),
                        R.string.error_personajes, Toast.LENGTH_LONG).show();
            }
        });

        lvCustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intencion = new Intent(actividad_main, PersonajeDescripcion.class);
                intencion.putExtra("personajes", listaCompleta);
                intencion.putExtra("posicion", position);
                startActivity(intencion);
            }
        });
    }


    public interface FragmentIterationListener {
        public void onFragmentIteration(Bundle parameters);
    }
}