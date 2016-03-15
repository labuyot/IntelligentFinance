package com.example.earllarry.intelligentfinance;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModificarTarjeta extends AppCompatActivity implements View.OnClickListener {

    Button buttonCancelar;
    Button buttonGuardar;

    int tarjetaAModificar = 0;

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN = "^([1-9]|0[1-9]|[12][0-9]|3[01])[- /.]([1-9]|0[1-9]|1[012])[- /.]([0-9]{4})?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_tarjeta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DBConnection connection = new DBConnection(ModificarTarjeta.this);

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

        String ayudaBanco = "";
        String ayudaMonto = "";
        int ayudafourDigits = 0;
        String ayudaInteres = "";
        String ayudaCorte = "";
        String ayudaVencimiento = "";
        String ayudaId = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ayudaBanco = extras.getString("banco");
            ayudaMonto = extras.getString("monto");
            ayudafourDigits = extras.getInt("fourDigits");
            ayudaInteres = extras.getString("interes");
            ayudaCorte = extras.getString("fechaCorte");
            ayudaVencimiento = extras.getString("fechaVencimiento");
            ayudaId = extras.getString("id");
        }

        tarjetaAModificar = Integer.parseInt(ayudaId);

        editTextBanco.setText(ayudaBanco.replaceAll("\\s+", ""));
        editTextMonto.setText(ayudaMonto.replaceAll("\\s+",""));
        editTextfourDigits.setText(String.valueOf(ayudafourDigits));
        editTextInteres.setText(ayudaInteres.replaceAll("\\s+",""));
        editTextCorte.setText(ayudaCorte.replaceAll("\\s+",""));
        editTextVencimiento.setText(ayudaVencimiento.replaceAll("\\s+",""));

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModificarTarjeta.this, MenuTarjetas.class));
                finish();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String helpCorte = String.valueOf(editTextCorte.getText());
                String helpVenciciemto = String.valueOf(editTextVencimiento.getText());

                if (editTextBanco.getText().toString().isEmpty() || editTextMonto.getText().toString().isEmpty() ||
                        editTextfourDigits.getText().toString().isEmpty() || editTextInteres.getText().toString().isEmpty() ||
                        editTextCorte.getText().toString().isEmpty() || editTextVencimiento.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Llenar campos",
                            Toast.LENGTH_LONG).show();
                } else if(!validate(helpCorte) || !validate(helpVenciciemto)){

                    Toast.makeText(getApplicationContext(), "Fecha incorrecta",
                            Toast.LENGTH_LONG).show();

                } else if(validate(helpCorte) && validate(helpVenciciemto)){

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date myDate1;
                    Date myDate2;

                    String helpBanco = String.valueOf(editTextBanco.getText());
                    double helpMonto = Double.valueOf(editTextMonto.getText().toString());
                    int helpFourDigits = Integer.valueOf(editTextfourDigits.getText().toString());
                    double helpInteres = Double.valueOf(editTextInteres.getText().toString());

                    String myText1 = "";
                    String myText2 = "";

                    try {
                        myDate1 = df.parse(editTextCorte.getText().toString());
                        myText1 = myDate1.getDate() + "-" + (myDate1.getMonth() + 1) + "-" + (1900 + myDate1.getYear());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    try {
                        myDate2 = df.parse(editTextVencimiento.getText().toString());
                        myText2 = myDate2.getDate() + "-" + (myDate2.getMonth() + 1) + "-" + (1900 + myDate2.getYear());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    helpBanco.replaceAll("\\s+", "");
                    String helpBancoLower = helpBanco.toLowerCase();

                    String helpBanco1 = helpBancoLower.substring(0, 1).toUpperCase() + helpBancoLower.substring(1);

                    ContentValues data=new ContentValues();
                    data.put("Banco", helpBanco1);
                    data.put("Monto", helpMonto);
                    data.put("Cuatrodigitos", helpFourDigits);
                    data.put("Interes", helpInteres);
                    data.put("Corte", myText1);
                    data.put("Vencimiento", myText2);

                    //if(connection.fourDigitsExist(helpFourDigits, "Tarjeta", "Cuatrodigitos")){

                    //    Toast.makeText(getApplicationContext(), "Tarjeta ya existe",
                    //           Toast.LENGTH_LONG).show();

                    //}else {

                        connection.updateDataTarjeta(tarjetaAModificar, data);

                        Toast.makeText(getApplicationContext(), "Tarjeta Modificada",
                                Toast.LENGTH_LONG).show();

                        //ir al Menu Tarjeta
                        startActivity(new Intent(ModificarTarjeta.this, MenuTarjetas.class));
                        finish();

                    //}
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
