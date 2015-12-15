package com.moviles.practica.marvel.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.moviles.practica.marvel.MainActivity;
import com.moviles.practica.marvel.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class LoginSign extends Activity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Bind(R.id.editTextUserNameToLogin)
    EditText editTextUserName;
    @Bind(R.id.editTextPasswordToLogin)
    EditText editTextPassword;
    @Bind(R.id.login_error)
    TextView login_error;
    private SharedPreferences pref;
    private boolean hacer_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        hacer_login = pref.getBoolean("login", true);
        if (!hacer_login) {
            finish();
            startActivity(new Intent(LoginSign.this, MainActivity.class));
        } else {

            setContentView(R.layout.login);
            ButterKnife.bind(this);
            // create a instance of SQLite Database
            loginDataBaseAdapter = new LoginDataBaseAdapter(this);
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        }

    }

    @OnFocusChange(R.id.editTextUserNameToLogin)
    public void cambia_foco() {
        login_error.setText("");
    }

    @OnClick(R.id.txt_regisUser)
    public void usuario_nuevo() {
        startActivity(new Intent(LoginSign.this, SignUPActivity.class));
    }

    @OnClick(R.id.buttonSignIn)
    public void boton_entrar() {

        // get The User name and Password
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

        // fetch the Password form database for respective user name
        String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

        // check if the Stored password matches with  Password entered by user
        if (password.equals(storedPassword)) {
            finish();
            startActivity(new Intent(LoginSign.this, MainActivity.class));

        } else {
            login_error.setText("Usuario o contraseña no valida.");
            editTextPassword.setText("");
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (hacer_login) {
            // Close The Database
            loginDataBaseAdapter.close();
        }
    }
}
