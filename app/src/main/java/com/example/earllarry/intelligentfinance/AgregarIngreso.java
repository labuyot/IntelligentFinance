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
import android.widget.Toast;

public class AgregarIngreso extends AppCompatActivity implements View.OnClickListener {

    Button buttonCancelar;
    Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ingreso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DBConnection connection = new DBConnection(AgregarIngreso.this);

        final EditText editTextConcepto = (EditText)findViewById(R.id.editTextConceptoIngreso);
        final EditText editTextMonto = (EditText)findViewById(R.id.editTextMontoIngreso);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxIngreso);
        final EditText editTextFecha = (EditText)findViewById(R.id.editTextFechaIngreso);

        buttonCancelar = (Button)findViewById(R.id.buttonCancelarIngreso);
        buttonCancelar.setOnClickListener(this);
        buttonGuardar = (Button)findViewById(R.id.buttonGuardarIngreso);
        buttonGuardar.setOnClickListener(this);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgregarIngreso.this, MenuIngreso.class));
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

                    //Si automatizar esta activado inserta ingreso automatico
                    if(checkBox.isChecked()){

                        connection.insertIngreso(helpConcepto, helpMonto, true, helpFecha, "");

                    }//Si automatizar esta desactivado inserta ingreso
                    else{
                        connection.insertIngreso(helpConcepto, helpMonto, false, helpFecha, "");
                    }

                    Toast.makeText(getApplicationContext(), "Ingreso Agregado",
                            Toast.LENGTH_LONG).show();

                    //ir al Menu Ingreso
                    startActivity(new Intent(AgregarIngreso.this, MenuIngreso.class));
                    finish();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
