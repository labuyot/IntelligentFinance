package com.example.earllarry.intelligentfinance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DashboardDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textViewBienvenida;
    private TextView textViewBalance;
    private TextView textViewIngresos;
    private TextView textViewGastos;
    private TextView textViewTarjetas;

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



        String lista = "";

        int helpIngreso = 0;
        int helpBalance = 0;
        int helpGasto = 0;
        int helpTarjeta = 0;

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
        helpBalance = (helpIngreso + helpTarjeta) - helpGasto;

        if(helpBalance <= 0){
            textViewBalance.setTextColor(getResources().getColor(R.color.BalanceRed));
        }else{
            textViewBalance.setTextColor(getResources().getColor(R.color.BalanceBlue));

        }
        textViewBalance.setText("$ " + helpBalance);
        textViewIngresos.setText("$ " + helpIngreso);
        textViewGastos.setText("$ " + helpGasto);
        textViewTarjetas.setText("$ " + helpTarjeta);

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
