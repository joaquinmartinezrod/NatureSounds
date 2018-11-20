package com.joaquinmtz.naturesounds;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;

public class SplashProgress extends AsyncTask<Void,Integer,Void> {

    private ProgressBar progressBar;
    private Context context;

    public SplashProgress(ProgressBar progressBar, Context context) {
        this.progressBar = progressBar;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //iteraciones
        for(int i=1; i<=10 ; i++){
            publishProgress(i*20);//puedes mandar varios valores separados por comas por ser ... array
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);//array
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Intent intent = new Intent(context,Player.class);
        context.startActivity(intent);
    }

}
