package com.moviles.practica.marvel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class RegistroLog extends AppCompatActivity {

    FileOutputStream fichero_salida;
    TextView tv_log;
    private String fichero_nombre = "RegistroLog.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        tv_log = (TextView) findViewById(R.id.tv_log);
        escribe_log(getIntent().getExtras().getString("log"));

        leer_log();
    }


    public void escribe_log(String log) {
        try {
            fichero_salida = openFileOutput(fichero_nombre, MODE_PRIVATE);
            fichero_salida.write(log.getBytes());
            fichero_salida.write("\n".toString().getBytes());
            fichero_salida.close();
        } catch (IOException e) {
            android.util.Log.e("LOG", e.getMessage(), e);
        }
    }


    public void leer_log() {
        InputStreamReader fichero_lectura = null;
        BufferedReader br;
        String cadena;
        String texto = "";
        try {
            fichero_lectura = new InputStreamReader(openFileInput(fichero_nombre));
            br = new BufferedReader(fichero_lectura);
            cadena = br.readLine();
            while (cadena != null) {
                texto += cadena + "\n";
                cadena = br.readLine();
            }
            tv_log.setText(texto);
        } catch (Exception ex) {
            android.util.Log.e("LOG", "Error leyendo de fichero");
        } finally {
            try {
                if (fichero_lectura != null)
                    fichero_lectura.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
