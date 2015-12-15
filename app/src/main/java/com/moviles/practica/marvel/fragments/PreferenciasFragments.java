package com.moviles.practica.marvel.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import com.moviles.practica.marvel.R;

public class PreferenciasFragments extends PreferenceFragment {
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);


    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        pref = PreferenceManager.getDefaultSharedPreferences(
                getActivity());
        Log.i("", "Opción series: " + pref.getString("OrdenSeries", ""));

        super.onPause();
    }


}