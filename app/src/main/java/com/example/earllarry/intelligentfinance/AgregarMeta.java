package com.example.earllarry.intelligentfinance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarMeta extends AppCompatActivity implements View.OnClickListener {

    Button buttonCancelar;
    Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_meta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DBConnection connection = new DBConnection(AgregarMeta.this);

        final EditText editTextConcepto = (EditText)findViewById(R.id.editTextConceptoMeta);
        final EditText editTextMonto = (EditText)findViewById(R.id.editTextMontoMeta);
        final EditText editTextFechaInicio = (EditText)findViewById(R.id.editTextFechaInicioMeta);
        final EditText editTextFechaFinal = (EditText)findViewById(R.id.editTextFechaFinalMeta);

        buttonCancelar = (Button)findViewById(R.id.buttonCancelarMeta);
        buttonCancelar.setOnClickListener(this);
        buttonGuardar = (Button)findViewById(R.id.buttonGuardarMeta);
        buttonGuardar.setOnClickListener(this);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgregarMeta.this, MenuMetas.class));
                finish();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextConcepto.getText().toString().isEmpty() || editTextMonto.getText().toString().isEmpty() ||
                        editTextFechaInicio.getText().toString().isEmpty() || editTextFechaFinal.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Llenar campos",
                            Toast.LENGTH_LONG).show();
                } else {

                    String helpConcepto = String.valueOf(editTextConcepto.getText());
                    double helpMonto = Double.valueOf(editTextMonto.getText().toString());
                    String helpFechaInicio = String.valueOf(editTextFechaInicio.getText());
                    String helpFechaFinal = String.valueOf(editTextFechaFinal.getText());

                    connection.insertMeta(helpConcepto, helpMonto, helpFechaInicio, helpFechaFinal);

                    Toast.makeText(getApplicationContext(), "Meta Agregada",
                            Toast.LENGTH_LONG).show();

                    //ir al Menu Metas
                    startActivity(new Intent(AgregarMeta.this, MenuMetas.class));
                    finish();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
