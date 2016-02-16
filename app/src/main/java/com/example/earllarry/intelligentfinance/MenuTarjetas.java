package com.example.earllarry.intelligentfinance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class MenuTarjetas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tarjetas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageAgregar = (ImageView)findViewById(R.id.imageViewAgregarTarjeta);
        ImageView imageInforme = (ImageView)findViewById(R.id.imageViewInformeTarjeta);

        imageAgregar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(MenuTarjetas.this, AgregarTarjeta.class));
            }
        });

        imageInforme.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(MenuTarjetas.this, InformeTarjeta.class));
            }
        });

    }

}
