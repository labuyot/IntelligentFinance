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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregarIngreso extends AppCompatActivity implements View.OnClickListener {

    Button buttonCancelar;
    Button buttonGuardar;

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN = "^([1-9]|0[1-9]|[12][0-9]|3[01])[- /.]([1-9]|0[1-9]|1[012])[- /.]([0-9]{4})?$";

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

                String helpFecha = editTextFecha.getText().toString();

                if (editTextConcepto.getText().toString().isEmpty() || editTextMonto.getText().toString().isEmpty() ||
                        editTextFecha.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Llenar campos",
                            Toast.LENGTH_LONG).show();
                } else if(!validate(helpFecha)){

                    Toast.makeText(getApplicationContext(), "Fecha incorrecta",
                            Toast.LENGTH_LONG).show();

                } else if(validate(helpFecha)){

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date myDate;

                    String helpConcepto = String.valueOf(editTextConcepto.getText());
                    double helpMonto = Double.valueOf(editTextMonto.getText().toString());

                    helpConcepto.replaceAll("\\s+", "");
                    String helpConceptoLower = helpConcepto.toLowerCase();

                    String helpConcepto1 = helpConceptoLower.substring(0, 1).toUpperCase() + helpConceptoLower.substring(1);

                    String myText = "";

                    try {
                        myDate = df.parse(editTextFecha.getText().toString());
                        myText = myDate.getDate() + "-" + (myDate.getMonth() + 1) + "-" + (1900 + myDate.getYear());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(connection.conceptoExist(helpConcepto1, "Ingreso", "Concepto")){

                        Toast.makeText(getApplicationContext(), "Concepto ya existe",
                                Toast.LENGTH_LONG).show();

                    }else {

                        //Si automatizar esta activado inserta ingreso automatico
                        if(checkBox.isChecked()){

                            connection.insertIngreso(helpConcepto1, helpMonto, true, myText, "");

                        }//Si automatizar esta desactivado inserta ingreso
                        else{
                            connection.insertIngreso(helpConcepto1, helpMonto, false, myText, "");
                        }

                        Toast.makeText(getApplicationContext(), "Ingreso Agregado",
                                Toast.LENGTH_LONG).show();

                        //ir al Menu Ingreso
                        startActivity(new Intent(AgregarIngreso.this, MenuIngreso.class));
                        finish();

                    }
                }
            }
        });

    }

    public boolean validate(String date){

        matcher = pattern.compile(DATE_PATTERN).matcher(date);

        if(matcher.matches()){
            matcher.reset();

            if(matcher.find()){
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                System.out.print(day);
                System.out.print(month);
                System.out.print(year);

                if (day.equals("31") &&
                        (month.equals("4") || month .equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                }

                else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }

                else{
                    return true;
                }
            }

            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
