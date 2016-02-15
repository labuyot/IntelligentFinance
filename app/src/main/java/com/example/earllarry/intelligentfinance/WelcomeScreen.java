package com.example.earllarry.intelligentfinance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences mPrefs;
    final String welcomeScreenShownPref = "welcomeScreenShown";

    Button buttonWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        buttonWelcome = (Button)findViewById(R.id.buttonWelcome);
        buttonWelcome.setOnClickListener(this);
        final EditText editTextNombre = (EditText)findViewById(R.id.editTextNombre);

        mPrefs = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(mPrefs.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, DashboardDrawer.class);
            startActivity(intent);
            finish();
        } else {

            buttonWelcome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (editTextNombre.getText().toString().isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Llenar campo",
                                Toast.LENGTH_LONG).show();
                    } else {

                        SharedPreferences.Editor ed = mPrefs.edit();
                        ed.putBoolean("activity_executed", true);
                        ed.commit(); // Very important to save the preference

                        //ir al Dashboard
                        startActivity(new Intent(WelcomeScreen.this, DashboardDrawer.class));
                        finish();
                    }
                }
            });
        }
    }

    public void onClickButton(){

        startActivity(new Intent(WelcomeScreen.this, DashboardDrawer.class));

    }

    @Override
    public void onClick(View v) {



    }
}
