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
import com.moviles.practica.marvel.adaptador.BaseAdapterSerie;
import com.moviles.practica.marvel.modelos.comics.ComicDataWrapper;
import com.moviles.practica.marvel.modelos.series.SerieDataWrapper;
import com.moviles.practica.marvel.retrofit.GetDatos;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Sergio on 17/11/2015.
 */
public class SerieFragment extends Fragment {

    private static final String ASD = "SerieFragment";
    private List<com.moviles.practica.marvel.modelos.series.Serie> serieLista;
    private BaseAdapterSerie adapt;
    private ListView lvCustomList;
    private GetDatos getDatos;
    private ProgressBar progressBar;
    private Activity actividad_main;
    private View view;
    private int offset = 0;
    private boolean loading = false;
    private int total = 0;
    private ArrayList<String> urlSerie;
    private SharedPreferences pref;
    private String orden;

    @Override
    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle) {
        view = layoutinflater.inflate(R.layout.activity_principal, viewgroup, false);
        actividad_main = getActivity();
        lvCustomList = (ListView) view.findViewById((R.id.lvCustomList));
        progressBar = (ProgressBar) view.findViewById((R.id.progressBar));
        adapt = new BaseAdapterSerie(actividad_main);
        lvCustomList.setAdapter(adapt);
        actividad_main.setTitle("Series");
        urlSerie = new ArrayList<String>();
        pref = PreferenceManager.getDefaultSharedPreferences(actividad_main);
        orden = pref.getString("OrdenSeries", "startYear");
        generaSeries();
        return view;
    }

    private void stopLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public void guardaUrl() {
        for (int x = 0; x < serieLista.size(); x++) {
            urlSerie.add(serieLista.get(x).getUrls().get(0).getUrl());
        }
    }

    public void generaSeries() {
        getDatos = new GetDatos();

        getDatos.getListaSeries(offset,orden, new Callback<SerieDataWrapper>() {

                    @Override
                    public void success(SerieDataWrapper serieDataWrapper, Response response) {
                        serieLista = serieDataWrapper.getData().getResults();
                        total = serieDataWrapper.getData().getTotal();


                        stopLoading();
                        guardaUrl();
                        adapt.setSerieList(serieLista);
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
                                                     generaSeries();
                                                 }
                                             }
                                         }

        );


        lvCustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intencion = new Intent(Intent.ACTION_VIEW);
                intencion.setData(Uri.parse(urlSerie.get(position)));
                startActivity(intencion);
            }
        });
    }
}