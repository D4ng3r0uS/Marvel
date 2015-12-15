package com.moviles.practica.marvel;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moviles.practica.marvel.fragments.ComicFragment;
import com.moviles.practica.marvel.fragments.EventoFragment;
import com.moviles.practica.marvel.fragments.PersonajeFragment;
import com.moviles.practica.marvel.fragments.SerieFragment;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private SensorManager mSensorManager;

    private String log = "";
    private Time hora;

    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
        notificacion_salir();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        inicializaSensor();


        fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frame_container, new EventoFragment()).commit();

    }


    public void inicializaSensor() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] == 0) {
            toast();
        }
    }

    public void toast() {
        Toast toastp = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.lyt_toast));
        TextView txtMsg = (TextView) layout.findViewById(R.id.txt_Mensaje);
        txtMsg.setText("Llamando a los vengadores");
        txtMsg.setTextSize(30);
        toastp.setDuration(Toast.LENGTH_LONG);
        toastp.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toastp.setView(layout);
        toastp.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void notificacion_salir() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setLargeIcon((((BitmapDrawable) getResources()
                                .getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                        .setContentTitle("Vuelve pronto")
                        .setContentText("Esperamos tu regreso")
                        .setTicker("Hasta luego");

        Intent notIntent = new Intent(this, MainActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(this, 0, notIntent, 0);
        mBuilder.setContentIntent(contIntent);

        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(4, mBuilder.build());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                log += "\nAcciones -> " + DateFormat.getDateTimeInstance().format(new Date());
                startActivity(new Intent(MainActivity.this, Preferencias.class));
                return true;
            case R.id.action_creditos:
                log += "\nCreditos -> " + DateFormat.getDateTimeInstance().format(new Date());
                startActivity(new Intent(MainActivity.this, AcerdaDe.class));
                return true;
            case R.id.action_log:
                Intent registro = new Intent(MainActivity.this, RegistroLog.class);
                registro.putExtra("log", log);
                log = "";
                startActivity(registro);
                return true;
            case R.id.action_salir:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_eventos) {
            log += "\nEventos -> " + DateFormat.getDateTimeInstance().format(new Date());
            fragment = new EventoFragment();
        } else if (id == R.id.nav_Personajes) {
            log += "\nPersonajes -> " + DateFormat.getDateTimeInstance().format(new Date());
            fragment = new PersonajeFragment();
        } else if (id == R.id.nav_Comics) {
            log += "\nComics -> " + DateFormat.getDateTimeInstance().format(new Date());
            fragment = new ComicFragment();
        } else if (id == R.id.nav_Series) {
            log += "\nSeries -> " + DateFormat.getDateTimeInstance().format(new Date());
            fragment = new SerieFragment();
        } else if (id == R.id.nav_conf) {
            log += "\nAjustes -> " + DateFormat.getDateTimeInstance().format(new Date());
            startActivity(new Intent(MainActivity.this, Preferencias.class));
            return true;
        }
        if (fragment != null) {
            fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.frame_container, fragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
