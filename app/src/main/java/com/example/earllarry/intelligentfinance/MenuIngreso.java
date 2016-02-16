package com.example.earllarry.intelligentfinance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class MenuIngreso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ingreso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageAgregar = (ImageView)findViewById(R.id.imageViewAgregarIngreso);
        ImageView imageInforme = (ImageView)findViewById(R.id.imageViewInformeIngreso);

        imageAgregar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(MenuIngreso.this, AgregarIngreso.class));
            }
        });

        imageInforme.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(MenuIngreso.this, InformeIngreso.class));
            }
        });

    }

}
