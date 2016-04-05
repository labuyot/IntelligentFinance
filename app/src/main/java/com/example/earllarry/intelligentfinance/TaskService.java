package com.example.earllarry.intelligentfinance;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by EarlLarry on 04-Apr-16.
 */
public class TaskService extends IntentService {

    public TaskService() {
        super("TaskService");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onHandleIntent(Intent arg0) {

        // Do some task
        Log.i("TaskService", "Service running wtf");
        Toast.makeText(this, "Servicio", Toast.LENGTH_LONG).show();

        final DBConnection connection = new DBConnection(TaskService.this);

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

            if(month == 2){
                if(day == 29 && year%4 != 0){
                    if(systemDay == 28 && month == systemMonth && year == systemYear){
                        connection.deleteData("Tarjeta", listaIds.get(j));
                    }
                }
            }else if(year == systemYear && month == systemMonth && day == systemDay){

                connection.deleteData("Tarjeta", listaIds.get(j));

            }

            j++;

        }
    }

}