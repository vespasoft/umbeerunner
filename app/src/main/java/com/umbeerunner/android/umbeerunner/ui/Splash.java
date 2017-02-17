package com.umbeerunner.android.umbeerunner.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.umbeerunner.android.umbeerunner.R;
import com.umbeerunner.android.umbeerunner.preferences.SessionPreferences;

import java.util.logging.Handler;

public class Splash extends Activity {

    private static final String TAG = Splash.class.getSimpleName();

    // Clase que administra la preferencia de la session del usuario
    SessionPreferences sessionpref;
	// Progress Bar
	static int progress;
    ProgressBar progressBar;
    int progressStatus = 0;
    Handler handler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sessionpref = new SessionPreferences(this);

        progress = 0;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        // starting new Async Task
        new LoadProgressBar().execute("Cargando ...");
    }

    /**
     * Background Async Task to download file
     **/
    class LoadProgressBar extends AsyncTask<String, Void, String> {

        //---hacer algun trabajo que dure---
        private int doSomeWork() {
            try {
                //---simular hacer algun trabajo---
            	Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ++progress;
        }
        
        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            //---Aqui va el codigo del hilo de ejecucion---
            while (progressStatus < 100) {
                progressStatus = doSomeWork();
                progressBar.setProgress(progressStatus);
            }
            return null;
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // Si el usuario ya inicio session en el dispositivo
            if  ( sessionpref.isLoggedIn() ) {
                Log.d(TAG, "Session iniciada TokenId;" + sessionpref.getUNAME() + ", UID " + sessionpref.getUID());
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
            } else {
                //--- llamar a otro Activity
                Intent intent = new Intent(Splash.this, LoginActivity.class);
                startActivity(intent);
            }
            //---Oculta la barra de progreso 0 - VISIBLE 4-INVISIBLE  8 - GONE ---
            // progressBar.setVisibility(View.GONE);
            finish();
        }

    }
}
