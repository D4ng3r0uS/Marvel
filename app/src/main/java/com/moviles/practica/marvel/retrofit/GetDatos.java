package com.moviles.practica.marvel.retrofit;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moviles.practica.marvel.modelos.comics.ComicDataWrapper;
import com.moviles.practica.marvel.modelos.eventos.EventDataWrapper;
import com.moviles.practica.marvel.modelos.personajes.*;
import com.moviles.practica.marvel.modelos.personajes.Character;
import com.moviles.practica.marvel.modelos.series.SerieDataWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Sergio on 11/11/2015.
 */
public class GetDatos {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();
    private List<Character> lista = new ArrayList<>();
    private RetrofitMarvelService retrofitMarvelService;
    private int nPersonajes;

    public GetDatos() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://gateway.marvel.com")
                .setRequestInterceptor(new MarvelRequestInterceptor())
                .setConverter(new GsonConverter(gson))
                        //.setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        retrofitMarvelService = restAdapter.create(RetrofitMarvelService.class);
    }


    public void getListaSuperheroes(int maximo, CallbackCharacter callbackCharac) {
        lista.clear();
        nPersonajes = maximo;
        final int limite;
        if (maximo > 100)
            limite = 100;
        else limite = maximo;

        for (int offset = 0; offset < maximo; offset += limite) {
            //++Log.i("Prueba", "succes: " + offset);
            retrofitMarvelService.getCharacters(limite, offset, new CallbackCharacterClass(callbackCharac, limite));
        }

    }

    public void getListaEventos(int offset, String orden, Callback<EventDataWrapper> callback) {

        retrofitMarvelService.getEvents(5, offset, orden, callback);
    }

    public void getListaComics(int offset, String orden, Callback<ComicDataWrapper> callback) {
        retrofitMarvelService.getComics(10, offset, orden, callback);
    }

    public void getListaSeries(int offset, String orden, Callback<SerieDataWrapper> callback) {
        retrofitMarvelService.getSeries(10, offset, "digest", orden, callback);
    }

    public interface CallbackCharacter {
        void onSuccess(List<Character> personajes);

        void onError();

    }

    public class CallbackCharacterClass implements retrofit.Callback<CharacterDataWrapper> {
        CallbackCharacter callbackCharac;
        int limite;

        public CallbackCharacterClass(CallbackCharacter callbackCharac, int limite) {
            this.callbackCharac = callbackCharac;
            this.limite = limite;
        }

        @Override
        public void success(CharacterDataWrapper characterDataWrapper, Response response) {
            lista.addAll(characterDataWrapper.getCharacterDataContainer().getResults());
            Log.i("Prueba", "lista: " + lista.size());
            if (lista.size() >= nPersonajes) {
                callbackCharac.onSuccess(lista);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            nPersonajes = nPersonajes - limite;
            callbackCharac.onError();
        }
    }

}