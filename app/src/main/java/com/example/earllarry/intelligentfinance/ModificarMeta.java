package com.example.earllarry.intelligentfinance;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModificarMeta extends AppCompatActivity implements View.OnClickListener {

    int mDay, mMonth, mYear;
    int fDay, fMonth, fYear;

    private SimpleDateFormat dateFormatter;

    Button buttonCancelar;
    Button buttonGuardar;

    int conceptoAModificar = 0;

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN = "^([1-9]|0[1-9]|[12][0-9]|3[01])[- /.]([1-9]|0[1-9]|1[012])[- /.]([0-9]{4})?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_meta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DBConnection connection = new DBConnection(ModificarMeta.this);

        final EditText editTextConcepto = (EditText)findViewById(R.id.editTextConceptoMeta);
        final EditText editTextMonto = (EditText)findViewById(R.id.editTextMontoMeta);
        final EditText editTextFechaInicio = (EditText)findViewById(R.id.editTextFechaInicioMeta);
        final EditText editTextFechaFinal = (EditText)findViewById(R.id.editTextFechaFinalMeta);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        buttonCancelar = (Button)findViewById(R.id.buttonCancelarMeta);
        buttonCancelar.setOnClickListener(this);
        buttonGuardar = (Button)findViewById(R.id.buttonGuardarMeta);
        buttonGuardar.setOnClickListener(this);

        String ayudaConcepto = "";
        String ayudaMonto = "";
        String ayudaFechaInicio = "";
        String ayudaFechaFinal = "";
        String ayudaId = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ayudaConcepto = extras.getString("concepto");
            ayudaMonto = extras.getString("monto");
            ayudaFechaInicio = extras.getString("fechaInicio");
            ayudaFechaFinal = extras.getString("fechaFinal");
            ayudaId = extras.getString("id");
        }

        conceptoAModificar = Integer.parseInt(ayudaId);

        editTextConcepto.setText(ayudaConcepto.replaceAll("\\s+", ""));
        editTextMonto.setText(ayudaMonto.replaceAll("\\s+",""));
        editTextFechaInicio.setText(ayudaFechaInicio.replaceAll("\\s+",""));
        editTextFechaFinal.setText(ayudaFechaFinal.replaceAll("\\s+", ""));

        editTextFechaInicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(ModificarMeta.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        editTextFechaInicio.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        editTextFechaFinal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                fYear = mcurrentDate.get(Calendar.YEAR);
                fMonth = mcurrentDate.get(Calendar.MONTH);
                fDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(ModificarMeta.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selectedyear, selectedmonth, selectedday);
                        editTextFechaFinal.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, fYear, fMonth, fDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModificarMeta.this, MenuMetas.class));
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

                    SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
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

                    helpConcepto.replaceAll("\\s+", "");
                    String helpConceptoLower = helpConcepto.toLowerCase();

                    String helpConcepto1 = helpConceptoLower.substring(0, 1).toUpperCase() + helpConceptoLower.substring(1);

                    ContentValues data=new ContentValues();
                    data.put("Concepto", helpConcepto1);
                    data.put("Monto", helpMonto);
                    data.put("FechaInicio", helpFecha1);
                    data.put("FechaFinal", helpFecha2);

                    //if(connection.conceptoExist(helpConcepto1, "Meta", "Concepto")){

                    //    Toast.makeText(getApplicationContext(), "Concepto ya existe",
                    //            Toast.LENGTH_LONG).show();

                    //}else {

                    connection.updateDataMeta(conceptoAModificar, data);

                        Toast.makeText(getApplicationContext(), "Meta Modificada",
                                Toast.LENGTH_LONG).show();

                        //ir al Menu Metas
                        startActivity(new Intent(ModificarMeta.this, MenuMetas.class));
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
