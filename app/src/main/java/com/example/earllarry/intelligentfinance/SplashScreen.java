package com.example.earllarry.intelligentfinance;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    protected  static final int seconds = 8;
    protected static final int TIMER_RUNTIME = seconds * 1000;
    protected static final int delay = 2;
    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mProgressBar = (ProgressBar)findViewById(R.id.progressBarSplash);
        mProgressBar.setMax(maximoProgreso());
        empezarProgress();

    }

    //timer para el tiempo del splash screen
    public void empezarProgress(){

        new CountDownTimer(TIMER_RUNTIME, 1000){

            @Override
            public void onTick(long milisecondsToEnd){
                mProgressBar.setProgress(progreso(milisecondsToEnd));
            }

            @Override
            public void onFinish(){

                startActivity(new Intent(SplashScreen.this, WelcomeScreen.class));
                finish();

            }
        }.start();
    }

    public int progreso(long milisegundos){

        return (int)((TIMER_RUNTIME-milisegundos) / 1000);

    }

    public int maximoProgreso(){
        return seconds - delay;
    }
}
