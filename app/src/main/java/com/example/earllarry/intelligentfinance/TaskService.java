package com.example.earllarry.intelligentfinance;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.DecimalFormat;
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
        Log.i("TaskService", "Service running");
        Toast.makeText(this, "Servicio", Toast.LENGTH_LONG).show();

        CorteVenciMethod();
        MetaMethod();

    }


    public void ingresoRecurrente(){

        final DBConnection connection = new DBConnection(TaskService.this);

        //Carga todos los ingresos de la base de datos
        List<Ingreso> ingresos = connection.getAllIngresos();

    }

    public void CorteVenciMethod() {

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
        for (int i = 0; i < tarjetas.size(); i++) {
            Tarjeta tarjeta = tarjetas.get(i);
            listaIds.add(tarjeta.getId());
        }

        //llena la lista con los cvv de las tarjetas
        for (int i = 0; i < tarjetas.size(); i++) {
            Tarjeta tarjeta = tarjetas.get(i);
            listaFourDigits.add(tarjeta.getFourdigits());
        }

        //llena la lista con los consumos de las tarjetas
        for (int i = 0; i < tarjetas.size(); i++) {
            Tarjeta tarjeta = tarjetas.get(i);
            listaConsumos.add(tarjeta.getConsumo());
        }

        //llena la lista con las fechas de corte de las tarjetas
        for (int i = 0; i < tarjetas.size(); i++) {
            Tarjeta tarjeta = tarjetas.get(i);
            listaFechaCorte.add(tarjeta.getCorte());
        }

        //llena la lista con las fechas de vencimiento de las tarjetas
        for (int i = 0; i < tarjetas.size(); i++) {
            Tarjeta tarjeta = tarjetas.get(i);
            listaFechaVencimiento.add(tarjeta.getVencimiento());
        }

        int j = 0;
        while (j < rowCount) {

            Calendar calendar = Calendar.getInstance();

            int systemDay = calendar.get(calendar.DAY_OF_MONTH);
            int systemMonth = calendar.get(calendar.MONTH) + 1;
            int systemYear = calendar.get(calendar.YEAR);

            final ContentValues dataConsumo = new ContentValues();
            dataConsumo.put("Consumo", 0);

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date myDateVenci;
            Date myDateCorte;

            String dateVenci = listaFechaVencimiento.get(j);
            String dateCorte = listaFechaCorte.get(j);

            int dayCorte = 0;
            int monthCorte = 0;
            int day = 0;
            int month = 0;
            int year = 0;

            try {
                myDateVenci = df.parse(dateVenci);

                day = myDateVenci.getDate();
                month = myDateVenci.getMonth() + 1;
                year = myDateVenci.getYear() + 1900;

            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                myDateCorte = df.parse(dateCorte);

                dayCorte = myDateCorte.getDate();
                monthCorte = myDateCorte.getMonth() + 1;

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (monthCorte == 2) {
                if (dayCorte == 29 || dayCorte == 30 || dayCorte == 31 && systemYear % 4 == 1) {
                    if (systemDay == 28) {
                        connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                        connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                    }
                } else if (dayCorte == 30 || dayCorte == 31 && systemYear % 4 == 0) {
                    if (systemDay == 29) {
                        connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                        connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                    }
                }
            } else if (monthCorte == 4 || monthCorte == 6 || monthCorte == 9 || monthCorte == 11) {
                if (dayCorte == 31) {
                    if (systemDay == 30) {
                        connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                        connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                    }
                } else if (dayCorte == systemDay) {
                    connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                    connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
                }
            } else if (dayCorte == systemDay) {
                connection.deleteDataGastoTarjetaId(listaFourDigits.get(j));
                connection.updateTarjetaConsumoId(listaIds.get(j), dataConsumo);
            }

            if (month == 2) {
                if (day == 29 && year % 4 != 0) {
                    if (systemDay == 28 && month == systemMonth && year == systemYear) {
                        connection.deleteData("Tarjeta", listaIds.get(j));
                    }
                }
            } else if (year == systemYear && month == systemMonth && day == systemDay) {

                connection.deleteData("Tarjeta", listaIds.get(j));

            }

            j++;

        }

    }

    private void MetaMethod() {

        final DBConnection connection = new DBConnection(TaskService.this);

        int rowCount = connection.getCantidadDeFilas("Meta");

        //Carga todos los metas de la base de datos
        List<Meta> metas = connection.getAllMetas();

        ArrayList<Integer> listaIds = new ArrayList<>();
        ArrayList<String> listaConceptos = new ArrayList<>();
        ArrayList<Double> listaMontos = new ArrayList<>();
        ArrayList<Double> listaAhorrados = new ArrayList<>();
        ArrayList<String> listaFechaInicio = new ArrayList<>();
        ArrayList<String> listaFechaFinal = new ArrayList<>();

        //llena la lista con los ids de las metas
        for (int i = 0; i < metas.size(); i++) {
            Meta meta = metas.get(i);
            listaIds.add(meta.getId());
        }

        //llena la lista con los conceptos de las metas
        for(int i = 0; i < metas.size(); i++){
            Meta meta = metas.get(i);
            listaConceptos.add(meta.getConcepto());
        }

        //llena la lista con los montos de las metas
        for (int i = 0; i < metas.size(); i++) {
            Meta meta = metas.get(i);
            listaMontos.add(meta.getMonto());
        }

        //llena la lista con los ahorros de las metas
        for (int i = 0; i < metas.size(); i++) {
            Meta meta = metas.get(i);
            listaAhorrados.add(meta.getAhorrado());
        }

        //llena la lista con los fecha inicio de las metas
        for (int i = 0; i < metas.size(); i++) {
            Meta meta = metas.get(i);
            listaFechaInicio.add(meta.getFechaInicio());
        }

        //llena la lista con las fechas finales de las metas
        for (int i = 0; i < metas.size(); i++) {
            Meta meta = metas.get(i);
            listaFechaFinal.add(meta.getFechaFinal());
        }

        int j = 0;
        while (j < rowCount) {

            Calendar calendar = Calendar.getInstance();

            int systemDay = calendar.get(calendar.DAY_OF_MONTH);
            int systemMonth = calendar.get(calendar.MONTH) + 1;
            int systemYear = calendar.get(calendar.YEAR);

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date myDateInicio;
            Date myDateFinal;

            double helpMonto = listaMontos.get(j);
            double helpAhorroMensual = helpMonto / 12;
            double ahorroMensual = Double.parseDouble(new DecimalFormat("##.##").format(helpAhorroMensual));
            double helpAhorro = listaAhorrados.get(j);
            double updateAhorro = helpAhorro + ahorroMensual;

            ContentValues data = new ContentValues();
            data.put("Ahorro", updateAhorro);

            String dateInicio = listaFechaInicio.get(j);
            String dateFinal = listaFechaFinal.get(j);
            String systemDate = systemDay + "-" + systemMonth + "-" + systemYear;

            int dayInicio = 0;
            int monthInicio = 0;
            int yearInico = 0;
            int dayFinal = 0;
            int monthFinal = 0;
            int yearFinal = 0;

            try {
                myDateInicio = df.parse(dateInicio);

                dayInicio = myDateInicio.getDate();
                monthInicio = myDateInicio.getMonth() + 1;
                yearInico = myDateInicio.getYear() + 1900;

            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                myDateFinal = df.parse(dateFinal);

                dayFinal = myDateFinal.getDate();
                monthFinal = myDateFinal.getMonth() + 1;
                yearFinal = myDateFinal.getYear() + 1900;

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (systemMonth == 2 && systemMonth != monthInicio) {
                if (systemDay == 28 && systemYear % 4 != 0) {
                    if (dayInicio == 29 || dayInicio == 30 || dayInicio == 31) {
                        connection.updateDataMeta(listaIds.get(j), data);
                        connection.insertGasto(listaConceptos.get(j), ahorroMensual, "Efectivo", false, systemDate, "");
                    }
                }
            } else if (systemMonth == 4 || systemMonth == 6 || systemMonth == 9 || systemMonth == 11) {
                if (systemMonth != monthInicio) {
                    if (dayInicio == 31) {
                        if (systemDay == 30) {
                            connection.updateDataMeta(listaIds.get(j), data);
                            connection.insertGasto(listaConceptos.get(j), ahorroMensual,"Efectivo", false, systemDate,"");
                        }
                    } else if (systemDay == dayInicio) {
                        connection.updateDataMeta(listaIds.get(j), data);
                        connection.insertGasto(listaConceptos.get(j), ahorroMensual,"Efectivo", false, systemDate,"");
                    }
                }
            }else if (systemDay == dayInicio && systemMonth != monthInicio) {
                connection.updateDataMeta(listaIds.get(j), data);
                connection.insertGasto(listaConceptos.get(j), ahorroMensual,"Efectivo", false, systemDate,"");

            }else if (systemMonth == monthInicio && systemYear == yearFinal) {
                //data.put("Estado", "Completo");
                ContentValues data2 = new ContentValues();
                data2.put("Ahorro", helpMonto);
                connection.updateDataMeta(listaIds.get(j), data2);
                connection.insertGasto(listaConceptos.get(j), ahorroMensual,"Efectivo", false, systemDate,"");
            }

            j++;

        }
    }

}