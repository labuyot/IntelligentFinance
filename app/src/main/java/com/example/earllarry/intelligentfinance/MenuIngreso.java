package com.example.earllarry.intelligentfinance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuIngreso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ingreso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white); // Set the icon

        // Icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cek", "home selected");
                startActivity(new Intent(MenuIngreso.this, DashboardDrawer.class));
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuIngreso.this, AgregarIngreso.class));
                finish();
            }
        });

        init();
    }

    public void init() {

        final DBConnection connection = new DBConnection(MenuIngreso.this);

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);

        int rowCount = connection.getCantidadDeFilas("Ingreso");

        if(rowCount != 0){

            TableRow tbrowHead = new TableRow(this);
            tbrowHead.setBackgroundColor(Color.LTGRAY);

            TextView tv0 = new TextView(this);
            //tv0.setBackgroundResource(R.drawable.row_border);
            tv0.setTextSize(20);
            tv0.setText("  Concepto  ");
            tv0.setTextColor(Color.BLACK);
            tv0.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv0);

            TextView tv1 = new TextView(this);
            //tv1.setBackgroundResource(R.drawable.row_border);
            tv1.setTextSize(20);
            tv1.setText("  Monto  ");
            tv1.setTextColor(Color.BLACK);
            tv1.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv1);

            TextView tv2 = new TextView(this);
            //tv2.setBackgroundResource(R.drawable.row_border);
            tv2.setTextSize(20);
            tv2.setText("  Automatizar  ");
            tv2.setTextColor(Color.BLACK);
            tv2.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv2);

            TextView tv3 = new TextView(this);
            //tv3.setBackgroundResource(R.drawable.row_border);
            tv3.setTextSize(20);
            tv3.setText("  Fecha  ");
            tv3.setTextColor(Color.BLACK);
            tv3.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv3);

            stk.addView(tbrowHead);

        }

        //Carga todos los ingresos de la base de datos
        List<Ingreso> ingresos = connection.getAllIngresos();

        ArrayList<String> listaConceptos = new ArrayList<>();
        ArrayList<Double> listaMontos = new ArrayList<>();
        ArrayList<Integer> listaAutomatizar = new ArrayList<>();
        ArrayList<String> listaFecha = new ArrayList<>();

        //llena la lista con los conceptos de los juegos
        for(int i = 0; i < ingresos.size(); i++){
            Ingreso ingreso = ingresos.get(i);
            listaConceptos.add(ingreso.getConcepto());
        }

        //llena la lista con los conceptos de los juegos
        for(int i = 0; i < ingresos.size(); i++){
            Ingreso ingreso = ingresos.get(i);
            listaMontos.add(ingreso.getMonto());
        }

        //llena la lista con los conceptos de los juegos
        for(int i = 0; i < ingresos.size(); i++){
            Ingreso ingreso = ingresos.get(i);
            listaAutomatizar.add(ingreso.getAutomatizar());
        }

        //llena la lista con los conceptos de los juegos
        for(int i = 0; i < ingresos.size(); i++){
            Ingreso ingreso = ingresos.get(i);
            listaFecha.add(ingreso.getFecha());
        }

        for (int i = 0; i < rowCount; i++) {

            TableRow tbrow = new TableRow(this);
            tbrow.setClickable(true);
            tbrow.setBackgroundColor(Color.WHITE);

            TextView concepto = new TextView(this);
            //concepto.setBackgroundResource(R.drawable.row_border);
            concepto.setTextSize(18);
            concepto.setText(" " + listaConceptos.get(i) + " ");
            concepto.setTextColor(Color.BLACK);
            concepto.setGravity(Gravity.CENTER);
            tbrow.addView(concepto);

            TextView monto = new TextView(this);
            //monto.setBackgroundResource(R.drawable.row_border);
            monto.setTextSize(18);
            monto.setText(" " + listaMontos.get(i) + " ");
            monto.setTextColor(Color.BLACK);
            monto.setGravity(Gravity.CENTER);
            tbrow.addView(monto);

            TextView automatizar = new TextView(this);
            //automatizar.setBackgroundResource(R.drawable.row_border);
            automatizar.setTextSize(18);
            if(listaAutomatizar.get(i) == 0){
                automatizar.setText(" No ");
            }else {
                automatizar.setText(" Si ");
            }
            automatizar.setTextColor(Color.BLACK);
            automatizar.setGravity(Gravity.CENTER);
            tbrow.addView(automatizar);

            TextView fecha = new TextView(this);
            //fecha.setBackgroundResource(R.drawable.row_border);
            fecha.setTextSize(18);
            fecha.setText(" " + listaFecha.get(i) + " ");
            fecha.setTextColor(Color.BLACK);
            fecha.setGravity(Gravity.CENTER);
            tbrow.addView(fecha);

            tbrow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    v.setBackgroundColor(Color.GRAY);

                    //get the data you need
                    TableRow tablerow = (TableRow) v;
                    TextView sampleConcepto = (TextView) tablerow.getChildAt(0);
                    TextView sampleMonto = (TextView) tablerow.getChildAt(1);
                    TextView sampleFecha = (TextView) tablerow.getChildAt(3);
                    String intentConcepto = sampleConcepto.getText().toString().replaceAll("\\s+","");
                    String intentMonto = sampleMonto.getText().toString().replaceAll("\\s+", "");
                    String intentFecha = sampleFecha.getText().toString().replaceAll("\\s+","");

                    Intent i = new Intent(getApplicationContext(), ModificarIngreso.class);
                    i.putExtra("concepto", intentConcepto);
                    i.putExtra("monto", intentMonto);
                    i.putExtra("fecha", intentFecha);
                    startActivity(i);

                   // startActivity(new Intent(MenuIngreso.this, ModificarIngreso.class));
                   // finish();
                }
            });

            stk.addView(tbrow);
        }

        if(rowCount != 0){

            TableRow tbrowBot = new TableRow(this);
            tbrowBot.setBackgroundColor(Color.parseColor("#FFFFE0"));

            TextView tv5 = new TextView(this);
            tv5.setHeight(150);
            tv5.setTextSize(20);
            tv5.setText(" ");
            tv5.setTextColor(Color.BLACK);
            tv5.setGravity(Gravity.CENTER);
            tbrowBot.addView(tv5);
            stk.addView(tbrowBot);

        }

    }

}
