package com.example.earllarry.intelligentfinance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarGasto extends AppCompatActivity implements View.OnClickListener {

    Button buttonCancelar;
    Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DBConnection connection = new DBConnection(AgregarGasto.this);

        final EditText editTextConcepto = (EditText)findViewById(R.id.editTextConceptoGasto);
        final EditText editTextMonto = (EditText)findViewById(R.id.editTextMontoGasto);
        final Spinner spinnerTipo = (Spinner)findViewById(R.id.spinnerTipoGasto);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxGasto);
        final EditText editTextFecha = (EditText)findViewById(R.id.editTextFechaGasto);

        buttonCancelar = (Button)findViewById(R.id.buttonCancelarGasto);
        buttonCancelar.setOnClickListener(this);
        buttonGuardar = (Button)findViewById(R.id.buttonGuardarGasto);
        buttonGuardar.setOnClickListener(this);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgregarGasto.this, MenuGasto.class));
                finish();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextConcepto.getText().toString().isEmpty() || editTextMonto.getText().toString().isEmpty() ||
                        editTextFecha.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Llenar campos",
                            Toast.LENGTH_LONG).show();
                } else {

                    String helpConcepto = String.valueOf(editTextConcepto.getText());
                    double helpMonto = Double.valueOf(editTextMonto.getText().toString());
                    String helpFecha = String.valueOf(editTextFecha.getText());
                    String helpFrecuencia = "Quincenal";

                    //Si automatizar esta activado inserta ingreso automatico
                    if(checkBox.isChecked()){

                        connection.insertGasto(helpConcepto, helpMonto, "", true, helpFecha, helpFrecuencia);
                    }//Si automatizar esta desactivado inserta ingreso
                    else{
                        connection.insertGasto(helpConcepto, helpMonto, "", false, helpFecha, helpFrecuencia);
                    }

                    Toast.makeText(getApplicationContext(), "Gasto Agregado",
                            Toast.LENGTH_LONG).show();

                    //ir al Menu Ingreso
                    startActivity(new Intent(AgregarGasto.this, MenuGasto.class));
                    finish();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
