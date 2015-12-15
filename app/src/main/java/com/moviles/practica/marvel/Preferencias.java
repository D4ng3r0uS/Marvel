package com.moviles.practica.marvel;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.moviles.practica.marvel.R;

/**
 * Created by Sergio on 19/11/2015.
 */
public class Preferencias extends PreferenceActivity {
    static private SharedPreferences pref;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reproducirSonido();
        addPreferencesFromResource(R.xml.preferences);
    }
    public void reproducirSonido() {
        boolean activado;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        activado = pref.getBoolean("sonidos", true);
        if (activado) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.intro);
            mp.start();
        }
    }

}
