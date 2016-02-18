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

public class AgregarTarjeta extends AppCompatActivity implements View.OnClickListener {

    Button buttonCancelar;
    Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarjeta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DBConnection connection = new DBConnection(AgregarTarjeta.this);

        final EditText editTextBanco = (EditText)findViewById(R.id.editTextBancoTarjeta);
        final EditText editTextMonto = (EditText)findViewById(R.id.editTextMontoTarjeta);
        final EditText editTextfourDigits = (EditText)findViewById(R.id.editTextFourDigitsTarjeta);
        final EditText editTextInteres = (EditText)findViewById(R.id.editTextInteresTarjeta);
        final EditText editTextCorte = (EditText)findViewById(R.id.editTextCorteTarjeta);
        final EditText editTextVencimiento = (EditText)findViewById(R.id.editTextVencimientoTarjeta);

        buttonCancelar = (Button)findViewById(R.id.buttonCancelarTarjeta);
        buttonCancelar.setOnClickListener(this);
        buttonGuardar = (Button)findViewById(R.id.buttonGuardarTarjeta);
        buttonGuardar.setOnClickListener(this);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgregarTarjeta.this, MenuTarjetas.class));
                finish();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextBanco.getText().toString().isEmpty() || editTextMonto.getText().toString().isEmpty() ||
                        editTextfourDigits.getText().toString().isEmpty() || editTextInteres.getText().toString().isEmpty() ||
                        editTextCorte.getText().toString().isEmpty() || editTextVencimiento.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Llenar campos",
                            Toast.LENGTH_LONG).show();
                } else {

                    String helpBanco = String.valueOf(editTextBanco.getText());
                    double helpMonto = Double.valueOf(editTextMonto.getText().toString());
                    int helpFourDigits = Integer.valueOf(editTextfourDigits.getText().toString());
                    double helpInteres = Double.valueOf(editTextInteres.getText().toString());
                    String helpCorte = String.valueOf(editTextCorte.getText());
                    String helpVenciciemto = String.valueOf(editTextVencimiento.getText());


                        connection.insertTarjeta(helpBanco, helpMonto, helpFourDigits, helpInteres, helpCorte, helpVenciciemto);

                    Toast.makeText(getApplicationContext(), "Tarjeta Agregada",
                            Toast.LENGTH_LONG).show();

                    //ir al Menu Ingreso
                    startActivity(new Intent(AgregarTarjeta.this, MenuTarjetas.class));
                    finish();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
