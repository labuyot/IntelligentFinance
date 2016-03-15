package com.example.earllarry.intelligentfinance;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class MenuTarjetas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tarjetas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white); // Set the icon

        // Icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cek", "home selected");
                startActivity(new Intent(MenuTarjetas.this, DashboardDrawer.class));
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuTarjetas.this, AgregarTarjeta.class));
                finish();
            }
        });

        init();
    }

    public void init() {

        final DBConnection connection = new DBConnection(MenuTarjetas.this);

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);

        int rowCount = connection.getCantidadDeFilas("Tarjeta");

        if(rowCount != 0){

            TableRow tbrowHead = new TableRow(this);
            tbrowHead.setBackgroundColor(Color.LTGRAY);

            TextView tv0 = new TextView(this);
            //tv0.setBackgroundResource(R.drawable.row_border);
            tv0.setTextSize(20);
            tv0.setText("  Banco  ");
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
            tv2.setText("  4 Digitos  ");
            tv2.setTextColor(Color.BLACK);
            tv2.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv2);

            TextView tv3 = new TextView(this);
            //tv3.setBackgroundResource(R.drawable.row_border);
            tv3.setTextSize(20);
            tv3.setText("  Interes  ");
            tv3.setTextColor(Color.BLACK);
            tv3.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv3);

            TextView tv4 = new TextView(this);
            //tv4.setBackgroundResource(R.drawable.row_border);
            tv4.setTextSize(20);
            tv4.setText("  Corte  ");
            tv4.setTextColor(Color.BLACK);
            tv4.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv4);

            TextView tv5 = new TextView(this);
            //tv5.setBackgroundResource(R.drawable.row_border);
            tv5.setTextSize(20);
            tv5.setText("  Vencimiento  ");
            tv5.setTextColor(Color.BLACK);
            tv5.setGravity(Gravity.CENTER);
            tbrowHead.addView(tv5);

            TextView tv6 = new TextView(this);
            //tv3.setBackgroundResource(R.drawable.row_border);
            tv6.setTextSize(20);
            tv6.setText("  Id  ");
            tv6.setTextColor(Color.BLACK);
            tv6.setGravity(Gravity.CENTER);
            tv6.setVisibility(View.GONE);
            tbrowHead.addView(tv6);

            stk.addView(tbrowHead);

        }

        //Carga todos los gastos de la base de datos
        List<Tarjeta> tarjetas = connection.getAllTarjetas();

        ArrayList<Integer> listaIds = new ArrayList<>();
        ArrayList<String> listaBancos = new ArrayList<>();
        ArrayList<Double> listaMontos = new ArrayList<>();
        ArrayList<Integer> listaFourDigits = new ArrayList<>();
        ArrayList<Double> listaIntereses = new ArrayList<>();
        ArrayList<String> listaFechaCorte = new ArrayList<>();
        ArrayList<String> listaFechaVencimiento = new ArrayList<>();

        //llena la lista con los Ids de las tarjetas
        for(int i = 0; i < tarjetas.size(); i++){
            Tarjeta tarjeta = tarjetas.get(i);
            listaIds.add(tarjeta.getId());
        }

        //llena la lista con los Bancos de las tarjetas
        for(int i = 0; i < tarjetas.size(); i++){
            Tarjeta tarjeta = tarjetas.get(i);
            listaBancos.add(tarjeta.getBanco());
        }

        //llena la lista con los montos de las tarjetas
        for(int i = 0; i < tarjetas.size(); i++){
            Tarjeta tarjeta = tarjetas.get(i);
            listaMontos.add(tarjeta.getMonto());
        }

        //llena la lista con los 4digitos de las tarjetas
        for(int i = 0; i < tarjetas.size(); i++){
            Tarjeta tarjeta = tarjetas.get(i);
            listaFourDigits.add(tarjeta.getFourdigits());
        }

        //llena la lista con los intereses de las tarjetas
        for(int i = 0; i < tarjetas.size(); i++){
            Tarjeta tarjeta = tarjetas.get(i);
            listaIntereses.add(tarjeta.getInteres());
        }

        //llena la lista con las fechas de corte de las tarjetas
        for(int i = 0; i < tarjetas.size(); i++){
            Tarjeta tarjeta = tarjetas.get(i);
            listaFechaCorte.add(tarjeta.getCorte());
        }

        //llena la lista con las fechas de vencimiento de las tarjetas
        for(int i = 0; i < tarjetas.size(); i++){
            Tarjeta tarjeta = tarjetas.get(i);
            listaFechaVencimiento.add(tarjeta.getVencimiento());
        }

        for (int i = 0; i < rowCount; i++) {

            TableRow tbrow = new TableRow(this);
            tbrow.setClickable(true);
            tbrow.setBackgroundColor(Color.WHITE);


            TextView banco = new TextView(this);
            //concepto.setBackgroundResource(R.drawable.row_border);
            banco.setTextSize(18);
            banco.setText(" " + listaBancos.get(i) + " ");
            banco.setTextColor(Color.BLACK);
            banco.setGravity(Gravity.CENTER);
            tbrow.addView(banco);

            TextView monto = new TextView(this);
            //monto.setBackgroundResource(R.drawable.row_border);
            monto.setTextSize(18);
            monto.setText(" " + listaMontos.get(i) + " ");
            monto.setTextColor(Color.BLACK);
            monto.setGravity(Gravity.CENTER);
            tbrow.addView(monto);

            TextView fourDigits = new TextView(this);
            //tipo.setBackgroundResource(R.drawable.row_border);
            fourDigits.setTextSize(18);
            fourDigits.setText(" " + listaFourDigits.get(i) + " ");
            fourDigits.setTextColor(Color.BLACK);
            fourDigits.setGravity(Gravity.CENTER);
            tbrow.addView(fourDigits);

            TextView intereses = new TextView(this);
            //fecha.setBackgroundResource(R.drawable.row_border);
            intereses.setTextSize(18);
            intereses.setText(" " + listaIntereses.get(i) + " ");
            intereses.setTextColor(Color.BLACK);
            intereses.setGravity(Gravity.CENTER);
            tbrow.addView(intereses);

            TextView fechaCorte = new TextView(this);
            //fecha.setBackgroundResource(R.drawable.row_border);
            fechaCorte.setTextSize(18);
            fechaCorte.setText(" " + listaFechaCorte.get(i) + " ");
            fechaCorte.setTextColor(Color.BLACK);
            fechaCorte.setGravity(Gravity.CENTER);
            tbrow.addView(fechaCorte);

            TextView fechaVencimiento = new TextView(this);
            //fecha.setBackgroundResource(R.drawable.row_border);
            fechaVencimiento.setTextSize(18);
            fechaVencimiento.setText(" " + listaFechaVencimiento.get(i) + " ");
            fechaVencimiento.setTextColor(Color.BLACK);
            fechaVencimiento.setGravity(Gravity.CENTER);
            tbrow.addView(fechaVencimiento);

            TextView id = new TextView(this);
            //concepto.setBackgroundResource(R.drawable.row_border);
            id.setTextSize(18);
            id.setText(" " + listaIds.get(i) + " ");
            id.setTextColor(Color.BLACK);
            id.setGravity(Gravity.CENTER);
            id.setVisibility(View.GONE);
            tbrow.addView(id);

            tbrow.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View v) {

                    v.setBackgroundColor(Color.GRAY);

                    //get the data you need
                    TableRow tablerow = (TableRow) v;
                    TextView sampleBanco = (TextView) tablerow.getChildAt(0);
                    TextView sampleMonto = (TextView) tablerow.getChildAt(1);
                    TextView samplefourDigits = (TextView) tablerow.getChildAt(2);
                    TextView sampleInteres = (TextView) tablerow.getChildAt(3);
                    TextView sampleFechaCorte = (TextView) tablerow.getChildAt(4);
                    TextView sampleFechaVencimiento = (TextView) tablerow.getChildAt(5);
                    TextView sampleId = (TextView) tablerow.getChildAt(6);

                    final String intentBanco = sampleBanco.getText().toString().replaceAll("\\s+","");
                    final String intentMonto = sampleMonto.getText().toString().replaceAll("\\s+", "");
                    final int intentfourDigits = Integer.parseInt(samplefourDigits.getText().toString().replaceAll("\\s+", ""));
                    final String intentInteres = sampleInteres.getText().toString().replaceAll("\\s+", "");
                    final String intentFechaCorte = sampleFechaCorte.getText().toString().replaceAll("\\s+", "");
                    final String intentFechaVencimiento = sampleFechaVencimiento.getText().toString().replaceAll("\\s+","");
                    final String intentId = sampleId.getText().toString().replaceAll("\\s+","");

                    AlertDialog.Builder builder = new AlertDialog.Builder(MenuTarjetas.this);
                    builder.setMessage("Que desea hacer?")
                            .setCancelable(false)
                            .setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent i = new Intent(getApplicationContext(), ModificarTarjeta.class);
                                    i.putExtra("banco", intentBanco);
                                    i.putExtra("monto", intentMonto);
                                    i.putExtra("fourDigits", intentfourDigits);
                                    i.putExtra("interes", intentInteres);
                                    i.putExtra("fechaCorte", intentFechaCorte);
                                    i.putExtra("fechaVencimiento", intentFechaVencimiento);
                                    i.putExtra("id", intentId);

                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    int dataId = Integer.parseInt(intentId);
                                    connection.deleteData("Tarjeta", dataId);

                                    Intent i = new Intent(MenuTarjetas.this, MenuTarjetas.class);
                                    startActivity(i);
                                    finish();
                                    //dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                    //startActivity(new Intent(MenuTarjetas.this, DashboardDrawer.class));
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
