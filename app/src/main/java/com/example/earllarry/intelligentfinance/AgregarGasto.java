package com.example.earllarry.intelligentfinance;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregarGasto extends AppCompatActivity implements View.OnClickListener {

    int mDay, mMonth, mYear;

    private SimpleDateFormat dateFormatter;

    Button buttonCancelar;
    Button buttonGuardar;

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN = "^([1-9]|0[1-9]|[12][0-9]|3[01])[- /.]([1-9]|0[1-9]|1[012])[- /.]([0-9]{4})?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DBConnection connection = new DBConnection(AgregarGasto.this);

        final EditText editTextConcepto = (EditText)findViewById(R.id.editTextConceptoGasto);
        final EditText editTextMonto = (EditText)findViewById(R.id.editTextMontoGasto);
        final Spinner spinnerTarjeta = (Spinner)findViewById(R.id.spinnerTarjetaGasto);
        final Spinner spinnerRecurrencia = (Spinner)findViewById(R.id.spinner2);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxGasto);
        final CheckBox checkBoxTarjeta = (CheckBox) findViewById(R.id.checkboxTarjeta);
        final EditText editTextFecha = (EditText)findViewById(R.id.editTextFechaGasto);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        spinnerRecurrencia.setVisibility(View.GONE);
        spinnerTarjeta.setVisibility(View.GONE);

        buttonCancelar = (Button)findViewById(R.id.buttonCancelarGasto);
        buttonCancelar.setOnClickListener(this);
        buttonGuardar = (Button)findViewById(R.id.buttonGuardarGasto);
        buttonGuardar.setOnClickListener(this);

        editTextFecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                mYear=mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(AgregarGasto.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        editTextFecha.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spinnerRecurrencia.setVisibility(View.VISIBLE);
                } else {
                    spinnerRecurrencia.setVisibility(View.GONE);
                }
            }
        });

        checkBoxTarjeta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(connection.getCantidadDeFilas("Tarjeta") > 0){
                        spinnerTarjeta.setVisibility(View.VISIBLE);

                        List<Tarjeta> tarjetas = connection.getAllTarjetas();
                        ArrayList<Integer> listaFourDigits = new ArrayList<>();

                        //llena la lista con los 4digitos de las tarjetas
                        for(int i = 0; i < tarjetas.size(); i++){
                            Tarjeta tarjeta = tarjetas.get(i);
                            listaFourDigits.add(tarjeta.getFourdigits());
                        }

                        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(AgregarGasto.this, android.R.layout.simple_spinner_dropdown_item, listaFourDigits);

                        spinnerTarjeta.setAdapter(adapter);

                    }else{

                        checkBoxTarjeta.setChecked(false);
                        Toast.makeText(getApplicationContext(), "No ha registrado tarjetas",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    spinnerTarjeta.setVisibility(View.GONE);
                }
            }
        });

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

                //variables para agregar tarjeta
                double balanceTarjeta = 0;
                double helpBalanceTarjeta = 0;
                double helpTarjetaConsumo = 0;
                double helpTarjetaConsumoUpdate = 0;

                //variables para agregar efectivo
                double helpIngreso = 0;
                double helpBalance = 0;
                double helpGastoEfectivo = 0;

                String helpFecha = editTextFecha.getText().toString();

                if (editTextConcepto.getText().toString().isEmpty() || editTextMonto.getText().toString().isEmpty() ||
                        editTextFecha.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Llenar campos",
                            Toast.LENGTH_LONG).show();
                }else if(!validate(helpFecha)) {

                    Toast.makeText(getApplicationContext(), "Fecha incorrecta",
                            Toast.LENGTH_LONG).show();

                }else  if(validate(helpFecha)){

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date myDate;

                    String helpConcepto = String.valueOf(editTextConcepto.getText());
                    final double helpMonto = Double.valueOf(editTextMonto.getText().toString());

                    helpConcepto.replaceAll("\\s+", "");
                    String helpConceptoLower = helpConcepto.toLowerCase();

                    final String helpConcepto1 = helpConceptoLower.substring(0, 1).toUpperCase() + helpConceptoLower.substring(1);

                    String myText = "";

                    try {
                        myDate = df.parse(editTextFecha.getText().toString());
                        myText = myDate.getDate() + "-" + (myDate.getMonth() + 1) + "-" + (1900 + myDate.getYear());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(connection.conceptoExist(helpConcepto1, "Gasto", "Concepto")){

                        Toast.makeText(getApplicationContext(), "Concepto ya existe",
                                Toast.LENGTH_LONG).show();

                    }else {

                        final ContentValues data=new ContentValues();

                        if(checkBoxTarjeta.isChecked()){

                            final int tarjeta = (int) spinnerTarjeta.getSelectedItem();

                            helpTarjetaConsumo = connection.getTarjetaConsumo(tarjeta);

                            helpTarjetaConsumoUpdate = helpTarjetaConsumo + helpMonto;

                            data.put("Consumo", helpTarjetaConsumoUpdate);

                            //Si automatizar esta activado inserta ingreso automatico
                            if(checkBox.isChecked()){

                                String textRecurrencia = spinnerRecurrencia.getSelectedItem().toString();

                                connection.insertGasto(helpConcepto1, helpMonto, "Tarjeta", true, myText, textRecurrencia);
                            }//Si automatizar esta desactivado inserta ingreso
                            else{

                                connection.insertGasto(helpConcepto1, helpMonto, "Tarjeta", false, myText, "No");
                            }

                            balanceTarjeta = connection.getTarjetaMonto(tarjeta);
                            helpBalanceTarjeta = balanceTarjeta - helpTarjetaConsumoUpdate;

                            if(helpBalanceTarjeta <= 0){

                                AlertDialog.Builder builder = new AlertDialog.Builder(AgregarGasto.this);
                                builder.setMessage("Ha sobrepasado el límite de su tarjeta")
                                        .setCancelable(false)
                                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                connection.insertTarjetaGasto(tarjeta, helpMonto, helpConcepto1);
                                                connection.updateTarjetaConsumo(tarjeta, data);

                                                Toast.makeText(getApplicationContext(), "Gasto Agregado",
                                                        Toast.LENGTH_LONG).show();

                                                startActivity(new Intent(AgregarGasto.this, MenuGasto.class));
                                                finish();
                                            }
                                        })
                                        .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                connection.deleteDataGasto("Gasto", helpConcepto1);

                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                            }else {
                                connection.insertTarjetaGasto(tarjeta, helpMonto, helpConcepto1);
                                connection.updateTarjetaConsumo(tarjeta, data);

                                Toast.makeText(getApplicationContext(), "Gasto Agregado",
                                        Toast.LENGTH_LONG).show();

                                //ir al Menu Ingreso
                                startActivity(new Intent(AgregarGasto.this, MenuGasto.class));
                                finish();
                            }

                        }else {
                            //Si automatizar esta activado inserta ingreso automatico
                            if(checkBox.isChecked()){

                                String textRecurrencia = spinnerRecurrencia.getSelectedItem().toString();

                                connection.insertGasto(helpConcepto1, helpMonto, "Efectivo", true, helpFecha, textRecurrencia);
                            }//Si automatizar esta desactivado inserta ingreso
                            else{
                                connection.insertGasto(helpConcepto1, helpMonto, "Efectivo", false, helpFecha, "No");
                            }

                            helpIngreso = connection.getTotal("Monto","Ingreso");
                            helpGastoEfectivo = connection.getTotalEfectivo("Monto","Gasto");
                            helpBalance = helpIngreso - helpGastoEfectivo;

                            if(helpBalance <= 0){

                                AlertDialog.Builder builder = new AlertDialog.Builder(AgregarGasto.this);
                                builder.setMessage("Su ha agotado su balance")
                                        .setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                Toast.makeText(getApplicationContext(), "Gasto Agregado",
                                                        Toast.LENGTH_LONG).show();

                                                startActivity(new Intent(AgregarGasto.this, MenuGasto.class));
                                                finish();
                                            }
                                        })
                                        .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                connection.deleteDataGasto("Gasto", helpConcepto1);

                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                                    alert.show();
                            }else {

                                Toast.makeText(getApplicationContext(), "Gasto Agregado",
                                        Toast.LENGTH_LONG).show();

                                //ir al Menu Ingreso
                                startActivity(new Intent(AgregarGasto.this, MenuGasto.class));
                                finish();
                            }
                        }
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
