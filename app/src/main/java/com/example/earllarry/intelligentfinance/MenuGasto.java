package com.example.earllarry.intelligentfinance;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuGasto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gasto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white); // Set the icon

        // Icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cek", "home selected");
                startActivity(new Intent(MenuGasto.this, DashboardDrawer.class));
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuGasto.this, AgregarGasto.class));
                finish();
            }
        });

        init();
    }

    public void init() {

        final DBConnection connection = new DBConnection(MenuGasto.this);

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);

        int rowCount = connection.getCantidadDeFilas("Gasto");

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
            tv1.setText("  Monto  ");
            tv1.setTextColor(Color.BLACK);
            tv1.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv1);

            TextView tv2 = new TextView(this);
            //tv2.setBackgroundResource(R.drawable.row_border);
            tv2.setTextSize(20);
            tv2.setText("  Tipo  ");
            tv2.setTextColor(Color.BLACK);
            tv2.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv2);

            TextView tv3 = new TextView(this);
            //tv3.setBackgroundResource(R.drawable.row_border);
            tv3.setTextSize(20);
            tv3.setText("  Automatizar  ");
            tv3.setTextColor(Color.BLACK);
            tv3.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv3);

            TextView tv4 = new TextView(this);
            //tv4.setBackgroundResource(R.drawable.row_border);
            tv4.setTextSize(20);
            tv4.setText("  Fecha  ");
            tv4.setTextColor(Color.BLACK);
            tv4.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv4);

            TextView tv5 = new TextView(this);
            //tv3.setBackgroundResource(R.drawable.row_border);
            tv5.setTextSize(20);
            tv5.setText("  Id  ");
            tv5.setTextColor(Color.BLACK);
            tv5.setGravity(Gravity.CENTER);
            tv5.setVisibility(View.GONE);
            tbrowHead.addView(tv5);

            stk.addView(tbrowHead);

        }

        //Carga todos los gastos de la base de datos
        List<Gasto> gastos = connection.getAllGastos();

        ArrayList<Integer> listaIds = new ArrayList<>();
        ArrayList<String> listaConceptos = new ArrayList<>();
        ArrayList<Double> listaMontos = new ArrayList<>();
        ArrayList<String> listaTipos = new ArrayList<>();
        ArrayList<Integer> listaAutomatizar = new ArrayList<>();
        ArrayList<String> listaFecha = new ArrayList<>();

        //llena la lista con los ids de los gastos
        for(int i = 0; i < gastos.size(); i++){
            Gasto gasto = gastos.get(i);
            listaIds.add(gasto.getId());
        }

        //llena la lista con los conceptos de los gastos
        for(int i = 0; i < gastos.size(); i++){
            Gasto gasto = gastos.get(i);
            listaConceptos.add(gasto.getConcepto());
        }

        //llena la lista con los montos de los gastos
        for(int i = 0; i < gastos.size(); i++){
            Gasto gasto = gastos.get(i);
            listaMontos.add(gasto.getMonto());
        }

        //llena la lista con los tipos de los gastos
        for(int i = 0; i < gastos.size(); i++){
            Gasto gasto = gastos.get(i);
            listaTipos.add(gasto.getTipo());
        }

        //llena la lista con los automatizar de los gastos
        for(int i = 0; i < gastos.size(); i++){
            Gasto gasto = gastos.get(i);
            listaAutomatizar.add(gasto.getAutomatizar());
        }

        //llena la lista con las fechas de los gastos
        for(int i = 0; i < gastos.size(); i++){
            Gasto gasto = gastos.get(i);
            listaFecha.add(gasto.getFecha());
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

            TextView tipo = new TextView(this);
            //tipo.setBackgroundResource(R.drawable.row_border);
            tipo.setTextSize(18);
            tipo.setText(" " + listaTipos.get(i) + " ");
            tipo.setTextColor(Color.BLACK);
            tipo.setGravity(Gravity.CENTER);
            tbrow.addView(tipo);

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

            TextView id = new TextView(this);
            //concepto.setBackgroundResource(R.drawable.row_border);
            id.setTextSize(18);
            id.setText(" " + listaIds.get(i) + " ");
            id.setTextColor(Color.BLACK);
            id.setGravity(Gravity.CENTER);
            id.setVisibility(View.GONE);
            tbrow.addView(id);

            tbrow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //v.setBackgroundColor(Color.GRAY);

                    //get the data you need
                    TableRow tablerow = (TableRow) v;
                    TextView sampleConcepto = (TextView) tablerow.getChildAt(0);
                    TextView sampleMonto = (TextView) tablerow.getChildAt(1);
                    TextView sampleTipo = (TextView) tablerow.getChildAt(2);
                    TextView sampleAutomatizar = (TextView) tablerow.getChildAt(3);
                    TextView sampleFecha = (TextView) tablerow.getChildAt(4);
                    TextView sampleId = (TextView) tablerow.getChildAt(5);
                    final String intentConcepto = sampleConcepto.getText().toString().replaceAll("\\s+", "");
                    final String intentMonto = sampleMonto.getText().toString().replaceAll("\\s+", "");
                    final String intentTipo = sampleTipo.getText().toString().replaceAll("\\s+", "");
                    final String intentAutomatizar = sampleAutomatizar.getText().toString().replaceAll("\\s+", "");
                    final String intentFecha = sampleFecha.getText().toString().replaceAll("\\s+", "");
                    final String intentId = sampleId.getText().toString().replaceAll("\\s+", "");

                    AlertDialog.Builder builder = new AlertDialog.Builder(MenuGasto.this);
                    builder.setMessage("Que desea hacer?")
                            .setCancelable(false)
                            .setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent i = new Intent(getApplicationContext(), ModificarGasto.class);
                                    i.putExtra("concepto", intentConcepto);
                                    i.putExtra("monto", intentMonto);
                                    i.putExtra("fecha", intentFecha);
                                    i.putExtra("id", intentId);
                                    if (Objects.equals("Si", intentAutomatizar)) {
                                        i.putExtra("automatizar", true);
                                    } else if (Objects.equals("No", intentAutomatizar)) {
                                        i.putExtra("automatizar", false);
                                    }
                                    startActivity(i);
                                }
                            })
                            .setNeutralButton("Eliminar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    int dataId = Integer.parseInt(intentId);
                                    connection.deleteData("Gasto", dataId);

                                    if (intentTipo.equals("Tarjeta")) {
                                        connection.deleteDataGastoTarjetaConcepto(intentConcepto);
                                    }

                                    Intent i = new Intent(MenuGasto.this, MenuGasto.class);
                                    startActivity(i);
                                    finish();
                                    //dialog.cancel();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                    //startActivity(new Intent(MenuGasto.this, DashboardDrawer.class));
                    //finish();
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
