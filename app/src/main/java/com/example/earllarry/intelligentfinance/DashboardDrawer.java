package com.example.earllarry.intelligentfinance;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashboardDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textViewBienvenida;
    private TextView textViewBalance;
    private TextView textViewIngresos;
    private TextView textViewGastos;
    private TextView textViewTarjetas;
    private TextView textViewIngresoFijoDashboard;
    private TextView textViewGastoFijoDashboard;
    private TextView textViewTarjetaFijoDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewBienvenida = (TextView) findViewById(R.id.textViewBienvenida);
        textViewBalance = (TextView) findViewById(R.id.textViewBalanceDashboard);
        textViewIngresos = (TextView) findViewById(R.id.textViewIngresoDashboard);
        textViewGastos = (TextView) findViewById(R.id.textViewGastoDashboard);
        textViewTarjetas = (TextView) findViewById(R.id.textViewTarjetDashboard);
        final FrameLayout frameIngreso = (FrameLayout) findViewById(R.id.frameLayoutIngreso);
        final FrameLayout frameGasto = (FrameLayout) findViewById(R.id.frameLayoutGasto);
        final FrameLayout frameTarjeta = (FrameLayout) findViewById(R.id.frameLayoutTarjeta);
        final DBConnection connection = new DBConnection(DashboardDrawer.this);
        textViewIngresoFijoDashboard = (TextView) findViewById(R.id.textViewIngresoFijoDashboard);
        textViewGastoFijoDashboard = (TextView) findViewById(R.id.textViewGastoFijoDashboard);
        textViewTarjetaFijoDashboard = (TextView) findViewById(R.id.textViewTarjetaFijoDashboard);

        /*
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DBConnection connection = new DBConnection(DashboardDrawer.this);

                int rowCount = connection.getCantidadDeFilas("Tarjeta");

                //Carga todos los gastos de la base de datos
                List<Tarjeta> tarjetas = connection.getAllTarjetas();

                ArrayList<Integer> listaIds = new ArrayList<>();
                ArrayList<Integer> listaFourDigits = new ArrayList<>();
                ArrayList<Double> listaConsumos = new ArrayList<>();
                ArrayList<String> listaFechaCorte = new ArrayList<>();
                ArrayList<String> listaFechaVencimiento = new ArrayList<>();

                //llena la lista con los Ids de las tarjetas
                for(int i = 0; i < tarjetas.size(); i++){
                    Tarjeta tarjeta = tarjetas.get(i);
                    listaIds.add(tarjeta.getId());
                }

                //llena la lista con los cvv de las tarjetas
                for(int i = 0; i < tarjetas.size(); i++){
                    Tarjeta tarjeta = tarjetas.get(i);
                    listaFourDigits.add(tarjeta.getFourdigits());
                }

                //llena la lista con los consumos de las tarjetas
                for(int i = 0; i < tarjetas.size(); i++){
                    Tarjeta tarjeta = tarjetas.get(i);
                    listaConsumos.add(tarjeta.getConsumo());
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

                int j = 0;
                while (j < rowCount){

                    Calendar calendar = Calendar.getInstance();

                    int systemDay = calendar.get(calendar.DAY_OF_MONTH);
                    int systemMonth = calendar.get(calendar.MONTH) + 1;
                    int systemYear = calendar.get(calendar.YEAR);

                    final ContentValues dataConsumo=new ContentValues();
                    dataConsumo.put("Consumo", 0);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date myDate;

                    String dateVenci = listaFechaVencimiento.get(j);
                    String dateCorte = listaFechaCorte.get(j);

                    int dayCorte = 0;
                    int monthCorte = 0;
                    int day = 0;
                    int month = 0;
                    int year = 0;

                    try {
                        myDate = df.parse(dateVenci);

                        day = myDate.getDate();
                        month = myDate.getMonth() + 1;
                        year = myDate.getYear() + 1900;

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    try {
                        myDate = df.parse(dateCorte);

                        dayCorte = myDate.getDate();
                        monthCorte = myDate.getMonth() + 1;

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(monthCorte == 2){
                        if(dayCorte == 29 || dayCorte == 30 || dayCorte == 31 && systemYear%4 == 1){
                            if(systemDay == 28){
                                connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                                connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                            }
                        }else if(dayCorte == 30 || dayCorte == 31 && systemYear%4 == 0){
                            if(systemDay == 29){
                                connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                                connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                            }
                        }
                    }else if(monthCorte == 4 || monthCorte == 6 || monthCorte == 9 || monthCorte == 11){
                        if(dayCorte == 31){
                            if(systemDay == 30){
                                connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                                connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                            }
                        }else if(dayCorte == systemDay){
                            connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                            connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                        }
                    }else if(dayCorte == systemDay){
                        connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                        connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                    }

                    if(year == systemYear && month == systemMonth && day == systemDay){

                        connection.deleteData("Tarjeta", listaIds.get(j));

                    }

                    j++;

                }
            }
        });
        */

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 19); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, 36);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarms = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), AlaramReceiver.class);
        intent.putExtra(AlaramReceiver.ACTION_ALARM, AlaramReceiver.ACTION_ALARM);

        final PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarms.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_DAY, pIntent);

        boolean alarmUp = (PendingIntent.getBroadcast(this, 0,
                new Intent("com.example.earllarry.intelligentfinance.MY_UNIQUE_ACTION"),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            Log.d("myTag", "Alarm is already active");
        }


        String lista = "";

        double helpIngreso = 0;
        double helpBalance = 0;
        double helpGasto = 0;
        double helpGastoEfectivo = 0;
        double helpTarjeta = 0;
        double gastoTarjeta = 0;

        List<Usuario> usuarios = connection.getAllUsuarios();

        //llena la lista con los nombres de los usuarios
        for(int i = 0; i < usuarios.size(); i++){
            Usuario usuario = usuarios.get(i);
            lista += usuario.getNombre().toString();
        }

        textViewBienvenida.setText("Bienvenido, " + lista);

        helpIngreso = connection.getTotal("Monto","Ingreso");
        helpGasto = connection.getTotal("Monto","Gasto");
        helpTarjeta = connection.getTotal("Monto","Tarjeta");
        helpGastoEfectivo = connection.getTotalEfectivo("Monto","Gasto");
        helpBalance = helpIngreso - helpGastoEfectivo;
        gastoTarjeta = connection.getTotal("Gasto", "Tarjetagasto");

        if(helpBalance <= 0){
            textViewBalance.setTextColor(getResources().getColor(R.color.BalanceRed));
        }else{
            textViewBalance.setTextColor(getResources().getColor(R.color.BalanceBlue));

        }
        textViewBalance.setText("$ " + helpBalance);
        textViewIngresos.setText("$ " + helpIngreso);
        textViewGastos.setText("$ " + helpGasto);
        textViewTarjetas.setText("$ " + (helpTarjeta - gastoTarjeta));

        //cliqueables
        frameIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuIngreso.class));
            }
        });

        frameGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuGasto.class));
            }
        });

        frameTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuTarjetas.class));
            }
        });

        textViewIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuIngreso.class));
            }
        });

        textViewGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuGasto.class));
            }
        });

        textViewTarjetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuTarjetas.class));
            }
        });

        textViewIngresoFijoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuIngreso.class));
            }
        });

        textViewGastoFijoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuGasto.class));
            }
        });

        textViewTarjetaFijoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardDrawer.this, MenuTarjetas.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ingresos) {
            startActivity(new Intent(DashboardDrawer.this, MenuIngreso.class));

        } else if (id == R.id.nav_gastos) {
            startActivity(new Intent(DashboardDrawer.this, MenuGasto.class));

        } else if (id == R.id.nav_tarjetas) {
            startActivity(new Intent(DashboardDrawer.this, MenuTarjetas.class));

        } else if (id == R.id.nav_metas) {
            startActivity(new Intent(DashboardDrawer.this, MenuMetas.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
