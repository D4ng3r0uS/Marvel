package com.moviles.practica.marvel.retrofit;

import com.moviles.practica.marvel.modelos.comics.ComicDataWrapper;
import com.moviles.practica.marvel.modelos.eventos.EventDataWrapper;
import com.moviles.practica.marvel.modelos.personajes.CharacterDataWrapper;
import com.moviles.practica.marvel.modelos.series.SerieDataWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface RetrofitMarvelService {

    @GET("/v1/public/characters")
    void getCharacters(@Query("limit") int limit, @Query("offset") int offset, Callback<CharacterDataWrapper> callback);

    @GET("/v1/public/events")
    void getEvents(@Query("limit") int limit, @Query("offset") int offset, @Query("orderBy") String orden, Callback<EventDataWrapper> callback);

    @GET("/v1/public/comics")
    void getComics(@Query("limit") int limit, @Query("offset") int offset, @Query("orderBy") String orden, Callback<ComicDataWrapper> callback);

    @GET("/v1/public/series")
    void getSeries(@Query("limit") int limit, @Query("offset") int offset, @Query("contains") String tipo, @Query("orderBy") String orden, Callback<SerieDataWrapper> callback);

}
