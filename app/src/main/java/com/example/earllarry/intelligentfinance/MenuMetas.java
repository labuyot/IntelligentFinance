package com.example.earllarry.intelligentfinance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuMetas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_metas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white); // Set the icon

        // Icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cek", "home selected");
                startActivity(new Intent(MenuMetas.this, DashboardDrawer.class));
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuMetas.this, AgregarMeta.class));
                finish();
            }
        });

        init();
    }

    public void init() {

        final DBConnection connection = new DBConnection(MenuMetas.this);

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);

        int rowCount = connection.getCantidadDeFilas("Meta");

        if(rowCount != 0){

            TableRow tbrowHead = new TableRow(this);
            tbrowHead.setBackgroundColor(Color.LTGRAY);

            TextView tv0 = new TextView(this);
            //tv0.setBackgroundResource(R.drawable.row_border);
            tv0.setTextSize(20);
            tv0.setText(" Concepto ");
            tv0.setTextColor(Color.BLACK);
            tv0.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv0);

            TextView tv1 = new TextView(this);
            //tv1.setBackgroundResource(R.drawable.row_border);
            tv1.setTextSize(20);
            tv1.setText(" Monto ");
            tv1.setTextColor(Color.BLACK);
            tv1.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv1);

            TextView tv2 = new TextView(this);
            //tv2.setBackgroundResource(R.drawable.row_border);
            tv2.setTextSize(20);
            tv2.setText(" Fecha Inicio ");
            tv2.setTextColor(Color.BLACK);
            tv2.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv2);

            TextView tv3 = new TextView(this);
            //tv3.setBackgroundResource(R.drawable.row_border);
            tv3.setTextSize(20);
            tv3.setText(" Fecha Final ");
            tv3.setTextColor(Color.BLACK);
            tv3.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv3);

            stk.addView(tbrowHead);

        }

        //Carga todos los gastos de la base de datos
        List<Meta> metas = connection.getAllMetas();

        ArrayList<String> listaConceptos = new ArrayList<>();
        ArrayList<Double> listaMontos = new ArrayList<>();
        ArrayList<String> listaFechaInicio = new ArrayList<>();
        ArrayList<String> listaFechaFinal = new ArrayList<>();

        //llena la lista con los conceptos de los juegos
        for(int i = 0; i < metas.size(); i++){
            Meta meta = metas.get(i);
            listaConceptos.add(meta.getConcepto());
        }

        //llena la lista con los montos de los juegos
        for(int i = 0; i < metas.size(); i++){
            Meta meta = metas.get(i);
            listaMontos.add(meta.getMonto());
        }

        //llena la lista con los automatizar de los juegos
        for(int i = 0; i < metas.size(); i++){
            Meta meta = metas.get(i);
            listaFechaInicio.add(meta.getFechaInicio());
        }

        //llena la lista con las fechas de los juegos
        for(int i = 0; i < metas.size(); i++){
            Meta meta = metas.get(i);
            listaFechaFinal.add(meta.getFechaFinal());
        }

        for (int i = 0; i < rowCount; i++) {

            TableRow tbrow = new TableRow(this);
            tbrow.setClickable(true);
            tbrow.setBackgroundColor(Color.WHITE);


            TextView concepto = new TextView(this);
            //concepto.setBackgroundResource(R.drawable.row_border);
            concepto.setTextSize(22);
            concepto.setText("" + listaConceptos.get(i));
            concepto.setTextColor(Color.BLACK);
            concepto.setGravity(Gravity.CENTER);
            tbrow.addView(concepto);

            TextView monto = new TextView(this);
            //monto.setBackgroundResource(R.drawable.row_border);
            monto.setTextSize(22);
            monto.setText("" + listaMontos.get(i));
            monto.setTextColor(Color.BLACK);
            monto.setGravity(Gravity.CENTER);
            tbrow.addView(monto);

            TextView fechaInicial = new TextView(this);
            //tipo.setBackgroundResource(R.drawable.row_border);
            fechaInicial.setTextSize(22);
            fechaInicial.setText("" + listaFechaInicio.get(i));
            fechaInicial.setTextColor(Color.BLACK);
            fechaInicial.setGravity(Gravity.CENTER);
            tbrow.addView(fechaInicial);

            TextView fechaFinal = new TextView(this);
            //fecha.setBackgroundResource(R.drawable.row_border);
            fechaFinal.setTextSize(22);
            fechaFinal.setText("" + listaFechaFinal.get(i));
            fechaFinal.setTextColor(Color.BLACK);
            fechaFinal.setGravity(Gravity.CENTER);
            tbrow.addView(fechaFinal);

            tbrow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    /*
                    v.setBackgroundColor(Color.GRAY);
                    System.out.println("Row clicked: " + v.getId());

                    //get the data you need
                    TableRow tablerow = (TableRow) v.getParent();
                    TextView sample = (TextView) tablerow.getChildAt(2);
                    String result = sample.getText().toString();

                    */

                    startActivity(new Intent(MenuMetas.this, DashboardDrawer.class));
                    finish();
                }
            });

            stk.addView(tbrow);
        }

        if(rowCount != 0){

            TableRow tbrowBot = new TableRow(this);
            tbrowBot.setBackgroundColor(Color.parseColor("#FFFFE0"));

            TextView tv5 = new TextView(this);
            tv5.setHeight(200);
            tv5.setTextSize(20);
            tv5.setText(" ");
            tv5.setTextColor(Color.BLACK);
            tv5.setGravity(Gravity.CENTER);
            tbrowBot.addView(tv5);
            stk.addView(tbrowBot);

        }

    }

}
