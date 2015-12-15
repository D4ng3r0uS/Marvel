package com.moviles.practica.marvel.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.moviles.practica.marvel.R;
import com.moviles.practica.marvel.adaptador.BaseAdapterComic;
import com.moviles.practica.marvel.modelos.comics.ComicDataWrapper;
import com.moviles.practica.marvel.retrofit.GetDatos;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Sergio on 17/11/2015.
 */
public class ComicFragment extends Fragment {

    private final String ASD = "Comics";
    private List<com.moviles.practica.marvel.modelos.comics.Comic> comicLista;
    private BaseAdapterComic adapt;
    private ListView lvCustomList;
    private GetDatos getDatos;
    private ProgressBar progressBar;
    private Activity actividad_main;
    private View view;
    private int offset = 0;
    private boolean loading = false;
    private int total = 0;
    private ArrayList<String> urlComic;
    private SharedPreferences pref;
    private String orden;

    @Override
    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle) {
        view = layoutinflater.inflate(R.layout.activity_principal, viewgroup, false);
        actividad_main = getActivity();
        lvCustomList = (ListView) view.findViewById((R.id.lvCustomList));
        progressBar = (ProgressBar) view.findViewById((R.id.progressBar));
        adapt = new BaseAdapterComic(actividad_main);
        lvCustomList.setAdapter(adapt);
        actividad_main.setTitle("Comics");
        urlComic = new ArrayList<String>();
        pref = PreferenceManager.getDefaultSharedPreferences(actividad_main);
        orden = pref.getString("OrdenComic", "-focDate");
        generaComics();
        return view;
    }

    private void stopLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public void guardaUrl() {
        for (int x = 0; x < comicLista.size(); x++) {
            urlComic.add(comicLista.get(x).getUrls().get(0).getUrl());
        }
    }

    public void generaComics() {
        getDatos = new GetDatos();

        getDatos.getListaComics(offset,orden, new Callback<ComicDataWrapper>() {

                    @Override
                    public void success(ComicDataWrapper comicDataWrapper, Response response) {
                        comicLista = comicDataWrapper.getData().getResults();
                        total = comicDataWrapper.getData().getTotal();


                        stopLoading();
                        guardaUrl();
                        adapt.setComicList(comicLista);
                        loading = false;

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(ASD, "Error ", error);
                    }
                }

        );
        lvCustomList.setOnScrollListener(new AbsListView.OnScrollListener() {
                                             @Override
                                             public void onScrollStateChanged(AbsListView view, int scrollState) {
                                             }

                                             @Override
                                             public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                                                  int totalItemCount) {
                                                 boolean lastItem = (firstVisibleItem + visibleItemCount == totalItemCount);
                                                 boolean moreRows = offset < total;

                                                 if (!loading && lastItem && moreRows) {
                                                     offset = offset + 10;
                                                     loading = true;
                                                     startLoading();
                                                     generaComics();
                                                 }
                                             }
                                         }

        );


        lvCustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intencion = new Intent(Intent.ACTION_VIEW);
                intencion.setData(Uri.parse(urlComic.get(position)));
                startActivity(intencion);
            }
        });
    }
}