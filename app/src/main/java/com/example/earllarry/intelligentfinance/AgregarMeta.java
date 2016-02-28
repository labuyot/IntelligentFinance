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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregarMeta extends AppCompatActivity implements View.OnClickListener {

    Button buttonCancelar;
    Button buttonGuardar;

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.]([0-9]{4})?$";

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

                String helpFecha1 = editTextFechaInicio.getText().toString();
                String helpFecha2 = editTextFechaFinal.getText().toString();

                if (editTextConcepto.getText().toString().isEmpty() || editTextMonto.getText().toString().isEmpty() ||
                        editTextFechaInicio.getText().toString().isEmpty() || editTextFechaFinal.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Llenar campos",
                            Toast.LENGTH_LONG).show();

                }else if(!validate(helpFecha1) || !validate(helpFecha2)){

                    Toast.makeText(getApplicationContext(), "Fecha incorrecta",
                            Toast.LENGTH_LONG).show();

                } else if(validate(helpFecha1) && validate(helpFecha2)) {

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date myDate1;
                    Date myDate2;

                    String helpConcepto = String.valueOf(editTextConcepto.getText());
                    double helpMonto = Double.valueOf(editTextMonto.getText().toString());

                    String myText1 = "";
                    String myText2 = "";

                    try {
                        myDate1 = df.parse(editTextFechaInicio.getText().toString());
                        myText1 = myDate1.getDate() + "-" + (myDate1.getMonth() + 1) + "-" + (1900 + myDate1.getYear());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    try {
                        myDate2 = df.parse(editTextFechaFinal.getText().toString());
                        myText2 = myDate2.getDate() + "-" + (myDate2.getMonth() + 1) + "-" + (1900 + myDate2.getYear());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    connection.insertMeta(helpConcepto, helpMonto, myText1, myText2);

                    Toast.makeText(getApplicationContext(), "Meta Agregada",
                            Toast.LENGTH_LONG).show();

                    //ir al Menu Metas
                    startActivity(new Intent(AgregarMeta.this, MenuMetas.class));
                    finish();
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
